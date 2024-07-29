package com.zja.sxau.govenmentadmin.controller;

import com.zja.sxau.govenmentadmin.entity.Queue;
import com.zja.sxau.govenmentadmin.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/queues")
public class QueueController {

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
}
