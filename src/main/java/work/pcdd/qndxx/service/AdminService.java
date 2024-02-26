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
     */
    R<String> login(String userId, String pwd, HttpSession session);

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
     * 提交时间分布 echarts 数据
     */
    Map<String, List<Object>> getSubmitEcharts();

}
