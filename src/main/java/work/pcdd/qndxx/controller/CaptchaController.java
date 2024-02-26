package work.pcdd.qndxx.controller;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pcdd
 * created by 2021/2/18 4:05
 */
@Tag(name = "验证码相关 API")
@RestController
public class CaptchaController {

    @Operation(summary = "生成动态验证码")
    @GetMapping("/user/captcha/generate")
    public void generate(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request, response)
                .style(CaptchaStyle.ANIM)
                .type(CaptchaType.DEFAULT)
                .build().finish();
    }

    @Operation(summary = "验证码校验")
    @PostMapping("/user/captcha/verify")
    public R verify(@RequestParam("captcha") String captcha, HttpServletRequest request) {
        if (HappyCaptcha.verification(request, captcha, true)) {
            HappyCaptcha.remove(request);
            return R.ok();
        }
        return R.fail(RCode.CAPTCHA_ERROR);
    }

}
