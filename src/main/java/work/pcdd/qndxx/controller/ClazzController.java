package work.pcdd.qndxx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.common.util.R;
import work.pcdd.qndxx.entity.Clazz;
import work.pcdd.qndxx.service.ClazzService;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
@Api(tags = "班级相关API")
@RestController
@RequestMapping("/clazz")
@RequiredArgsConstructor
public class ClazzController {

    private final ClazzService clazzService;

    @ApiOperation("获取clazz表字段")
    @GetMapping("/{clazzName}")
    public R<Clazz> getClazz(@PathVariable("clazzName") String clazzName, HttpSession session) {
        return R.ok(clazzService.getClazz(clazzName, session));
    }

    @ApiOperation("根据班级修改季数和期数")
    @PutMapping("/issue/{season}/{period}/{clazzName}")
    public R<Integer> updateIssue(@PathVariable String season, @PathVariable String period, @PathVariable String clazzName) {
        Clazz clazz = new Clazz();
        clazz.setSeason(season);
        clazz.setPeriod(period);
        clazz.setClazzName(clazzName);
        return R.ok(clazzService.updateIssue(clazz));
    }

    @ApiOperation("根据班级修改系统状态")
    @PutMapping("/isEnable/{isEnable}/{clazzName}")
    public R<Integer> updateIsEnable(@PathVariable boolean isEnable, @PathVariable String clazzName, HttpSession session) {
        Clazz clazz = new Clazz();
        clazz.setIsEnable(isEnable);
        clazz.setClazzName(clazzName);
        return R.ok(clazzService.updateIsEnable(clazz, session));
    }

}
