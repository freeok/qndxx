package work.pcdd.qndxx.util;

import lombok.Data;
import work.pcdd.qndxx.common.RCode;

import java.io.Serializable;

/**
 * @author pcdd
 */
@Data
public class R<T> implements Serializable {

    private Integer code;
    private String msg;
    private transient T data;
    private RCode rCode;
    private Long count;

    /**
     * @return 返回成功
     */
    public static <T> R<T> ok() {
        return R.ok(null);
    }

    /**
     * @param data 返回的数据
     * @return 返回成功
     */
    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(RCode.SUCCESS.getCode());
        r.setMsg(RCode.SUCCESS.getMessage());
        r.setData(data);
        return r;
    }

    /**
     * 返回layui表格指定格式的格式(code为0)
     *
     * @param data 返回的数据
     * @return 返回成功
     */
    public static <T> R<T> ok0(T data, Long count) {
        R<T> r = new R<>();
        r.setCode(RCode.SUCCESS_LAYUI.getCode());
        r.setMsg(RCode.SUCCESS.getMessage());
        r.setData(data);
        r.setCount(count);
        return r;
    }

    /**
     * 返回失败
     *
     * @param rCode 状态码
     * @return 响应结果
     */
    public static <T> R<T> fail(RCode rCode) {
        return R.fail(rCode, null);
    }

    /**
     * 返回失败
     *
     * @param rCode 状态码
     * @param data  返回的数据
     * @return 响应结果
     */
    public static <T> R<T> fail(RCode rCode, T data) {
        R<T> r = new R<>();
        r.setCode(rCode.getCode());
        r.setMsg(rCode.getMessage());
        r.setData(data);
        r.setRCode(rCode);
        return r;
    }

}
