package com.connector.connect.service.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Response<T>{

    private T data;

    private List<String> errors;

    public List<String> getErrors() {
        if(errors == null)
            errors = new ArrayList<String>();
        return errors;
    }

}
