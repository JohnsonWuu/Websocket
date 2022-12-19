package com.exception;

import com.errorcode.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalRuntimeExceptionHandler {
    @ExceptionHandler(CustomRuntimeException.class)
    public final void handleDemoException(CustomRuntimeException cex) {

        String errorCodeResult =  (cex.getErrorCode() != null) ? "error code: " + cex.getErrorCode().getErrorCode() + " ,error message: " + cex.getErrorCode().getErrorMsg() : "None";
        String logMessage = (cex.getLogMessage() != null) ? cex.getLogMessage().getLogErrorMsg() : "None";

        String result = errorCodeResult + " ,log message: " + logMessage;

        log.error("Handle CustomRuntimeException,result: " + result);
        log.error("CustomRuntimeExceptionHandler exception:", cex);
    }


    @ExceptionHandler(CmsCustomRuntimeException.class)
    public final void handleCmsException(CmsCustomRuntimeException cex) {

        String errorCodeResult =  (cex.getErrorCode() != null) ? "error code: " + cex.getErrorCode().getErrorCode() + " ,error message: " + cex.getErrorCode().getErrorMsg() : "None";
        String logMessage = (cex.getLogMessage() != null) ? cex.getLogMessage().getLogErrorMsg() : "None";

        String result = errorCodeResult + " ,log message: " + logMessage;

        log.error("Handle CmsCustomRuntimeException,result: " + result);
        log.error("CmsCustomRuntimeException exception:", cex);
    }
}
