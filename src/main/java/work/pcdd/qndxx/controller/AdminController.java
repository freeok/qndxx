package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.common.util.R;
import work.pcdd.qndxx.entity.Student;
import work.pcdd.qndxx.service.AdminService;
import work.pcdd.qndxx.service.ImageService;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
@Tag(name = "管理员相关API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ImageService imageService;

    @Operation(summary = "管理员注销")
    @GetMapping("/findAllByClazzName/{start}/{limit}")
    public R findAllByClazzName(@PathVariable int start, @PathVariable int limit, HttpSession session) {
        // 从session中取出当前管理员所在的班级作为参数传递
        Student admin = (Student) session.getAttribute("admin");
        return adminService.findAllByClazzName(admin.getClazzName(), start, limit);
    }

    @Operation(summary = "管理员注销")
    @GetMapping("/findSubmitted/{start}/{limit}")
    public R findSubmitted(@PathVariable int start, @PathVariable int limit, HttpSession session) {
        Student admin = (Student) session.getAttribute("admin");
        return adminService.findSubmitted(admin.getClazzName(), start, limit);
    }

    @Operation(summary = "管理员注销")
    @GetMapping("/findUnpaid/{start}/{limit}")
    public R findUnpaid(@PathVariable int start, @PathVariable int limit, HttpSession session) {
        Student admin = (Student) session.getAttribute("admin");
        return adminService.findUnpaid(admin.getClazzName(), start, limit);
    }

    @Operation(summary = "根据班级查询不同班级的截图已交人数")
    @GetMapping("/findSubmittedCount/{clazzName}")
    public R<Integer> findSubmittedCount(@PathVariable("clazzName") String clazzName) {
        return R.ok(adminService.findSubmittedCount(clazzName));
    }

    @Operation(summary = "查询不同班级的截图未交人数")
    @GetMapping("/findUnpaidCount/{clazzName}")
    public R<Integer> findUnpaidCount(@PathVariable("clazzName") String clazzName) {
        return R.ok(adminService.findUnpaidCount(clazzName));
    }

    @Operation(summary = "结束某一班级本轮提交")
    @DeleteMapping("/reset/{clazzName}")
    public R deleteUpload(@PathVariable String clazzName) {
        imageService.deleteUpload(clazzName);
        return R.ok();
    }

    @Operation(summary = "管理员修改密码")
    @PutMapping("/password/{oldPwd}/{newPwd}")
    public R updPwd(@PathVariable String oldPwd, @PathVariable String newPwd, HttpSession session) {
        return adminService.updPwd(oldPwd, newPwd, session);
    }

}
