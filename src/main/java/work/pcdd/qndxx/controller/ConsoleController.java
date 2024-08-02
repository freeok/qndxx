package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.pcdd.qndxx.entity.Organize;
import work.pcdd.qndxx.mapper.EchartsMapper;
import work.pcdd.qndxx.mapper.UserMapper;
import work.pcdd.qndxx.service.OrganizeService;
import work.pcdd.qndxx.util.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pcdd
 */
@Tag(name = "控制台相关 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ConsoleController {

    private final OrganizeService organizeService;
    private final UserMapper userMapper;
    private final EchartsMapper echartsMapper;

    @Operation(summary = "数据一览 > 数据统计")
    @GetMapping("/statistics")
    public R<Map<String, Object>> getStatistics(@RequestParam Integer organizeId) {
        Organize organize = organizeService.getById(organizeId);
        int submittedCount = userMapper.findSubmittedCount(organizeId);
        int unpaidCount = userMapper.findUnpaidCount(organizeId);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("organizeName", organize.getOrganizeName());
        map.put("season", organize.getSeason());
        map.put("period", organize.getPeriod());
        map.put("submittedCount", submittedCount);
        map.put("unpaidCount", unpaidCount);
        return R.ok(map);
    }

    @Operation(summary = "提交时间折线图数据")
    @GetMapping("/submit-echarts")
    public R getSubmitEcharts() {
        List<Object> xList = new ArrayList<>();
        List<Object> yList = new ArrayList<>();
        List<Map<String, String>> list = echartsMapper.getSubmitEcharts();
        list.forEach(e -> {
            String hourStart = e.get("hour_start");
            String recordCount = String.valueOf(e.get("record_count"));
            xList.add(hourStart);
            yList.add(recordCount);
        });
        // TODO 封装 VO
        Map<String, List<Object>> map = new LinkedHashMap<>();
        map.put("x", xList);
        map.put("y", yList);
        return R.ok(map);
    }

}
