package com.bankbaru.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUpsertNasabahDTO {
    private Long nomorKtp;
    private String namaLengkap;
    private String alamat;
    private String tempatLahir;
    private LocalDate tanggalLahir;
    private String nomorHp;
}
