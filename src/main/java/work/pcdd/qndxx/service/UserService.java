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
     *
     * @param userId  用户id
     * @param session session
     * @return 执行结果
     */
    R unSafeLogin(String userId, HttpSession session);

    /**
     * 增加学生
     *
     * @param user user
     * @return 执行结果
     */
    R add(User user);

    /**
     * 根据学号删除学生
     *
     * @param userId 用户id
     * @return 执行结果
     */
    Integer delete(String userId);

    /**
     * 在指定的班级中根据学号或姓名模糊查询学生
     *
     * @param user user bean
     * @return 执行结果
     */
    R findByName(User user);

    /**
     * 更新学生信息
     *
     * @param user user bean
     * @return 执行结果
     */
    Integer update(User user);

}
