package work.pcdd.qndxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.pcdd.qndxx.common.R;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.mapper.AdminMapper;
import work.pcdd.qndxx.service.AdminService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
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
    public R login(String stuId, String pwd, HttpSession session) {
        Student student = new Student();
        student.setStuId(stuId);
        student.setPwd(pwd);
        List<Student> list = adminMapper.login(student);
        if (list.size() == 1 && Objects.equals(list.get(0).getStuId(), stuId)
                && Objects.equals(list.get(0).getPwd(), pwd)) {
            session.setAttribute("admin", list.get(0));
            return R.success();
        }
        return R.failure(RCode.USER_LOGIN_ERROR);
    }

    @Override
    public R findAllByClazzName(String clazzName, int start, int limit) {
        // 分页助手，设置起始页和每页显示的条数
        PageHelper.startPage(start, limit);
        List<Student> list = adminMapper.findAllByClazzName(clazzName);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return R.success0(list, pageInfo.getTotal());
    }

    @Override
    public R findSubmitted(String clazzName, int start, int limit) {
        PageHelper.startPage(start, limit);
        List<Map<String, Object>> list = adminMapper.findSubmitted(clazzName);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return R.success0(list, pageInfo.getTotal());
    }

    @Override
    public R findUnpaid(String clazzName, int start, int limit) {
        PageHelper.startPage(start, limit);
        List<Student> list = adminMapper.findUnpaid(clazzName);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return R.success0(list, pageInfo.getTotal());
    }

    @Override
    public R findSubmittedCount(String clazzName) {
        return R.success(adminMapper.findSubmittedCount(clazzName));
    }

    @Override
    public R findUnpaidCount(String clazzName) {
        return R.success(adminMapper.findUnpaidCount(clazzName));
    }

    @Override
    public R updPwd(String oldPwd, String newPwd, HttpSession session) {
        Student admin = (Student) session.getAttribute("admin");
        String stuId = admin.getStuId();
        Student student = new Student();
        student.setStuId(stuId);
        student.setPwd(oldPwd);
        // 旧密码错误，拒绝修改
        if (adminMapper.login(student).isEmpty()) {
            return R.failure(RCode.ADMIN_OLD_PASSWORD_ERROR);
        }
        student.setPwd(newPwd);
        if (adminMapper.updPwd(student) == 1) {
            return R.success();
        }

        return R.failure(RCode.ADMIN_UPDATE_PASSWORD_FAIL);
    }

}
