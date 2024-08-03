package com.zja.sxau.govenmentadmin.controller;

import com.zja.sxau.govenmentadmin.entity.Appointment;
import com.zja.sxau.govenmentadmin.entity.Queue;
import com.zja.sxau.govenmentadmin.entity.VO.QueueResponse;
import com.zja.sxau.govenmentadmin.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/queues")
public class QueueController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private QueueService queueService;

    @GetMapping("/byDocumentNumber")
    public List<Queue> getQueueByDocumentNumber(@RequestParam String documentNumber) {
        return queueService.getQueueByDocumentNumber(documentNumber);
    }

    @GetMapping
    public List<Queue> getAllQueues() {
        return queueService.getAllQueues();
    }

    @PostMapping
    public void insertQueue(@RequestBody Queue queue) {
        queueService.insertQueue(queue);
    }

    @PutMapping("/{queueId}")
    public void updateQueue(@PathVariable int queueId, @RequestBody Queue queue) {
        queue.setQueueId(queueId);
        queueService.updateQueue(queue);
    }

    @DeleteMapping("/{queueId}")
    public void deleteQueue(@PathVariable int queueId) {
        queueService.deleteQueue(queueId);
    }


    /*
     * @Author 张建安
     * @parma Appointment
     * @return void
     * 点击立即叫号后，选择一个预约条目后，立即根据ServiceId查询可用窗口，根据当天的顺序存储在Redis中，并新加一个状态栏
     * */

    @GetMapping("/{documentNumber}")
    public List<Appointment> getAppointmentByDocumentNumber(@PathVariable String documentNumber) {
//        1.查询预约订单
        return queueService.getAppointmentByDocumentNumber(documentNumber);
    }

    @PostMapping("/queuemethod")
    public ResponseEntity<?> addQueueMethod(@RequestBody Appointment appointment) {
        Queue queue = queueService.queryMethod(appointment);
        if (queue != null) {
            QueueResponse response = new QueueResponse("取号成功", queue);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("取号失败");
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllQueueInfo() {
        // 获取Redis中所有以 "queue:" 开头的键集合
        Set<String> keys = redisTemplate.keys("queue:*");

        if (keys == null || keys.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Redis中没有排队信息，请检查Redis连接或者今天是否有排队数据");
        }

        List<String> queueInfos = new ArrayList<>();

        for (String key : keys) {
            String keyType = redisTemplate.type(key).code(); // 获取键的类型

            if ("string".equals(keyType)) {
                // 处理字符串类型的键
                String value = redisTemplate.opsForValue().get(key);
                if (value != null) {
                    queueInfos.add(value);
                }
            } else if ("list".equals(keyType)) {
                // 处理列表类型的键
                List<String> listValues = redisTemplate.opsForList().range(key, 0, -1);
                if (listValues != null && !listValues.isEmpty()) {
                    queueInfos.addAll(listValues); // 添加所有列表元素到结果集合
                }
            } else {
                // 记录下不匹配的类型，或处理其他类型
                System.out.println("Skipping key " + key + " with type " + keyType);
            }
        }

        if (queueInfos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("没有可用的排队数据");
        }

        return ResponseEntity.ok(queueInfos);
    }


}
