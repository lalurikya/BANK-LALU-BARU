package com.bankbaru.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertNasabahDTO {
    private String nomorKtp;
    private String namaLengkap;
    private String alamat;
    private String tempatLahir;
    private LocalDate tanggalLahir;
    private String nomorHp;
}
