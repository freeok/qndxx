package work.pcdd.qndxx.service;

import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
public interface UserService {

    /**
     * 用户无密码登录
     */
    R unSafeLogin(String userId, HttpSession session);


    /**
     * 在指定的组织中根据学号或姓名模糊查询学生
     */
    R fuzzyQuery(User user);

    /**
     * 增加学生
     */
    R add(User user);

    /**
     * 根据学号删除学生
     */
    Integer delete(String userId);

    /**
     * 更新学生信息
     */
    Integer update(User user);

}
