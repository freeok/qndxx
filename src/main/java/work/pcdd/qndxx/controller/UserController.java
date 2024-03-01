package work.pcdd.qndxx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.entity.User;
import work.pcdd.qndxx.mapper.UserMapper;
import work.pcdd.qndxx.service.UserService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;


/**
 * @author pcdd
 * created by 2021/1/6
 */
@Tag(name = "用户相关 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "模糊查询用户")
    @GetMapping("/user/fuzzy-query")
    public R fuzzyQuery(@RequestParam String userId, @RequestParam String username) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        return userService.fuzzyQuery(user);
    }

    @Operation(summary = "查询当前组织的所有用户")
    @GetMapping("/user/list")
    public R findAllByOrganizeName(@RequestParam int page, @RequestParam int limit, HttpSession session) {
        // 从session中取出当前管理员所在的组织作为参数传递
        User admin = (User) session.getAttribute("admin");
        PageInfo<Object> pageInfo = PageHelper.startPage(page, limit)
                .doSelectPageInfo(() -> userMapper.list(admin.getOrganizeId()));
        return R.ok0(pageInfo.getList(), pageInfo.getTotal());
    }

    @Operation(summary = "查询截图上交情况")
    @GetMapping("/user/upload-details")
    public R getSubmitDetails(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit, HttpSession session) {
        User admin = (User) session.getAttribute("admin");
        Integer organizeId = admin.getOrganizeId();
        PageInfo<Object> pageInfo = PageHelper.startPage(page, limit)
                .doSelectPageInfo(() -> userMapper.getUploadDetails(organizeId));
        return R.ok0(pageInfo.getList(), pageInfo.getTotal());
    }

    @Operation(summary = "查询截图已交人数")
    @GetMapping("/user/findSubmittedCount")
    public R<Integer> findSubmittedCount(@RequestParam Integer organizeId) {
        return R.ok(userMapper.findSubmittedCount(organizeId));
    }

    @Operation(summary = "添加用户")
    @PostMapping("/user/add/{userId}/{username}/{organizeName}")
    public R add(@PathVariable String userId, @PathVariable String username, @PathVariable String organizeName) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setOrganizeName(organizeName);
        return R.ok(userService.save(user));
    }

    @Operation(summary = "更新用户信息")
    @PutMapping("/user/update")
    public R update(@RequestParam String userId, @RequestParam String oldId, @RequestParam String username) {
        User user = new User();
        user.setUserId(userId);
        user.setOldId(oldId);
        user.setUsername(username);
        return R.ok(userService.saveOrUpdate(user));
    }

    @Operation(summary = "修改管理员密码")
    @PutMapping("/user/password/{oldPwd}/{newPwd}")
    public R updPwd(@PathVariable String oldPwd, @PathVariable String newPwd, HttpSession session) {
        return userService.updPwd(oldPwd, newPwd, session);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/user/delete/{userId}")
    public R delete(@PathVariable String userId) {
        return R.ok(userService.removeById(userId));
    }

}
