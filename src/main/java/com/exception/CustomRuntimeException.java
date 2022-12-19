package com.exception;


import com.errorcode.ErrorCode;
import com.errorcode.LogMessage;

public class CustomRuntimeException extends RuntimeException {

    private final ErrorCode errorCode;
    private LogMessage logMsg;


    public CustomRuntimeException(ErrorCode errorCode,String message) {
        super(String.valueOf(message));
        this.errorCode = errorCode;
    }

    public CustomRuntimeException(ErrorCode errorCode) {
        super(String.valueOf(errorCode.getErrorCode()));
        this.errorCode = errorCode;
    }

    public CustomRuntimeException(ErrorCode errorCode, LogMessage logMsg,String... logParameter)
    {
        super(String.valueOf(errorCode.getErrorCode()));
        this.errorCode = errorCode;
        this.logMsg = logMsg;
        if(logParameter.length > 0)
            this.logMsg.setLogErrorMsg(logMsg.getAsFormattedText(logParameter));
    }


    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public LogMessage getLogMessage() {
        return this.logMsg;
    }

}
