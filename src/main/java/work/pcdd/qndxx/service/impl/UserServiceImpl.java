package work.pcdd.qndxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.mapper.UserMapper;
import work.pcdd.qndxx.service.UserService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author pcdd
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public R fuzzyQuery(User user) {
        List<User> list = userMapper.fuzzyQuery(user);
        return R.ok0(list, (long) list.size());
    }

    @Override
    public R updPwd(String oldPwd, String newPwd, HttpSession session) {
        User admin = (User) session.getAttribute("admin");
        String userId = admin.getUserId();
        User user = new User();
        user.setUserId(userId);
        user.setPwd(oldPwd);
        // 旧密码错误，拒绝修改
        if (userMapper.login(user) == null) {
            return R.fail(RCode.ADMIN_OLD_PASSWORD_ERROR);
        }
        user.setPwd(newPwd);
        if (userMapper.updPwd(user) == 1) {
            return R.ok();
        }

        return R.fail(RCode.ADMIN_UPDATE_PASSWORD_FAIL);
    }


}
