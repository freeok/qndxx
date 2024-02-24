package work.pcdd.qndxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.mapper.AdminMapper;
import work.pcdd.qndxx.service.AdminService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author pcdd
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Override
    public R<String> login(String userId, String pwd, HttpSession session) {
        User user = new User();
        user.setUserId(userId);
        user.setPwd(pwd);
        User obj = adminMapper.login(user);
        if (obj != null
                && Objects.equals(obj.getUserId(), userId)
                && Objects.equals(obj.getPwd(), pwd)) {
            session.setAttribute("admin", obj);
            return R.ok("管理员登录成功");
        }
        return R.fail(RCode.USER_LOGIN_ERROR);
    }

    @Override
    public PageInfo<User> findAllByorganizeName(String organizeName, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> adminMapper.findAllByorganizeName(organizeName));
    }

    @Override
    public PageInfo<User> findSubmitted(String organizeName, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> adminMapper.findSubmitted(organizeName));
    }

    @Override
    public PageInfo<User> findUnpaid(String organizeName, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> adminMapper.findUnpaid(organizeName));
    }

    @Override
    public Integer findSubmittedCount(String organizeName) {
        return adminMapper.findSubmittedCount(organizeName);
    }

    @Override
    public Integer findUnpaidCount(String organizeName) {
        return adminMapper.findUnpaidCount(organizeName);
    }

    @Override
    public R updPwd(String oldPwd, String newPwd, HttpSession session) {
        User admin = (User) session.getAttribute("admin");
        String userId = admin.getUserId();
        User user = new User();
        user.setUserId(userId);
        user.setPwd(oldPwd);
        // 旧密码错误，拒绝修改
        if (adminMapper.login(user) == null) {
            return R.fail(RCode.ADMIN_OLD_PASSWORD_ERROR);
        }
        user.setPwd(newPwd);
        if (adminMapper.updPwd(user) == 1) {
            return R.ok();
        }

        return R.fail(RCode.ADMIN_UPDATE_PASSWORD_FAIL);
    }

    @Override
    public Map<String, List<Object>> getSubmitEcharts() {
        List<Object> xList = new ArrayList<>();
        List<Object> yList = new ArrayList<>();
        List<Map<String, String>> list = adminMapper.getSubmitEcharts();
        list.forEach(e -> {
            String hourStart = e.get("hour_start");
            String recordCount = String.valueOf(e.get("record_count"));
            xList.add(hourStart);
            yList.add(recordCount);
        });
        Map<String, List<Object>> map = new LinkedHashMap<>();
        map.put("x", xList);
        map.put("y", yList);
        return map;
    }

}
