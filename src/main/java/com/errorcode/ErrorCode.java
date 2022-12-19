package com.errorcode;

public enum ErrorCode {

    //Generic
    SUCCESS(0, "成功"),
    PROCESS_SYSTEM_INVOCATION_METHOD_FAILED(-10000, "未存在此函式呼叫方法."),
    SYSTEM_CANCELLED(-10001,"系統錯誤取消"),
    TOTE_EMPTY(-10002,"TOTE EMPTY"),

   //SYSTEM_CANCELLED(-10001,"系統錯誤取消", "invalid booking. Booking no : [%s], status : [%s]"),


    UNDEFINED_CODE(-99999, "未定義的錯誤碼.");

    private final int code;
    private final String message;
    //private String logMessage = "";

    ErrorCode(int errorCode, String errorMsg) {
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

    public static ErrorCode messageMapper(String message) {
        for (ErrorCode e : ErrorCode.values()) {
            if (e.getErrorMsg().equals(message)) {
                return e;
            }
        }
        return ErrorCode.UNDEFINED_CODE;
    }
}
