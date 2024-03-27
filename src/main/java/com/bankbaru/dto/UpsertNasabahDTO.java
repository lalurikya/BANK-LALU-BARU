package com.bankbaru.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertNasabahDTO {
    @NotBlank(message = "Nomor Ktp Wajib di-input")
    @Size(max = 25, message = "Nomor KTP Tidak boleh lebih dari 25 chars")
    private String nomorKtp;

    @NotBlank(message = "Nama lengkap wajib di-input")
    @Size(max = 50, message = "Nama Lengkap tidak boleh dari 50 chars")
    private String namaLengkap;

    @NotBlank(message = "Alamat wajib di-input")
    @Size(max = 250, message = "Alamat tidak boleh dari 250 chars")
    private String alamat;

    @NotBlank(message = "Tempat Lahir wajib di-input")
    @Size(max = 30, message = "Tempat Lahir tidak boleh dari 30 chars")
    private String tempatLahir;

    @NotNull(message = "Tanggal lahir wajib di-input")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahir;

    @NotBlank(message = "Nomor Hp wajib di-input")
    @Size(max = 15, message = "Nomor Hp tidak boleh dari 15 chars")
    private String nomorHp;
}
