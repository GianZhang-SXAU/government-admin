package com.zja.sxau.govenmentadmin.service.impl;

import com.zja.sxau.govenmentadmin.entity.Queue;
import com.zja.sxau.govenmentadmin.mapper.QueueMapper;
import com.zja.sxau.govenmentadmin.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private QueueMapper queueMapper;

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
}
