package work.pcdd.qndxx.mapper;

import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author pcdd
 */
public interface EchartsMapper {

    @MapKey("hour_start")
    List<Map<String, String>> getSubmitEcharts();

}
