package com.bankbaru.utility;

import com.bankbaru.dto.utility.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // Mendapatkan pesan kesalahan dari HttpMessageNotReadableException
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Masukan Format inputan yang sesuai, format nomor KTP berupa angka max 16 digit, format tanggal lahir yyyy-MM-dd");

        // Mengembalikan respons dengan status HTTP 400 Bad Request dan pesan kesalahan yang sesuai
        return ResponseEntity.badRequest().body(responseDTO);
    }

    // Anda dapat menambahkan penanganan untuk jenis kesalahan lain di sini sesuai kebutuhan

//     Contoh penanganan kesalahan umum
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Terjadi kesalahan pada server.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }
}