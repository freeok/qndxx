package work.pcdd.qndxx.common.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author AD
 */
@Data
@Component
public class Result implements Serializable {
    private Integer code;
    private String message;
    private Object data;
    private ResultCode resultCode;
    private Long count;

    /**
     * @return 返回成功
     */
    public static Result success() {
        return Result.success(null);
    }

    /**
     * @param data 返回的数据
     * @return 返回成功
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 返回layui表格指定格式的格式(code为0)
     *
     * @param data 返回的数据
     * @return 返回成功
     */
    public static Result success0(Object data, Long count) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS_LAYUI.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setCount(count);
        result.setData(data);
        return result;
    }

    /**
     * 返回失败
     *
     * @param resultCode 状态码
     * @return 响应结果
     */
    public static Result failure(ResultCode resultCode) {
        return Result.failure(resultCode, null);
    }

    /**
     * 返回失败
     *
     * @param resultCode 状态码
     * @param data       返回的数据
     * @return 响应结果
     */
    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static Result failure(Integer code, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setData(data);
        return result;
    }

}
