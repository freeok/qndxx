package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.service.UserService;
import work.pcdd.qndxx.util.R;


/**
 * @author pcdd
 * created by 2021/1/6
 */
@Tag(name = "学生相关API")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "模糊查询用户")
    @GetMapping("/findByIdOrName/{username}/{clazzName}")
    public R findByIdOrName(@PathVariable String username, @PathVariable String clazzName) {
        User user = new User();
        user.setUsername(username);
        user.setClazzName(clazzName);
        return userService.findByName(user);
    }

    @Operation(summary = "添加用户")
    @PostMapping("/add/{userId}/{username}/{clazzName}")
    public R add(@PathVariable String userId, @PathVariable String username, @PathVariable String clazzName) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setClazzName(clazzName);
        return userService.add(user);
    }

    @Operation(summary = "更新用户信息")
    @PutMapping("/update/{userId}/{username}/{clazzName}/{pwd}/{role}")
    public R<Integer> update(@PathVariable String userId
            , @PathVariable String username
            , @PathVariable String clazzName
            , @PathVariable String pwd
            , @PathVariable String role) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setClazzName(clazzName);
        user.setPwd(pwd);
        user.setRole(role);
        return R.ok(userService.update(user));
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/delById/{userId}")
    public R<Integer> delete(@PathVariable String userId) {
        return R.ok(userService.delete(userId));
    }

}
