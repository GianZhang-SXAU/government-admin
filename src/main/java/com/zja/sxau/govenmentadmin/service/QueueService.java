package com.zja.sxau.govenmentadmin.service;

import com.zja.sxau.govenmentadmin.entity.Appointment;
import com.zja.sxau.govenmentadmin.entity.Queue;
import com.zja.sxau.govenmentadmin.entity.Window;

import java.util.List;

public interface QueueService {
    public List<Queue> getQueueByDocumentNumber(String documentNumber);
    public List<Queue> getAllQueues();
    public void insertQueue(Queue queue);
    public void updateQueue(Queue queue);
    public void deleteQueue(int queueID);
    /*
     * @Author 张建安
     * @parma documentNumber
     * @return List<Appointment>
     *
     * 根据证件号查询预约订单
     * */
    public List<Appointment> getAppointmentByDocumentNumber(String documentNumber);
    /*
     * @Author 张建安
     * @parma ServiceId
     * @return List<Window>
     *
     * 根据服务号查询可用窗口
     * */
    public List<Window> getWindowByServiceIdAndStatus(int serviceID);

    /*
     * @Author 张建安
     * @parma Appointment
     * @return void
     * 点击立即叫号后，选择一个预约条目后，立即根据ServiceId查询可用窗口，根据当天的顺序存储在Redis中，并新加一个状态栏
     * */
    public Queue queryMethod(Appointment appointment);
}
