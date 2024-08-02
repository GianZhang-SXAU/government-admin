package com.zja.sxau.govenmentadmin.service.impl;

import com.zja.sxau.govenmentadmin.entity.Appointment;
import com.zja.sxau.govenmentadmin.entity.Queue;
import com.zja.sxau.govenmentadmin.entity.Window;
import com.zja.sxau.govenmentadmin.mapper.AppointmentMapper;
import com.zja.sxau.govenmentadmin.mapper.QueueMapper;
import com.zja.sxau.govenmentadmin.mapper.WindowMapper;
import com.zja.sxau.govenmentadmin.service.AppointmentService;
import com.zja.sxau.govenmentadmin.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class QueueServiceImpl implements QueueService {

    private static final String QUEUE_NUMBER_KEY = "queue:number:";
    private static final String ORDER_NUMBER_KEY = "order:number:";

    @Autowired
    private QueueMapper queueMapper;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private WindowMapper windowMapper;

    @Autowired
    private AppointmentService appointmentService;

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Queue> getQueueByDocumentNumber(String documentNumber) {
        return queueMapper.getQueueByDocumentNumber(documentNumber);
    }

    @Override
    public List<Queue> getAllQueues() {
        return queueMapper.getAllQueues();
    }

    @Override
    public void insertQueue(Queue queue) {
        queueMapper.insertQueue(queue);
    }

    @Override
    public void updateQueue(Queue queue) {
        queueMapper.updateQueue(queue);
    }

    @Override
    public void deleteQueue(int queueID) {
        queueMapper.deleteQueue(queueID);
    }

    /*
     * @Author 张建安
     * @parma documentNumber
     * @return List<Appointment>
     *
     * 根据证件号查询预约订单
     * */
    @Override
    public List<Appointment> getAppointmentByDocumentNumber(String documentNumber) {
        return appointmentMapper.getAppointmentsByDocumentNumber(documentNumber);
    }

    /*
     * @Author 张建安
     * @parma ServiceId
     * @return List<Window>
     *
     * 根据服务号查询可用窗口
     * */
    @Override
    public List<Window> getWindowByServiceIdAndStatus(int serviceID) {
        return windowMapper.findByServiceIdAndStatus(serviceID);
    }


    /*
     * @Author 张建安
     * @parma Appointment
     * @return void
     * 点击立即叫号后，选择一个预约条目后，立即根据ServiceId查询可用窗口，根据当天的顺序存储在Redis中，并新加一个状态栏
     * */
    @Override
    public Queue queryMethod(Appointment appointment) {
        //        2.根据用户选择，查询叫号服务的可用窗口
        List<Window> availableWindows = windowMapper.findByServiceIdAndStatus(appointment.getServiceId());

        if (availableWindows.isEmpty()) {
            throw new RuntimeException("这个服务没有可用的窗口");
        }
//        3.使用算法，选择可用窗口中排队订单数量最少的窗口，分配排队数量最少的窗口
        Window assignedWindow = null;
        int minQueueCount = Integer.MAX_VALUE;

        for (Window window : availableWindows) {
            int queueCount = windowMapper.countWaitingQueueByWindowId(window.getWindowId());
            if (queueCount < minQueueCount) {
                minQueueCount = queueCount;
                assignedWindow = window;
            }
        }

        if (assignedWindow == null) {
            throw new RuntimeException("筛选后没有可用的服务窗口");
        }

//        4.生成四位数排队号码
        int queueNumber = generateIncrementalQueueNumber(appointment.getAppointmentDate());

//        5.生成订单号
        String orderNumber = generateOrderNumber();

//        6.修改排队数据并将其保存在数据库中
        Queue queue = new Queue();
        queue.setAppointmentId(appointment.getAppointmentId());
        queue.setWindowId(assignedWindow.getWindowId());
        queue.setQueueNumber(queueNumber);
        queue.setQueueStatus("waiting");
        queue.setOrderNumber(orderNumber);
        queue.setCalledTime(null); //将呼叫设置为空，后续进行设置
        queueMapper.insertQueue(queue);

        // 7. 存储到Redis
        storeQueueInfoInRedis(appointment, queue, assignedWindow);

        return queue;
    }

    //    生成累加的排队号码
    private int generateIncrementalQueueNumber(Date appointmentDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateKey = sdf.format(appointmentDate);
        String redisKey = QUEUE_NUMBER_KEY + dateKey;

        Long queueNumber = redisTemplate.opsForValue().increment(redisKey, 1);

        // 确保当天排队号码从0001开始
        if (queueNumber == 1) {
            redisTemplate.expire(redisKey, 1, TimeUnit.DAYS); // 设置当天的过期时间
        }

        return Math.toIntExact(queueNumber);
    }

    private String generateOrderNumber() {
        return "ORDER-" + (int)(Math.random() * 100000000);
    }

    //    将数据存储在Redis中，随后将使用Redis进行排队大屏的展示
    private void storeQueueInfoInRedis(Appointment appointment, Queue queue, Window window) {
        String lastFourDigits = appointment.getDocumentNumber().substring(Math.max(appointment.getDocumentNumber().length() - 4, 0));
        String redisKey = "queue:" + queue.getQueueId(); // 用排队ID作为Redis键

        String redisValue = String.format("DocumentLastFour: %s, QueueNumber: %04d, ServiceId: %d, WindowId: %d, OrderNumber: %s",
                lastFourDigits, queue.getQueueNumber(), appointment.getServiceId(), window.getWindowId(), queue.getOrderNumber());

        // 将排队信息存储到Redis，设置过期时间为30分钟
        redisTemplate.opsForValue().set(redisKey, redisValue, 30, TimeUnit.MINUTES);

        // 存储订单号
        redisTemplate.opsForValue().set(ORDER_NUMBER_KEY + queue.getOrderNumber(), redisKey, 30, TimeUnit.MINUTES);
    }

}
