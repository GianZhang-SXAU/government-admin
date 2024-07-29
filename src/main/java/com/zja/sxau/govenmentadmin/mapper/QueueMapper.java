package com.zja.sxau.govenmentadmin.mapper;


import com.zja.sxau.govenmentadmin.entity.Queue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QueueMapper {

    List<Queue> getQueueByDocumentNumber(String documentNumber);

    List<Queue> getAllQueues();


    void insertQueue(Queue queue);


    void updateQueue(Queue queue);

    void deleteQueue(int queueId);
}
