package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import work.pcdd.qndxx.service.AuthService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
@Tag(name = "登录相关 API")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "无密码登录")
    @PostMapping("/user/auth/login/{userId}")
    public R userLogin(@PathVariable String userId, HttpSession session) {
        return authService.unSafeLogin(userId, session);
    }

    @Operation(summary = "用户登出")
    @GetMapping("/user/auth/logout")
    public void userLogout(HttpSession session) {
        session.removeAttribute("user");
    }

    @Operation(summary = "管理员登录")
    @PostMapping("/admin/auth/login/{userId}/{pwd}")
    public R<String> adminLogin(@Parameter(name = "账号") @PathVariable String userId, @Parameter(name = "密码") @PathVariable String pwd, HttpSession session) {
        return authService.login(userId, pwd, session);
    }

    @Operation(summary = "管理员登出")
    @GetMapping("/admin/auth/logout")
    public void adminLogout(HttpSession session) {
        session.removeAttribute("admin");
        session.removeAttribute("organize");
    }

}
