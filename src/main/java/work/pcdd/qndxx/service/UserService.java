package work.pcdd.qndxx.service;

import com.github.pagehelper.PageInfo;
import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
public interface UserService {

    /**
     * 在指定的组织中根据学号或姓名模糊查询学生
     */
    R fuzzyQuery(User user);

    /**
     * 根据组织名查询所有学生的学号，姓名，组织
     */
    PageInfo<User> findAllByOrganizeName(Integer organizeId, int pageNum, int pageSize);

    /**
     * 查询不同组织的截图已交人员
     */
    PageInfo<User> findSubmitted(Integer organizeId, int pageNum, int pageSize);

    /**
     * 查询不同组织的截图未交人员
     */
    PageInfo<User> findUnpaid(Integer organizeId, int pageNum, int pageSize);


    /**
     * 查询不同组织的截图已交人数
     */
    Integer findSubmittedCount(Integer organizeId);

    /**
     * 查询不同组织的截图未交人数
     */
    Integer findUnpaidCount(Integer organizeId);

    /**
     * 修改管理员密码
     */
    R updPwd(String oldPwd, String newPwd, HttpSession session);

    /**
     * 增加学生
     */
    R add(User user);


    /**
     * 更新学生信息
     */
    Integer update(User user);

    /**
     * 根据学号删除学生
     */
    Integer delete(String userId);

}
