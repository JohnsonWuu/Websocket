package com.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> {

    private int status;
    private String message;
    private T data;

    public static <T> ResultData<T> success(T data) {

        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getMessage());
        resultData.setData(data);

        return resultData;
    }

    public static <T> ResultData<T> fail(int code, String message) {

        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);

        return resultData;
    }
}