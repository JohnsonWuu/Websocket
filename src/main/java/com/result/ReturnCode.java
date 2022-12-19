package com.result;

public enum ReturnCode {

    SUCCESS(200, "success"),

    BAD_REQUEST_ERROR(400, "bad request error"),
    INTERNAL_ERROR(500, "internal error"),

    AUTHENTICATION_ERROR(2001, "authentication error"),
    TOKEN_EXPIRE_ERROR(2002, "jwt token expired error"),

    METHOD_ARGUMENT_ERROR(3001, "invalid method argument error"),
    HTTP_CLIENT_SIDE_ERROR(3002, "http client side error"),
    HTTP_SERVER_SIDE_ERROR(3003, "http server side error"),

    MERCHANT_NOT_FOUND_ERROR(4001, "merchant not found error"),
    WAREHOUSE_NOT_FOUND_ERROR(4002, "warehouse not found error"),
    BOOKING_ALREADY_EXIST_ERROR(4003, "booking already exist error"),
    TOTE_TYPES_SETTING_NOT_FOUND_ERROR(4004, "no tote types setting data error"),
    STORAGE_TYPES_SETTING_NOT_FOUND_ERROR(4005, "no storage types setting data  error"),
    STORAGE_TYPE_NOT_FOUND_ERROR(4006, "storage type not found error"),
    WAREHOUSE_SERVICE_TYPE_NOT_FOUND_ERROR(4007, "warehouse service type not found error"),
    WAREHOUSE_SERVICE_INTERNAL_SERVICE_NOT_FOUND_ERROR(4008, "internal warehouse service not found error"),
    AVAILABLE_TOTE_NOT_ENOUGH_ERROR(4009,"available tote not enough error"),
    MERCHANT_TOTE_RENTAL_NOT_FOUND_ERROR(4010,"merchant tote rental not found error"),
    QUOTA_NOT_FOUND_ERROR(4011, "quota not found error"),
    URL_NOT_FOUND_ERROR(4012, "url not found"),
    BOOKING_NOT_FOUND_ERROR(4013,"booking not found error"),
    QUOTA_NOT_ENOUGH_ERROR(4014,"quota not enough error"),
    DATA_NOT_MATCH_ERROR(4015,"data not match error"),
    MERCHANT_ALREADY_HAS_SKU_ERROR(4016, "merchant already has sku"),
    SKU_BATCH_VERIFIED_ERROR(4017, "SkuBatch has been verified"),
    SKU_NOT_FOUND_ERROR(4018, "Sku not found error"),
    SPECIAL_HANDLING_NOT_FOUND_NOT_FOUND_ERROR(4019, "SpecialHandling not found error"),
    MERCHANT_STATUS_NOT_FOUND_ERROR(4020, "Merchant status is only described in [disabled] or [normal]"),
    MERCHANT_UPDATE_DATA_NULL_OR_EMPTY_ERROR(4021, "Merchant update data column null or empty error"),
    TPLRS_DEFAULT_ERROR(4022, "Tplrs response error"),
    TOTE_NOT_FOUND_ERROR(4022, "Tote not found error"),
    TOTE_STATUS_LOCATION_NOT_MATCH_ERROR(4023, "Tote status location not match"),
    CREATE_DEFAULT_QUOTA_ERROR(4024, "create default quota error"),
    INVALID_TIMESLOT_QUOTA_ERROR(4025, "invalid timeslot quota error"),

    TPLRS_SYSTEM_VARIABLE_NOT_FOUND_ERROR(4026, "Tplrs system variable not found error"),

    TIMESLOT_NOT_FOUND_ERROR(4027, "timeslot not found error"),
    TIMESLOT_ALREADY_EXIST_ERROR(4028, "timeslot already exist error"),
    TOTE_COMPARTMENT_NOT_FOUND_ERROR(4029, "ToteCompartment not found error"),
    TOTE_COMPARTMENT_IS_USED_ERROR(4030, "ToteCompartment is used"),
    STOCK_IN_BOOKING_NOT_FOUND_ERROR(4031, "ToteCompartment is used"),
    MERCHANT_REGISTER_SKU_DATA_NULL_ERROR(4032, "Merchant register sku data column null or empty error"),

    RABBITMQ_ERROR(5001, "rabbitmq error");

    private final int code;
    private final String message;

    ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}