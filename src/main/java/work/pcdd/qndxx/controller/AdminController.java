package work.pcdd.qndxx.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.service.AdminService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author pcdd
 */
@Tag(name = "管理员相关API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "根据组织名查询所有学生")
    @GetMapping("/findAllByOrganizeName")
    public R<List<User>> findAllByOrganizeName(@RequestParam int page, @RequestParam int limit, HttpSession session) {
        // 从session中取出当前管理员所在的组织作为参数传递
        User admin = (User) session.getAttribute("admin");
        PageInfo<User> pageInfo = adminService.findAllByorganizeName(admin.getOrganizeName(), page, limit);
        return R.ok0(pageInfo.getList(), pageInfo.getTotal());
    }

    @Operation(summary = "查询截图已交信息")
    @GetMapping("/findSubmitted")
    public R<List<User>> findSubmitted(@RequestParam int page, @RequestParam int limit, HttpSession session) {
        User admin = (User) session.getAttribute("admin");
        PageInfo<User> pageInfo = adminService.findSubmitted(admin.getOrganizeName(), page, limit);
        return R.ok0(pageInfo.getList(), pageInfo.getTotal());
    }

    @Operation(summary = "查询截图未交信息")
    @GetMapping("/findUnpaid")
    public R<List<User>> findUnpaid(@RequestParam int page, @RequestParam int limit, HttpSession session) {
        User admin = (User) session.getAttribute("admin");
        PageInfo<User> pageInfo = adminService.findUnpaid(admin.getOrganizeName(), page, limit);
        return R.ok0(pageInfo.getList(), pageInfo.getTotal());
    }

    @Operation(summary = "查询截图已交人数")
    @GetMapping("/findSubmittedCount/{organizeName}")
    public R<Integer> findSubmittedCount(@PathVariable("organizeName") String organizeName) {
        return R.ok(adminService.findSubmittedCount(organizeName));
    }

    @Operation(summary = "查询截图未交人数")
    @GetMapping("/findUnpaidCount/{organizeName}")
    public R<Integer> findUnpaidCount(@PathVariable("organizeName") String organizeName) {
        return R.ok(adminService.findUnpaidCount(organizeName));
    }

    @Operation(summary = "修改管理员密码")
    @PutMapping("/password/{oldPwd}/{newPwd}")
    public R updPwd(@PathVariable String oldPwd, @PathVariable String newPwd, HttpSession session) {
        return adminService.updPwd(oldPwd, newPwd, session);
    }

}
