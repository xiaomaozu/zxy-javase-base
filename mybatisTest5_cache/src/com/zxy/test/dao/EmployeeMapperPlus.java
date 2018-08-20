package com.zxy.test.dao;

import com.zxy.test.pojo.Employee;

import java.util.List;


public interface EmployeeMapperPlus {

    public Employee getEmpById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpByIdStep(Integer id);

    public List<Employee> getEmpsByDeptId(Integer id);
}
