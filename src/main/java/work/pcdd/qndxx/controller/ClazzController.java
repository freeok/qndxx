package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.util.R;
import work.pcdd.qndxx.entity.Clazz;
import work.pcdd.qndxx.service.ClazzService;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
@Tag(name = "班级相关API")
@RestController
@RequestMapping("/clazz")
@RequiredArgsConstructor
public class ClazzController {

    private final ClazzService clazzService;

    @Operation(summary = "查询班级信息")
    @GetMapping("/{clazzName}")
    public R<Clazz> getClazz(@PathVariable("clazzName") String clazzName, HttpSession session) {
        return R.ok(clazzService.getClazz(clazzName, session));
    }

}
