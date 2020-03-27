package suyan.dao;

import suyan.domain.QueryVo;
import suyan.domain.User;

import java.util.List;

/**
 * Author:liang;
 * Date:2020/3/26;
 * Time:21:37;
 * Project Name:MyBatis_Practice;
 * Package Name:suyan.dao;
 * description:Userdao,对User表特定操作的规范接口
 */
public interface Userdao {
    /**
     * 查找user表中的所有信息
     * @return 结果的list
     */
    List<User> findAll();

    /**
     * 根据id查找user
     * @param id 用户Id
     * @return 查到的用户
     */
    User findById(Integer id);

    /**
     * 向User表中插入一条记录
     * @param user 要插入的用户信息
     */
    void saveUser(User user);

    /**
     * 不确定用户输入的是User的哪个信息，只知道输的是姓名或者姓名+性别，所以要在本地用标签<if>等进行逻辑判断
     * @param user 用户输入的信息
     * @return 查询到的结果
     */
    List<User> findByCondition(User user);

    /**
     * 实现select * from user where id in (?,?,?)
     * @param vo QueryVo
     * @return 查询到的结果
     */
    List<User> findByIds(QueryVo vo);
}
