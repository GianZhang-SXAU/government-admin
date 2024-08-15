package com.zja.sxau.govenmentadmin.mapper;

import com.zja.sxau.govenmentadmin.entity.Service;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ServiceMapper {
    @Select("SELECT * FROM services")
    List<Service> findAll();

    @Select("SELECT * FROM services WHERE service_id = #{serviceId}")
    Service findById(@Param("serviceId") Integer serviceId);

    @Insert("INSERT INTO services(service_name, description) VALUES(#{serviceName}, #{description})")
    void insert(Service service);

    @Update("UPDATE services SET service_name=#{serviceName}, description=#{description} WHERE service_id=#{serviceId}")
    void update(Service service);

    @Delete("DELETE FROM services WHERE service_id=#{serviceId}")
    void delete(@Param("serviceId") Integer serviceId);

    @Select("SELECT s.service_id, s.service_name, d.description FROM services s LEFT JOIN service_descriptions d ON s.service_id = d.service_id")
    List<Service> getAllServicesWithDescriptions();


}
