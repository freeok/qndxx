package work.pcdd.qndxx.controller;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.common.util.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pcdd
 * created by 2021/2/18 4:05
 */
@Api(tags = "验证码相关API")
@RestController
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
    public R verify(@PathVariable("yzm") String yzm, HttpServletRequest request) {
        if (HappyCaptcha.verification(request, yzm, true)) {
            HappyCaptcha.remove(request);
            return R.ok();
        }
        return R.fail(RCode.CAPTCHA_ERROR);
    }

}
