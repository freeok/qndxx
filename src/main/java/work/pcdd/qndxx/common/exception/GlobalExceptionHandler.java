package work.pcdd.qndxx.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import work.pcdd.qndxx.common.RCode;
import work.pcdd.qndxx.util.R;

/**
 * @author pcdd
 * created by 2021/7/13 18:22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public R<String> handle(Exception e) {
        log.error(e.getMessage(), e);
        return R.fail(RCode.ERROR, e.getMessage());
    }

}
