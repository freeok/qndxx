package work.pcdd.qndxx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.common.R;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.service.AdminService;
import work.pcdd.qndxx.service.ImageService;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
@Api(tags = "管理员相关API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ImageService imageService;

    @ApiOperation("管理员登录")
    @PostMapping("/login/{stuId}/{pwd}")
    public R login(@ApiParam("学号") @PathVariable String stuId, @ApiParam("密码") @PathVariable String pwd, HttpSession session) {
        return adminService.login(stuId, pwd, session);
    }

    @ApiOperation("管理员注销")
    @GetMapping("/logout")
    public R logout(HttpSession session) {
        session.removeAttribute("admin");
        session.removeAttribute("clazz");
        return R.success();
    }

    @ApiOperation("根据班级名查询所有学生的学号，姓名，班级")
    @GetMapping("/findAllByClazzName/{start}/{limit}")
    public R findAllByClazzName(@PathVariable int start, @PathVariable int limit, HttpSession session) {
        // 从session中取出当前管理员所在的班级作为参数传递
        Student admin = (Student) session.getAttribute("admin");
        return adminService.findAllByClazzName(admin.getClazzName(), start, limit);
    }

    @ApiOperation("查询截图已交人员的学号、姓名、首次上传时间")
    @GetMapping("/findSubmitted/{start}/{limit}")
    public R findSubmitted(@PathVariable int start, @PathVariable int limit, HttpSession session) {
        Student admin = (Student) session.getAttribute("admin");
        return adminService.findSubmitted(admin.getClazzName(), start, limit);
    }

    @ApiOperation("根据班级查询截图未交人员的学号、姓名")
    @GetMapping("/findUnpaid/{start}/{limit}")
    public R findUnpaid(@PathVariable int start, @PathVariable int limit, HttpSession session) {
        Student admin = (Student) session.getAttribute("admin");
        return adminService.findUnpaid(admin.getClazzName(), start, limit);
    }

    @ApiOperation("根据班级查询不同班级的截图已交人数")
    @GetMapping("/findSubmittedCount/{clazzName}")
    public R findSubmittedCount(@PathVariable("clazzName") String clazzName) {
        return adminService.findSubmittedCount(clazzName);
    }

    @ApiOperation("查询不同班级的截图未交人数")
    @GetMapping("/findUnpaidCount/{clazzName}")
    public R findUnpaidCount(@PathVariable("clazzName") String clazzName) {
        return adminService.findUnpaidCount(clazzName);
    }

    @ApiOperation("结束某一班级本轮提交")
    @DeleteMapping("/reset/{clazzName}")
    public R deleteUpload(@PathVariable String clazzName) {
        return imageService.deleteUpload(clazzName);
    }

    @ApiOperation("管理员修改密码")
    @PutMapping("/password/{oldPwd}/{newPwd}")
    public R updPwd(@PathVariable String oldPwd, @PathVariable String newPwd, HttpSession session) {
        return adminService.updPwd(oldPwd, newPwd, session);
    }

}
