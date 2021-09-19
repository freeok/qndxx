package work.pcdd.qndxx.service;

import work.pcdd.qndxx.common.vo.Result;
import work.pcdd.qndxx.entity.Student;

import javax.servlet.http.HttpSession;

/**
 * @author AD
 */
public interface StudentService {
    /**
     * 用户无密码登录
     *
     * @param stuId   学号
     * @param session session
     * @return 执行结果
     */
    Result unSafeLogin(String stuId, HttpSession session);

    /**
     * 增加学生
     *
     * @param student student
     * @return 执行结果
     */
    Result addStudent(Student student);

    /**
     * 根据学号删除学生
     *
     * @param stuId 学号
     * @return 执行结果
     */
    Result delStudentById(String stuId);

    /**
     * 在指定的班级中根据学号或姓名模糊查询学生
     *
     * @param student student bean
     * @return 执行结果
     */
    Result findByName(Student student);

    /**
     * 更新学生信息
     *
     * @param student student bean
     * @return 执行结果
     */
    Result updStudentById(Student student);
}
