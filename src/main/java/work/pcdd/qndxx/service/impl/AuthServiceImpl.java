package work.pcdd.qndxx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.mapper.UserMapper;
import work.pcdd.qndxx.service.AuthService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author pcdd
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    @Override
    public R unSafeLogin(String userId, HttpSession session) {
        User user = userMapper.findById(userId);
        if (user != null && Objects.equals(user.getUserId(), userId)) {
            session.setAttribute("user", user);
            return R.ok();
        }
        return R.fail(RCode.USER_LOGIN_ERROR);
    }

    @Override
    public R<String> login(String userId, String pwd, HttpSession session) {
        User user = new User();
        user.setUserId(userId);
        user.setPwd(pwd);
        User obj = userMapper.login(user);
        if (obj != null
                && Objects.equals(obj.getUserId(), userId)
                && Objects.equals(obj.getPwd(), pwd)) {
            session.setAttribute("admin", obj);
            return R.ok("管理员登录成功");
        }
        return R.fail(RCode.USER_LOGIN_ERROR);
    }

}
