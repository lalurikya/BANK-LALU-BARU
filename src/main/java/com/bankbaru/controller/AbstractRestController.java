package com.bankbaru.controller;

import com.bankbaru.dto.utility.ValidationDTO;
import com.bankbaru.utility.MapperHelper;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRestController {
    List<ValidationDTO> getErrors(List<ObjectError> errors){
        var dto = new ArrayList<ValidationDTO>();
        for(var error : errors){
            var fieldName = MapperHelper.getStringField(error.getArguments()[0], "defaultMessage");
            fieldName = (fieldName.equals("")) ? "object" : fieldName;
            var message = error.getDefaultMessage();
            dto.add(new ValidationDTO(fieldName, message));
        }
        return dto;
    }
}
