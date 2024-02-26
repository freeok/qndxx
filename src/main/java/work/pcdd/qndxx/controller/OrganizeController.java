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
@Tag(name = "组织相关 API")
@RestController
@RequiredArgsConstructor
public class OrganizeController {

    private final OrganizeService organizeService;

    @Operation(summary = "查询组织信息")
    @GetMapping("/user/organize/{organizeId}")
    public R<Organize> getOne(@PathVariable("organizeId") Integer organizeId, HttpSession session) {
        return R.ok(organizeService.getOne(organizeId, session));
    }

    @Operation(summary = "修改季数和期数")
    @PutMapping("/admin/organize/issue/{season}/{period}/{organizeName}")
    public R<Integer> updateIssue(@PathVariable Integer season, @PathVariable Integer period, @PathVariable String organizeName) {
        Organize organize = new Organize();
        organize.setSeason(season);
        organize.setPeriod(period);
        organize.setOrganizeName(organizeName);
        return R.ok(organizeService.updateIssue(organize));
    }

    @Operation(summary = "开启/关闭系统")
    @PutMapping("/admin/organize/action")
    public R<Integer> updateIsEnable(@RequestParam Boolean isEnable, @RequestParam Integer organizeId, HttpSession session) {
        Organize organize = new Organize();
        organize.setId(organizeId);
        organize.setIsEnable(isEnable);
        return R.ok(organizeService.updateIsEnable(organize, session));
    }

}
