package work.pcdd.qndxx.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import work.pcdd.qndxx.common.vo.Result;

/**
 * @author pcdd
 * created by 2021/7/13 18:22
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handle(Exception e) {
        return Result.failure(500, e.getMessage());
    }

}
