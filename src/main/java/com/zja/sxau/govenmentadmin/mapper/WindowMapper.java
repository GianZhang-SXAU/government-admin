package com.zja.sxau.govenmentadmin.mapper;

import com.zja.sxau.govenmentadmin.entity.Window;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WindowMapper {

    List<Window> getAllWindows();

    Window getWindowById(int windowId);

    void insertWindow(Window window);

    void updateWindow(Window window);

    void deleteWindow(int windowId);

    List<Window> findByServiceIdAndStatus(int serviceId);


    /*
    * @author: 张建安
    * @papam：windowid
    * @return: number
    * 记录各窗口的排队个数，用来决定对新的排队数据分配在哪个窗口
    * */
    int countWaitingQueueByWindowId(int windowId);
}
