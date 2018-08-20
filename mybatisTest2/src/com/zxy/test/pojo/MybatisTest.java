package com.zxy.test.pojo;

import com.zxy.test.dao.EmployeeMapper;
import com.zxy.test.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
/**
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
public class MybatisTest {

    /**
     * 1.配置xml配置文件创建一个sqlsessionFactory对象
     * 2.sql配置文件，配置了每一个sql，以及sql的分装规则等
     * 3.将sql的映射文件注册在全局配置文件中
     * 4.写代码
     *          1.根据全局配置文件得到sqlSessionFactory
     *          2.使用sqlSessionFactory获取sqlSession对象，进行增删改查
     *          一个sqlSession获取到和数据库的一次对话，用完关闭
     *          3.使用sql的唯一标示来告诉Mybatis都是保存映射文件的
     *
     *
     * @throws IOException
     */
    @Test
    public void testMybatis() throws IOException {
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        //w2.获取一个sqlSession实例，能直接执行已经映射的sql语句
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //执行sql需要的参数，
        Employee e1=sqlSession.selectOne("selectEmployee",1);
        System.out.println(e1);
        sqlSession.close();

    }

    /**
     * 与接口绑定的测试
     */
    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmployeeById(2);
        System.out.println(employee);
        sqlSession.close();
    }

    /**
     * 与接口绑定的测试注解的方式
     */
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = mapper.getEmployeeById(1);
            System.out.println(employee);
            sqlSession.close();
            }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();

        }
        }


    /**
     * 获取sqlSessionFactory对象
     *
     */
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
