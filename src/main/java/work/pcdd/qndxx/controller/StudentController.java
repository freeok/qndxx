package work.pcdd.qndxx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.common.R;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.service.StudentService;

import javax.servlet.http.HttpSession;


/**
 * @author pcdd
 * created by 2021/1/6
 */
@Api(tags = "学生相关API")
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @ApiOperation("用户无密码登录")
    @PostMapping("/login/{stuId}")
    public R login(@PathVariable String stuId, HttpSession session) {
        return studentService.unSafeLogin(stuId, session);
    }

    @ApiOperation("判断用户是否登录")
    @PostMapping("/isLogin")
    public R isLogin(HttpSession session) {
        if (session.getAttribute("student") == null) {
            return R.failure(RCode.USER_NOT_LOGGED_IN);
        }
        return R.success();
    }

    @ApiOperation("用户注销")
    @GetMapping("/logout")
    public R logout(HttpSession session) {
        session.removeAttribute("student");
        return R.success();
    }

    @ApiOperation("添加用户")
    @PostMapping("/add/{stuId}/{stuName}/{clazzName}")
    public R addStudent(@PathVariable String stuId, @PathVariable String stuName, @PathVariable String clazzName) {
        Student student = new Student();
        student.setStuId(stuId);
        student.setStuName(stuName);
        student.setClazzName(clazzName);
        return studentService.addStudent(student);
    }

    @ApiOperation("根据学号删除用户")
    @DeleteMapping("/delById/{stuId}")
    public R delStudentById(@PathVariable String stuId) {
        return studentService.delStudentById(stuId);
    }

    @ApiOperation("在指定的班级中根据姓名模糊查询学生")
    @GetMapping("/findByIdOrName/{stuName}/{clazzName}")
    public R findByIdOrName(@PathVariable String stuName, @PathVariable String clazzName) {
        Student student = new Student();
        student.setStuName(stuName);
        student.setClazzName(clazzName);
        return studentService.findByName(student);
    }

    @ApiOperation("更新学生信息")
    @PutMapping("/updStudentById/{stuId}/{stuName}/{clazzName}/{pwd}/{role}")
    public R updStudentById(@PathVariable String stuId
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
        return studentService.updStudentById(student);
    }

}
