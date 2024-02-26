package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import work.pcdd.qndxx.entity.Organize;
import work.pcdd.qndxx.service.OrganizeService;
import work.pcdd.qndxx.util.R;

/**
 * @author pcdd
 */
@Tag(name = "组织相关 API")
@RestController
@RequiredArgsConstructor
public class OrganizeController {

    private final OrganizeService organizeService;

    @Operation(summary = "查询一条组织信息")
    @GetMapping("/user/organize/{organizeId}")
    public R<Organize> getOne(@PathVariable("organizeId") Integer organizeId) {
        return R.ok(organizeService.getById(organizeId));
    }

    @Operation(summary = "更新数据")
    @PostMapping("/admin/organize/update")
    public R update(@RequestBody Organize organize) {
        return R.ok(organizeService.updateById(organize));
    }

}
