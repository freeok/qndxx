package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.entity.Organize;
import work.pcdd.qndxx.service.OrganizeService;
import work.pcdd.qndxx.util.R;

import javax.servlet.http.HttpSession;

/**
 * @author pcdd
 */
@Tag(name = "组织相关API")
@RestController
@RequestMapping("/organize")
@RequiredArgsConstructor
public class OrganizeController {

    private final OrganizeService organizeService;

    @Operation(summary = "查询组织信息")
    @GetMapping("/{organizeName}")
    public R<Organize> getOne(@PathVariable("organizeName") String organizeName, HttpSession session) {
        return R.ok(organizeService.getOne(organizeName, session));
    }

    @Operation(summary = "修改季数和期数")
    @PutMapping("/issue/{season}/{period}/{organizeName}")
    public R<Integer> updateIssue(@PathVariable String season, @PathVariable String period, @PathVariable String organizeName) {
        Organize organize = new Organize();
        organize.setSeason(season);
        organize.setPeriod(period);
        organize.setOrganizeName(organizeName);
        return R.ok(organizeService.updateIssue(organize));
    }

    @Operation(summary = "开启/关闭系统")
    @PutMapping("/isEnable/{isEnable}/{organizeName}")
    public R<Integer> updateIsEnable(@PathVariable boolean isEnable, @PathVariable String organizeName, HttpSession session) {
        Organize organize = new Organize();
        organize.setIsEnable(isEnable);
        organize.setOrganizeName(organizeName);
        return R.ok(organizeService.updateIsEnable(organize, session));
    }

}
