package com.exception;

import com.errorcode.CmsErrorCode;
import com.errorcode.CmsLogMessage;
import com.errorcode.ErrorCode;

public class CmsCustomRuntimeException extends RuntimeException{
    private final CmsErrorCode errorCode;
    private CmsLogMessage cmsLogMsg;



    public CmsCustomRuntimeException(CmsErrorCode errorCode, CmsLogMessage logMsg,String... cmsLogParameter)
    {
        super(String.valueOf(errorCode.getErrorCode()));
        this.errorCode = errorCode;
        this.cmsLogMsg = logMsg;
        if(cmsLogParameter.length > 0)
            this.cmsLogMsg.setLogErrorMsg(logMsg.getAsFormattedText(cmsLogParameter));
    }


    public CmsErrorCode getErrorCode() {
        return this.errorCode;
    }

    public CmsLogMessage getLogMessage() {
        return this.cmsLogMsg;
    }
}
