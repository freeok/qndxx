package work.pcdd.qndxx.mapper;

import org.springframework.stereotype.Repository;
import work.pcdd.qndxx.entity.Student;

import java.util.List;

/**
 * 说明：
 * dao层：Repository注解可以不加，但注入dao层的bean时会IDEA会有红线报错，
 * 但@Mapper或者@MapperScan必须加，前者加在每个mapper接口上，后者加载SpringBoot启动类上；
 * 推荐使用后者（比较方便），这样不用给每个mapper都加上@Mapper注解了
 *
 * @author AD
 */
@Repository
public interface StudentMapper {
    /**
     * 根据学号查询学生
     *
     * @param stuId 学号
     * @return student bean
     */
    Student findById(String stuId);

    /**
     * 在指定的班级中根据学号或姓名模糊查询学生
     *
     * @param student student bean
     * @return student bean
     */
    List<Student> findByName(Student student);

    /**
     * 增加学生
     *
     * @param student student bean
     * @return 影响的行数
     */
    int addStudent(Student student);

    /**
     * 更新学生信息
     *
     * @param student student bean
     * @return 影响的行数
     */
    int updStudentById(Student student);

    /**
     * 根据学号删除学生
     *
     * @param stuId 学号
     * @return 影响的行数
     */
    int delStudentById(String stuId);
}
