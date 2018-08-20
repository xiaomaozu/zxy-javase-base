package com.zxy.test.dynamictest;

import com.zxy.test.dao.*;
import com.zxy.test.pojo.Department;
import com.zxy.test.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

public class DynamicTest{ /**
 * 1、接口式编程
 * 	原生：		Dao		====>  DaoImpl
 * 	mybatis：	Mapper	====>  xxMapper.xml
 *
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * 		（将接口和xml进行绑定）
 * 		EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * 		mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * 		sql映射文件：保存了每一个sql语句的映射信息：
 * 					将sql抽取出来。
 *
 *
 * @author lfy
 *
 */

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

   /* @Test
    public void testInnerParam() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee2 = new Employee();
            employee2.setLastName("%e%");
            List<Employee> list = mapper.getEmpsTestInnerParameter(employee2);
            for (Employee employee : list) {
                System.out.println(employee);
            }
        }finally{
            openSession.close();
        }
    }*/

    @Test
    public void testBatchSave() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = new ArrayList<>();
            emps.add(new Employee(null, "smith0x1", "smith0x1@atguigu.com", "1",new Department(1)));
            emps.add(new Employee(null, "allen0x1", "allen0x1@atguigu.com", "0",new Department(1)));
            mapper.addEmps(emps);
            openSession.commit();
        }finally{
            openSession.close();
        }
    }

   @Test
    public void testDynamicSql() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            //select * from tbl_employee where id=? and last_name like ?
            //测试if\where
            Employee employee = new Employee(1, "admin", null, "0");
            //查询的时候如果某些条件没带可能sql拼装会有问题
            //1、给where后面加上1=1，以后的条件都and xxx.
            //2、mybatis使用<where></where>标签来将所有的查询条件包括在内。mybatis就会将where标签中拼装的sql，多出来的and或者or去掉
            //where只会去掉第一个多出来的and或者or。
            /*	List<Employee> emps = mapper.getEmpsByConditionIf(employee );
			for (Employee emp : emps) {
				System.out.println(emp);
			}*/

            //查询的时候如果某些条件没带可能sql拼装会有问题
            //1、给where后面加上1=1，以后的条件都and xxx.
            //2、mybatis使用where标签来将所有的查询条件包括在内。mybatis就会将where标签中拼装的sql，多出来的and或者or去掉
            //where只会去掉第一个多出来的and或者or。

            //测试Trim
			/*List<Employee> emps2 = mapper.getEmpsByConditionTrim(employee);
			for (Employee emp : emps2) {
				System.out.println(emp);
			}*/


            //测试choose
		/*	List<Employee> list = mapper.getEmpsByConditionChoose(employee);
			for (Employee emp : list) {
				System.out.println(emp);
			}*/

            //测试set标签
			/*mapper.updateEmp(employee);
			openSession.commit();*/
//            List<Integer> ids = ;
            List<Employee> list = mapper.getEmpsByConditionForeach(Arrays.asList(1, 4,5,6));
            for (Employee emp : list) {
                System.out.println(emp);
            }

        }finally{
            openSession.close();
        }
   }






}
