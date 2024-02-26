package work.pcdd.qndxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.pcdd.qndxx.mapper.EchartsMapper;
import work.pcdd.qndxx.util.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pcdd
 */
@Tag(name = "ECharts 相关 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class EchartsController {

    private final EchartsMapper echartsMapper;

    @Operation(summary = "获取提交时间折线图数据")
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
        Map<String, List<Object>> map = new LinkedHashMap<>();
        map.put("x", xList);
        map.put("y", yList);
        return R.ok(map);
    }

}
