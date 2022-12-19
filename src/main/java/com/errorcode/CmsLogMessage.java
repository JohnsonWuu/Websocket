package com.errorcode;

public enum CmsLogMessage {

    //Generic
    CMS_ERROR("CMS Invalid booking. Booking no : [%s], status : [%s]"),
    SYSTEM_DELETED("CMS Delete booking. Booking no : [%s]");

    private String logMessage = "";

    CmsLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public String getAsFormattedText(Object... o) {
        return String.format(logMessage, o);
    }

    public String getLogErrorMsg() {
        return logMessage;
    }

    public void setLogErrorMsg(String logMessage) {
        this.logMessage = logMessage;
    }

}
