package work.pcdd.qndxx.common;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author pcdd
 */
@Data
@Component
public class R implements Serializable {

    private Integer code;
    private String message;
    private Object data;
    private RCode rCode;
    private Long count;

    /**
     * @return 返回成功
     */
    public static R success() {
        return R.success(null);
    }

    /**
     * @param data 返回的数据
     * @return 返回成功
     */
    public static R success(Object data) {
        R r = new R();
        r.setCode(RCode.SUCCESS.getCode());
        r.setMessage(RCode.SUCCESS.getMessage());
        r.setData(data);
        r.setRCode(RCode.SUCCESS);
        return r;
    }

    /**
     * 返回layui表格指定格式的格式(code为0)
     *
     * @param data 返回的数据
     * @return 返回成功
     */
    public static R success0(Object data, Long count) {
        R r = new R();
        r.setCode(RCode.SUCCESS_LAYUI.getCode());
        r.setMessage(RCode.SUCCESS.getMessage());
        r.setCount(count);
        r.setData(data);
        return r;
    }

    /**
     * 返回失败
     *
     * @param rCode 状态码
     * @return 响应结果
     */
    public static R failure(RCode rCode) {
        return R.failure(rCode, null);
    }

    /**
     * 返回失败
     *
     * @param rCode 状态码
     * @param data       返回的数据
     * @return 响应结果
     */
    public static R failure(RCode rCode, Object data) {
        R r = new R();
        r.setCode(rCode.getCode());
        r.setMessage(rCode.getMessage());
        r.setRCode(rCode);
        r.setData(data);
        return r;
    }

    public static R failure(Integer code, Object data) {
        R r = new R();
        r.setCode(code);
        r.setData(data);
        return r;
    }

}
