package com.zxy.test.dao;

import com.zxy.test.pojo.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {

    @Select("select * from tbl_employee where id=#{id}")
    public Employee getEmployeeById(Integer id);
}
