package com.bankbaru.controller;

import com.bankbaru.dto.UpsertNasabahDTO;
import com.bankbaru.service.NasabahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nasabah")
public class NasabahRestController {
    @Autowired
    private NasabahService service;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        var dto = service.getAllDataNasabah();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    };

    @GetMapping("/{nomorKtp}")
    public ResponseEntity<Object> getOne(@PathVariable(required = false) String nomorKtp){
        var dto = service.getOneDataNasabahByNoKtp(nomorKtp);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    };

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody UpsertNasabahDTO dto){
            service.insertNasabah(dto);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
    };

    @PutMapping
    public ResponseEntity<Object> put(@RequestBody UpsertNasabahDTO dto){
        service.insertNasabah(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    };

    @DeleteMapping("/{nomorKtp}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) String nomorKtp){
        service.deleteOneNasabah(nomorKtp);
        return ResponseEntity.status(HttpStatus.OK).body(nomorKtp);
    };
}

