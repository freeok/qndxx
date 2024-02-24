package work.pcdd.qndxx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.mapper.UserMapper;
import work.pcdd.qndxx.service.UserService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author pcdd
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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
    public R add(User user) {
        // 添加学生前判断学号是否存在，若存在，则拒绝添加
        if (userMapper.findById(user.getUserId()) != null) {
            return R.fail(RCode.USER_HAS_EXISTED);
        }
        return R.ok(userMapper.add(user));
    }

    @Override
    public Integer delete(String userId) {
        return userMapper.delete(userId);
    }

    @Override
    public R findByName(User user) {
        List<User> list = userMapper.findByName(user);
        return R.ok0(list, (long) list.size());
    }

    @Override
    public Integer update(User user) {
        return userMapper.update(user);
    }

}
