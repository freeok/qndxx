package work.pcdd.qndxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public R fuzzyQuery(User user) {
        List<User> list = userMapper.fuzzyQuery(user);
        return R.ok0(list, (long) list.size());
    }

    @Override
    public PageInfo<User> findAllByOrganizeName(Integer organizeId, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> userMapper.findAllUserByOrganize(organizeId));
    }

    @Override
    public PageInfo<User> findSubmitted(Integer organizeId, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> userMapper.findSubmitted(organizeId));
    }

    @Override
    public PageInfo<User> findUnpaid(Integer organizeId, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> userMapper.findUnpaid(organizeId));
    }

    @Override
    public Integer findSubmittedCount(Integer organizeId) {
        return userMapper.findSubmittedCount(organizeId);
    }

    @Override
    public Integer findUnpaidCount(Integer organizeId) {
        return userMapper.findUnpaidCount(organizeId);
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

    @Override
    public Integer update(User user) {
        return userMapper.update(user);
    }

    @Override
    public Integer delete(String userId) {
        return userMapper.delete(userId);
    }

}
