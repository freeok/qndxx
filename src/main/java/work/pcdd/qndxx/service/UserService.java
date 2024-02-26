package work.pcdd.qndxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author pcdd
 */
public interface UserService extends IService<User> {

    /**
     * 在指定的组织中根据账号或姓名模糊查询学生
     */
    R fuzzyQuery(User user);

    /**
     * 修改管理员密码
     */
    R updPwd(String oldPwd, String newPwd, HttpSession session);


}
