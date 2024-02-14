package work.pcdd.qndxx.controller;

import cn.hutool.core.codec.Base64;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work.pcdd.qndxx.common.util.R;
import work.pcdd.qndxx.entity.Upload;
import work.pcdd.qndxx.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;


/**
 * @author pcdd
 */
@Api(tags = "图片相关API")
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @ApiOperation("图片上传")
    @PostMapping("/upload/{id}/{name}/{par}/{clazzName}")
    public R upload(@RequestParam("file") MultipartFile mf
            , @PathVariable("id") String id
            , @PathVariable("name") String name
            , @PathVariable("par") String par
            , @PathVariable("clazzName") String clazzName) {
        return imageService.upload(id, name, par, clazzName, mf);
    }

    @ApiOperation("图片下载")
    @GetMapping("/download/{clazzName}")
    public void download(HttpServletRequest req, HttpServletResponse resp, @PathVariable String clazzName) {
        imageService.download(req, resp, clazzName);
    }

    @ApiOperation("判断用户是否上传")
    @GetMapping("/isUploaded/{stuId}")
    public R<List<Upload>> isUploaded(@PathVariable String stuId) {
        return R.ok(imageService.isUploaded(stuId));
    }

    @ApiOperation("根据图片路径返回图片的base64编码")
    @PostMapping("/base64")
    public R<String> getImageBase64(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json;charset=utf-8");

        String parentPath = System.getProperty("user.dir") + "/src/main/resources/static";
        String path = parentPath + req.getParameter("path");
        // use hutool
        String base64 = "data:image/jpg;base64," + Base64.encode(new File(path));

        return R.ok(base64);
    }

    @PostMapping("/empty")
    public String empty() {
        return "空api,防报405，无实际作用";
    }

}
