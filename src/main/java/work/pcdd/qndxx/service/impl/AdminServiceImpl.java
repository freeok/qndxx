package work.pcdd.qndxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.mapper.AdminMapper;
import work.pcdd.qndxx.service.AdminService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author pcdd
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Override
    public R<String> login(String stuId, String pwd, HttpSession session) {
        Student student = new Student();
        student.setStuId(stuId);
        student.setPwd(pwd);
        Student obj = adminMapper.login(student);
        if (obj != null
                && Objects.equals(obj.getStuId(), stuId)
                && Objects.equals(obj.getPwd(), pwd)) {
            session.setAttribute("admin", obj);
            return R.ok("管理员登录成功");
        }
        return R.fail(RCode.USER_LOGIN_ERROR);
    }

    @Override
    public PageInfo<Student> findAllByClazzName(String clazzName, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> adminMapper.findAllByClazzName(clazzName));
    }

    @Override
    public PageInfo<Student> findSubmitted(String clazzName, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> adminMapper.findSubmitted(clazzName));
    }

    @Override
    public PageInfo<Student> findUnpaid(String clazzName, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> adminMapper.findUnpaid(clazzName));
    }

    @Override
    public Integer findSubmittedCount(String clazzName) {
        return adminMapper.findSubmittedCount(clazzName);
    }

    @Override
    public Integer findUnpaidCount(String clazzName) {
        return adminMapper.findUnpaidCount(clazzName);
    }

    @Override
    public R updPwd(String oldPwd, String newPwd, HttpSession session) {
        Student admin = (Student) session.getAttribute("admin");
        String stuId = admin.getStuId();
        Student student = new Student();
        student.setStuId(stuId);
        student.setPwd(oldPwd);
        // 旧密码错误，拒绝修改
        if (adminMapper.login(student) == null) {
            return R.fail(RCode.ADMIN_OLD_PASSWORD_ERROR);
        }
        student.setPwd(newPwd);
        if (adminMapper.updPwd(student) == 1) {
            return R.ok();
        }

        return R.fail(RCode.ADMIN_UPDATE_PASSWORD_FAIL);
    }

}
