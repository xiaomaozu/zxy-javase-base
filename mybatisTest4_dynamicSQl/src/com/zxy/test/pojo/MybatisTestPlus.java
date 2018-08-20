package com.zxy.test.pojo;

import com.zxy.test.dao.DepartmentMapper;
import com.zxy.test.dao.EmployeeMapper;
import com.zxy.test.dao.EmployeeMapperAnnotation;
import com.zxy.test.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MybatisTestPlus {

    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee =mapper.getEmpAndDept(5);
            System.out.println(employee.toString());
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();

        }
    }

    /**
     *测试getEmpByIdStep
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee =mapper.getEmpByIdStep(4);
            System.out.println(employee.getDepartment());
            System.out.println(employee.getLastName());
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();

        }
    }

    /**
     *测试getDeptByIdPlus
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department deptByIdPlus = mapper.getDeptByIdPlus(3);

            System.out.println(deptByIdPlus);
            System.out.println(deptByIdPlus.getEmps());
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();

        }
    }

    /**
     *测试分段查询collection、resultMap、getDeptByIdPlus
     * @throws IOException
     */
    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department deptByIdPlus = mapper.getDeptByIdStep(3);

            System.out.println(deptByIdPlus);
            System.out.println(deptByIdPlus.getEmps());
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();

        }
    }

    /**
     * 获取sqlSessionFactory对象
     */
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


}
