package work.pcdd.qndxx.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import work.pcdd.qndxx.common.R;

/**
 * @author pcdd
 * created by 2021/7/13 18:22
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public R handle(Exception e) {
        return R.failure(500, e.getMessage());
    }

}
