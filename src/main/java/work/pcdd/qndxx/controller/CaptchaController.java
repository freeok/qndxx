package work.pcdd.qndxx.controller;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.common.vo.Result;
import work.pcdd.qndxx.common.vo.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 1907263405@qq.com
 * @date 2021/2/18 4:05
 */
@Api(tags = "验证码相关API")
@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    @ApiOperation("生成动态验证码")
    @GetMapping("/getYzm")
    public void happyCaptcha(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request, response)
                //.font(Fonts.getInstance().enFont1())
                .style(CaptchaStyle.ANIM)
                .type(CaptchaType.DEFAULT)
                .build().finish();
    }

    @ApiOperation("验证码校验")
    @PostMapping("/verify/{yzm}")
    @ResponseBody
    public Result verify(@PathVariable("yzm") String yzm, HttpServletRequest request) {
        if (HappyCaptcha.verification(request, yzm, true)) {
            HappyCaptcha.remove(request);
            return Result.success();
        }
        return Result.failure(ResultCode.CAPTCHA_ERROR);
    }
}
