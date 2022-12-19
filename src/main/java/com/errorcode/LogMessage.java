package com.errorcode;

public enum LogMessage {

    //Generic
    SYSTEM_CANCELLED("Invalid booking. Booking no : [%s], status : [%s]"),
    SYSTEM_DELETED("Delete booking. Booking no : [%s]");

    private String logMessage = "";

    LogMessage(String logMessage) {
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
