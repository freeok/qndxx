package work.pcdd.qndxx.mapper;

import org.apache.ibatis.annotations.MapKey;
import work.pcdd.qndxx.entity.User;

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
    User login(User stu);

    /**
     * 根据组织名查询所有学生的学号，姓名，组织
     *
     * @param organizeName 组织名
     * @return 学生信息
     */
    List<User> findAllByorganizeName(String organizeName);

    /**
     * 查询所有学生
     *
     * @return 学生信息
     */
    List<User> findAll();

    /**
     * 根据组织查询截图已交名单
     *
     * @param organizeName 组织名
     * @return 学生信息
     */
    @MapKey("user_id")
    List<Map<String, Object>> findSubmitted(String organizeName);

    /**
     * 根据组织查询截图未交名单
     *
     * @param organizeName 组织名
     * @return 学生信息
     */
    List<User> findUnpaid(String organizeName);

    /**
     * 根据组织查询截图已交人数
     *
     * @param organizeName 组织名
     * @return 记录数
     */
    int findSubmittedCount(String organizeName);

    /**
     * 根据组织查询截图未交交人数
     *
     * @param organizeName 组织名
     * @return 记录数
     */
    int findUnpaidCount(String organizeName);

    /**
     * 根据学号修改管理员密码
     *
     * @param user 管理员（学生）bean
     * @return 影响的行数
     */
    int updPwd(User user);

    @MapKey("hour_start")
    List<Map<String, String>> getSubmitEcharts();

}
