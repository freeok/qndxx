package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.service.AdminService;
import work.pcdd.qndxx.service.UserService;
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

    private final UserService userService;
    private final AdminService adminService;

    @Operation(summary = "无密码登录")
    @PostMapping("/user/login/{userId}")
    public R userLogin(@PathVariable String userId, HttpSession session) {
        return userService.unSafeLogin(userId, session);
    }

    @Operation(summary = "用户登出")
    @GetMapping("/user/logout")
    public void userLogout(HttpSession session) {
        session.removeAttribute("user");
    }

    @Operation(summary = "管理员登录")
    @PostMapping("/admin/login/{userId}/{pwd}")
    public R<String> adminLogin(@Parameter(name = "学号") @PathVariable String userId, @Parameter(name = "密码") @PathVariable String pwd, HttpSession session) {
        return adminService.login(userId, pwd, session);
    }

    @Operation(summary = "管理员登出")
    @GetMapping("/admin/logout")
    public void adminLogout(HttpSession session) {
        session.removeAttribute("admin");
        session.removeAttribute("organize");
    }

}
