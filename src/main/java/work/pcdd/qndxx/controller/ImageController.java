package work.pcdd.qndxx.controller;

import cn.hutool.core.codec.Base64;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.entity.Image;
import work.pcdd.qndxx.common.dto.FileUploadDTO;
import work.pcdd.qndxx.service.ImageService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;


/**
 * @author pcdd
 */
@Tag(name = "图片相关 API")
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "图片上传")
    @PostMapping("/user/image/upload")
    public R upload(FileUploadDTO dto) {
        return imageService.upload(dto);
    }

    @Operation(summary = "图片下载")
    @GetMapping("/admin/image/download")
    public void download(HttpServletResponse resp, @RequestParam String organizeName) {
        imageService.download(resp, organizeName);
    }

    @Operation(summary = "查询用上传记录")
    @GetMapping("/user/image/list/{userId}")
    public R<List<Image>> list(@PathVariable String userId) {
        LambdaQueryWrapper<Image> queryWrapper = new LambdaQueryWrapper<Image>().select().eq(Image::getUserId, userId);
        return R.ok(imageService.list(queryWrapper));
    }

    @Operation(summary = "根据图片路径返回图片的base64编码")
    @GetMapping("/user/image/base64")
    public R<String> getImageBase64(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json;charset=utf-8");

        String parentPath = System.getProperty("user.dir") + "/src/main/resources/static";
        String path = parentPath + req.getParameter("path");
        // use hutool
        String base64 = "data:image/jpg;base64," + Base64.encode(new File(path));

        return R.ok(base64);
    }

    @Operation(summary = "结束指定组织本轮提交")
    @DeleteMapping("/admin/image/reset/{organizeId}")
    public R reset(@PathVariable Integer organizeId) {
        imageService.reset(organizeId);
        return R.ok();
    }

    @PostMapping("/user/image/empty")
    public String empty() {
        return "空api,防报405，无实际作用";
    }

}
