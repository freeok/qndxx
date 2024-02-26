package work.pcdd.qndxx.mapper;

import org.apache.ibatis.annotations.MapKey;
import work.pcdd.qndxx.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 说明：
 * dao层：Repository注解可以不加，但注入dao层的bean时会IDEA会有红线报错，
 * 但@Mapper或者@MapperScan必须加，前者加在每个mapper接口上，后者加载SpringBoot启动类上；
 * 推荐使用后者（比较方便），这样不用给每个mapper都加上@Mapper注解了
 *
 * @author pcdd
 */
public interface UserMapper {

    /**
     * 根据学号查询学生
     *
     * @param userId 用户id
     * @return User bean
     */
    User findById(String userId);

    /**
     * 在指定的组织中根据学号或姓名模糊查询学生
     *
     * @param user user bean
     * @return user bean
     */
    List<User> fuzzyQuery(User user);

    /**
     * 管理员登录
     */
    User login(User user);

    /**
     * 根据组织名查询所有学生的学号，姓名，组织
     *
     * @return 学生信息
     */
    List<User> findAllUserByOrganize(Integer organizeId);

    /**
     * 查询所有学生
     *
     * @return 学生信息
     */
    List<User> findAll();

    /**
     * 根据组织查询截图已交名单
     *
     * @return 学生信息
     */
    @MapKey("user_id")
    List<Map<String, Object>> findSubmitted(Integer organizeId);

    /**
     * 根据组织查询截图未交名单
     *
     * @return 学生信息
     */
    List<User> findUnpaid(Integer organizeId);

    /**
     * 根据组织查询截图已交人数
     *
     * @return 记录数
     */
    int findSubmittedCount(Integer organizeId);

    /**
     * 根据组织查询截图未交交人数
     *
     * @return 记录数
     */
    int findUnpaidCount(Integer organizeId);

    /**
     * 根据学号修改管理员密码
     *
     * @param user 管理员（学生）bean
     * @return 影响的行数
     */
    int updPwd(User user);

    /**
     * 增加学生
     *
     * @param user user bean
     * @return 影响的行数
     */
    int add(User user);

    /**
     * 更新学生信息
     *
     * @param user user bean
     * @return 影响的行数
     */
    int update(User user);

    /**
     * 根据学号删除学生
     *
     * @param userId 用户id
     * @return 影响的行数
     */
    int delete(String userId);

}
