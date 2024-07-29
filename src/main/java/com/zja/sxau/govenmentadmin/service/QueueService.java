package com.zja.sxau.govenmentadmin.service;

import com.zja.sxau.govenmentadmin.entity.Queue;

import java.util.List;

public interface QueueService {
    public List<Queue> getQueueByDocumentNumber(String documentNumber);
    public List<Queue> getAllQueues();
    public void insertQueue(Queue queue);
    public void updateQueue(Queue queue);
    public void deleteQueue(int queueID);

}
