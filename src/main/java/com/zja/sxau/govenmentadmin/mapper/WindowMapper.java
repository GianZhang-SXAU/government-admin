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

}
