package work.pcdd.qndxx.mapper;

import org.apache.ibatis.annotations.MapKey;
import work.pcdd.qndxx.entity.Student;

import java.util.List;
import java.util.Map;

/**
 * @author pcdd
 */
public interface AdminMapper {

    /**
     * 管理员登录
     *
     * @param stu 管理员（学生）bean
     * @return 管理员（学生）bean
     */
    Student login(Student stu);

    /**
     * 根据班级名查询所有学生的学号，姓名，班级
     *
     * @param clazzName 班级名
     * @return 学生信息
     */
    List<Student> findAllByClazzName(String clazzName);

    /**
     * 查询所有学生
     *
     * @return 学生信息
     */
    List<Student> findAll();

    /**
     * 根据班级查询截图已交名单
     *
     * @param clazzName 班级名
     * @return 学生信息
     */
    @MapKey("stu_id")
    List<Map<String, Object>> findSubmitted(String clazzName);

    /**
     * 根据班级查询截图未交名单
     *
     * @param clazzName 班级名
     * @return 学生信息
     */
    List<Student> findUnpaid(String clazzName);

    /**
     * 根据班级查询截图已交人数
     *
     * @param clazzName 班级名
     * @return 记录数
     */
    int findSubmittedCount(String clazzName);

    /**
     * 根据班级查询截图未交交人数
     *
     * @param clazzName 班级名
     * @return 记录数
     */
    int findUnpaidCount(String clazzName);

    /**
     * 根据学号修改管理员密码
     *
     * @param student 管理员（学生）bean
     * @return 影响的行数
     */
    int updPwd(Student student);

}
