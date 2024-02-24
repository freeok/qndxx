package work.pcdd.qndxx.service;

import com.github.pagehelper.PageInfo;
import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author pcdd
 */
public interface AdminService {

    /**
     * 管理员登录
     *
     * @param userId  管理员账户（学号）
     * @param pwd     密码
     * @param session session
     * @return 执行结果
     */
    R<String> login(String userId, String pwd, HttpSession session);

    /**
     * 根据组织名查询所有学生的学号，姓名，组织
     *
     * @param organizeName 组织名
     * @param pageNum      起始页
     * @param pageSize     每页显示的条数
     * @return 执行结果
     */
    PageInfo<User> findAllByOrganizeName(String organizeName, int pageNum, int pageSize);

    /**
     * 查询不同组织的截图已交人员
     *
     * @param organizeName 组织名
     * @param pageNum      起始页
     * @param pageSize     每页显示的条数
     * @return 执行结果
     */
    PageInfo<User> findSubmitted(String organizeName, int pageNum, int pageSize);

    /**
     * 查询不同组织的截图未交人员
     *
     * @param organizeName 组织名
     * @param pageNum      起始页
     * @param pageSize     每页显示的条数
     * @return 学生信息
     */
    PageInfo<User> findUnpaid(String organizeName, int pageNum, int pageSize);


    /**
     * 查询不同组织的截图已交人数
     *
     * @param organizeName 组织名
     * @return 已交人数
     */
    Integer findSubmittedCount(String organizeName);

    /**
     * 查询不同组织的截图未交人数
     *
     * @param organizeName 组织名
     * @return 未交人数
     */
    Integer findUnpaidCount(String organizeName);

    /**
     * 管理员修改密码
     *
     * @param oldPwd  旧密码
     * @param newPwd  新密码
     * @param session session对象
     * @return 执行结果
     */
    R updPwd(String oldPwd, String newPwd, HttpSession session);

    Map<String, List<Object>> getSubmitEcharts();

}
