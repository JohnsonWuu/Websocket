package com.errorcode;

public enum CmsErrorCode {

    //Generic
    SUCCESS(0, "成功"),
    CMS_ERROR_ONE(-10000, "CMS_ERROR_ONE."),
    CMS_ERROR_TWO(-10001,"CMS_ERROR_TWO"),
    CMS_ERROR_THREE(-10002,"CMS_ERROR_THREE"),

   //SYSTEM_CANCELLED(-10001,"系統錯誤取消", "invalid booking. Booking no : [%s], status : [%s]"),


    UNDEFINED_CODE(-99999, "未定義的錯誤碼.");

    private final int code;
    private final String message;
    //private String logMessage = "";

    CmsErrorCode(int errorCode, String errorMsg) {
        this.code = errorCode;
        this.message = errorMsg;
    }

//    ErrorCode(int errorCode, String errorMsg,String logMessage) {
//        this.code = errorCode;
//        this.message = errorMsg;
//        this.logMessage = logMessage;
//    }
//
//    public String getAsFormattedText(Object... o) {
//        return String.format(logMessage, o);
//    }

    public int getErrorCode() {
        return code;
    }

    public String getErrorMsg() {
        return message;
    }

//    public String getLogErrorMsg() {
//        return logMessage;
//    }
//
//    public void setLogErrorMsg(String logMessage) {
//        this.logMessage = logMessage;
//    }

    public static CmsErrorCode messageMapper(String message) {
        for (CmsErrorCode e : CmsErrorCode.values()) {
            if (e.getErrorMsg().equals(message)) {
                return e;
            }
        }
        return CmsErrorCode.UNDEFINED_CODE;
    }
}
