package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.service.AdminService;
import work.pcdd.qndxx.service.StudentService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
@Tag(name = "登录相关 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final StudentService studentService;
    private final AdminService adminService;

    @Operation(summary = "无密码登录")
    @PostMapping("/user/login/{stuId}")
    public R userLogin(@PathVariable String stuId, HttpSession session) {
        return studentService.unSafeLogin(stuId, session);
    }

    @Operation(summary = "用户登出")
    @GetMapping("/user/logout")
    public void userLogout(HttpSession session) {
        session.removeAttribute("student");
    }

    @Operation(summary = "管理员登录")
    @PostMapping("/admin/login/{stuId}/{pwd}")
    public R<String> adminLogin(@Parameter(name = "学号") @PathVariable String stuId, @Parameter(name = "密码") @PathVariable String pwd, HttpSession session) {
        return adminService.login(stuId, pwd, session);
    }

    @Operation(summary = "管理员登出")
    @GetMapping("/admin/logout")
    public void adminLogout(HttpSession session) {
        session.removeAttribute("admin");
        session.removeAttribute("clazz");
    }

}
