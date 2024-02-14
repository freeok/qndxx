package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.common.util.R;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.service.StudentService;

import javax.servlet.http.HttpSession;


/**
 * @author pcdd
 * created by 2021/1/6
 */
@Tag(name = "学生相关API")
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "用户无密码登录")
    @PostMapping("/login/{stuId}")
    public R unSafeLogin(@PathVariable String stuId, HttpSession session) {
        return studentService.unSafeLogin(stuId, session);
    }

    @Operation(summary = "判断用户是否登录")
    @PostMapping("/isLogin")
    public R isLogin(HttpSession session) {
        if (session.getAttribute("student") == null) {
            return R.fail(RCode.USER_NOT_LOGGED_IN);
        }
        return R.ok();
    }

    @Operation(summary = "用户注销")
    @GetMapping("/logout")
    public R<String> logout(HttpSession session) {
        session.removeAttribute("student");
        return R.ok("退出登录成功");
    }

    @Operation(summary = "添加用户")
    @PostMapping("/add/{stuId}/{stuName}/{clazzName}")
    public R addStudent(@PathVariable String stuId, @PathVariable String stuName, @PathVariable String clazzName) {
        Student student = new Student();
        student.setStuId(stuId);
        student.setStuName(stuName);
        student.setClazzName(clazzName);
        return studentService.addStudent(student);
    }

    @Operation(summary = "根据学号删除用户")
    @DeleteMapping("/delById/{stuId}")
    public R<Integer> delStudentById(@PathVariable String stuId) {
        return R.ok(studentService.delStudentById(stuId));
    }

    @Operation(summary = "在指定的班级中根据姓名模糊查询学生")
    @GetMapping("/findByIdOrName/{stuName}/{clazzName}")
    public R findByIdOrName(@PathVariable String stuName, @PathVariable String clazzName) {
        Student student = new Student();
        student.setStuName(stuName);
        student.setClazzName(clazzName);
        return studentService.findByName(student);
    }

    @Operation(summary = "更新学生信息")
    @PutMapping("/updStudentById/{stuId}/{stuName}/{clazzName}/{pwd}/{role}")
    public R<Integer> updStudentById(@PathVariable String stuId
            , @PathVariable String stuName
            , @PathVariable String clazzName
            , @PathVariable String pwd
            , @PathVariable String role) {
        Student student = new Student();
        student.setStuId(stuId);
        student.setStuName(stuName);
        student.setClazzName(clazzName);
        student.setPwd(pwd);
        student.setRole(role);
        return R.ok(studentService.updStudentById(student));
    }

}
