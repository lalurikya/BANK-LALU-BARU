package com.bankbaru.controller;

import com.bankbaru.dto.UpsertNasabahDTO;
import com.bankbaru.dto.utility.ResponseDTO;
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
//        int hitung = 5/0;
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    };


    @GetMapping("/{nomorKtp}")
    public ResponseEntity<Object> getOne(@PathVariable(required = false) Long nomorKtp) {
        if (service.existsByNomorKtp(nomorKtp)) {
            var dto = service.getOneDataNasabahByNoKtp(nomorKtp);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            ResponseDTO errorResponse = new ResponseDTO(HttpStatus.NOT_FOUND.value(), "Data dengan nomor KTP " + nomorKtp + " tidak ditemukan. Harap masukkan nomor KTP yang benar.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody UpsertNasabahDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
        }
        // Periksa apakah ada kesalahan validasi
//        if (bindingResult.hasErrors()) {
//            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Harap masukan Format inputan yang sesuai, format KTP berupa angka, format tanggal lahir yyyy-MM-dd");
//            return ResponseEntity.badRequest().body(responseDTO);
//        }

        // Cek apakah data dengan nomor KTP tersebut sudah ada
        if (service.existsByNomorKtp(dto.getNomorKtp())) {
            return ResponseEntity.badRequest().body(
                    new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "Data dengan nomor KTP " + dto.getNomorKtp() + " sudah ada")
            );
        }

        // Jika tidak ada kesalahan dan data belum ada, lakukan penyisipan
        service.upsertNasabah(dto);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "Data Nasabah berhasil dimasukkan"));
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
        if (!service.existsByNomorKtp(Long.valueOf(nomorKtp))) {
            ResponseDTO responseDTO = new ResponseDTO(HttpStatus.NOT_FOUND.value(), "Data Nasabah dengan nomor KTP " + nomorKtp + " tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }

        service.deleteOneNasabah(Long.valueOf(nomorKtp));
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK.value(), "Berhasil menghapus data Nasabah dengan nomor KTP " + nomorKtp);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    };

}

