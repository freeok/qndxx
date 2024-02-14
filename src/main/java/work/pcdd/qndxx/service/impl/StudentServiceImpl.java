package work.pcdd.qndxx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.common.util.R;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.mapper.StudentMapper;
import work.pcdd.qndxx.service.StudentService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author pcdd
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    @Override
    public R unSafeLogin(String stuId, HttpSession session) {
        Student student = studentMapper.findById(stuId);
        if (student != null && Objects.equals(student.getStuId(), stuId)) {
            session.setAttribute("student", student);
            return R.ok();
        }
        return R.fail(RCode.USER_LOGIN_ERROR);
    }

    @Override
    public R addStudent(Student student) {
        // 添加学生前判断学号是否存在，若存在，则拒绝添加
        if (studentMapper.findById(student.getStuId()) != null) {
            return R.fail(RCode.USER_HAS_EXISTED);
        }
        return R.ok(studentMapper.addStudent(student));
    }

    @Override
    public Integer delStudentById(String stuId) {
        return studentMapper.delStudentById(stuId);
    }

    @Override
    public R findByName(Student student) {
        List<Student> list = studentMapper.findByName(student);
        return R.ok0(list, (long) list.size());
    }

    @Override
    public Integer updStudentById(Student student) {
        return studentMapper.updStudentById(student);
    }

}
