package work.pcdd.qndxx.mapper;

import work.pcdd.qndxx.entity.User;

import java.util.List;

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
     * 在指定的班级中根据学号或姓名模糊查询学生
     *
     * @param user user bean
     * @return user bean
     */
    List<User> findByName(User user);

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
