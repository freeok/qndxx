package work.pcdd.qndxx.service;

import work.pcdd.qndxx.common.vo.Result;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
public interface AdminService {

    /**
     * 管理员登录
     *
     * @param stuId   管理员账户（学号）
     * @param pwd     密码
     * @param session session
     * @return 执行结果
     */
    Result login(String stuId, String pwd, HttpSession session);

    /**
     * 根据班级名查询所有学生的学号，姓名，班级
     *
     * @param clazzName 班级名
     * @param start     起始页
     * @param limit     每页显示的条数
     * @return 执行结果
     */
    Result findAllByClazzName(String clazzName, int start, int limit);

    /**
     * 查询不同班级的截图已交人员
     *
     * @param clazzName 班级名
     * @param start     起始页
     * @param limit     每页显示的条数
     * @return 执行结果
     */
    Result findSubmitted(String clazzName, int start, int limit);

    /**
     * 查询不同班级的截图未交人员
     *
     * @param clazzName 班级名
     * @param start     起始页
     * @param limit     每页显示的条数
     * @return 学生信息
     */
    Result findUnpaid(String clazzName, int start, int limit);


    /**
     * 查询不同班级的截图已交人数
     *
     * @param clazzName 班级名
     * @return 已交人数
     */
    Result findSubmittedCount(String clazzName);

    /**
     * 查询不同班级的截图未交人数
     *
     * @param clazzName 班级名
     * @return 未交人数
     */
    Result findUnpaidCount(String clazzName);

    /**
     * 管理员修改密码
     *
     * @param oldPwd  旧密码
     * @param newPwd  新密码
     * @param session session对象
     * @return 执行结果
     */
    Result updPwd(String oldPwd, String newPwd, HttpSession session);

}
