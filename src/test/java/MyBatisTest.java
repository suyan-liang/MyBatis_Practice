import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.nio.cs.US_ASCII;
import suyan.dao.Userdao;
import suyan.domain.QueryVo;
import suyan.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author:liang;
 * Date:2020/3/26;
 * Time:19:42;
 * Project Name:MyBatis_Practice;
 * Package Name:PACKAGE_NAME;
 * description:测试类
 */
public class MyBatisTest {

    InputStream in;
    SqlSession session;
    Userdao userdao;

    /**
     * 自动在test方法前执行
     * 创建动态代理
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        in= Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        session=factory.openSession();
        userdao=session.getMapper(Userdao.class);
    }

    /**
     * 自动在test方法后执行
     * 关闭session资源和inputstream流
     * @throws IOException
     */
    @After
    public void destory() throws IOException {
        in.close();
        session.close();
    }

    /**
     * 测试findAll函数
     */
    @Test
    public void test_findAll(){
        List<User> list = userdao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    /**
     * 测试findById函数
     */
    @Test
    public void test_findById(){
        Integer id=48;
        User user=userdao.findById(id);
        System.out.println(user);
    }
    /**
     * 测试saveUser函数
     */
    @Test
    public void test_saveUser(){
        User user=new User();
        user.setUsername("小屁");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("河南");
        userdao.saveUser(user);
        session.commit();
    }
    /**
     * 测试findByCondition函数
     */
    @Test
    public void test_findByCondition(){
        User user=new User();
        user.setUsername("老王");
        user.setSex("女");
        List<User> list=userdao.findByCondition(user);
        for (User user1 : list) {
            System.out.println(user1);
        }
    }
    /**
     * 测试findByIds函数
     * 实现select * from user where id in (?,?,?)
     */
    @Test
    public void test_findByIds(){
        List<Integer> ids=new ArrayList<Integer>();
        ids.add(45);
        ids.add(46);
        ids.add(48);
        QueryVo vo=new QueryVo();
        vo.setIds(ids);
        List<User> list=userdao.findByIds(vo);
        for (User user : list) {
            System.out.println(user);
        }
    }
}
