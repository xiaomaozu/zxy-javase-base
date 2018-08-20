package com.zxy.test.dao;

import com.zxy.test.pojo.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    //多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("lastName")
    public Map<String, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    //返回一条记录的map；key就是列名，值就是对应的值
    public Map<String, Object> getEmpByIdReturnMap(Integer id);

    public List<Employee> getEmpsByLastNameLike(String lastName);

    public Employee getEmployeeByMap(Map<String,Object> map);

    public Employee getEmployeeByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmployeeById(Integer id);

    public int addEmp(Employee employee);

    public int updateEmp(Employee employee);

    public int deleteEmpById(Integer id);


}
