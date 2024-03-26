package com.bankbaru.controller;

import com.bankbaru.dto.UpsertNasabahDTO;
import com.bankbaru.service.NasabahService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nasabah")
public class NasabahRestController extends AbstractRestController{
    @Autowired
    private NasabahService service;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        var dto = service.getAllDataNasabah();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    };

    @GetMapping("/{nomorKtp}")
    public ResponseEntity<Object> getOne(@PathVariable(required = false) String nomorKtp) {
        if (service.existsByNomorKtp(nomorKtp)) {
            var dto = service.getOneDataNasabahByNoKtp(nomorKtp);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data dengan nomor KTP '" + nomorKtp + "' tidak ditemukan. Harap masukkan nomor KTP yang benar.");
        }
    }


    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody UpsertNasabahDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
        }

        // Cek apakah data dengan nomor KTP tersebut sudah ada
        if (service.existsByNomorKtp(dto.getNomorKtp())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data dengan nomor KTP " + dto.getNomorKtp() + " sudah ada");
        }

        // Jika tidak ada kesalahan dan data belum ada, lakukan penyisipan
        service.upsertNasabah(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Data berhasil dimasukkan");
    }


    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpsertNasabahDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            var response = service.upsertNasabah(dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    };

    @DeleteMapping("/{nomorKtp}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) String nomorKtp){
        // Cek apakah data dengan nomor KTP tersebut ada
        if (!service.existsByNomorKtp(nomorKtp)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data dengan nomor KTP " + nomorKtp + " tidak ditemukan");
        }

        service.deleteOneNasabah(nomorKtp);
        return ResponseEntity.status(HttpStatus.OK).body("Berhasil menghapus data dari nomor KTP " + nomorKtp);
    };

}

