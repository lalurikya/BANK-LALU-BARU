package com.bankbaru.service;

import com.bankbaru.dto.ResponseUpsertNasabahDTO;
import com.bankbaru.entity.Nasabah;
import com.bankbaru.dao.NasabahRepository;
import com.bankbaru.dto.NasabahDTO;
import com.bankbaru.dto.UpsertNasabahDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class NasabahService {
    @Autowired
    private NasabahRepository nasabahRepository;

    public List<NasabahDTO> getAllDataNasabah(){
        return nasabahRepository.getAllDataNasabah();
    };

    public NasabahDTO getOneDataNasabahByNoKtp(Long nomorKtp){
        var entity = nasabahRepository.findById(String.valueOf(nomorKtp)).get();
        return new NasabahDTO(
            entity.getNomorKtp(),
            entity.getNamaLengkap(),
            entity.getAlamat(),
            entity.getTempatLahir(),
            entity.getTanggalLahir(),
            entity.getNomorHp()
        );
    };

    public void deleteOneNasabah(Long nomorKtp){
        nasabahRepository.deleteById(String.valueOf(nomorKtp));
    };

    public ResponseUpsertNasabahDTO upsertNasabah(UpsertNasabahDTO dto){
            var entity = new Nasabah();
            entity.setNomorKtp(dto.getNomorKtp());
            entity.setNamaLengkap(dto.getNamaLengkap());
            entity.setAlamat(dto.getAlamat());
            entity.setTempatLahir(dto.getTempatLahir());
            entity.setTanggalLahir(dto.getTanggalLahir());
            entity.setNomorHp(dto.getNomorHp());
            var responseEntity = nasabahRepository.save(entity);
            var responseDTO = new ResponseUpsertNasabahDTO(
                    responseEntity.getNomorKtp(),
                    responseEntity.getNamaLengkap(),
                    responseEntity.getAlamat(),
                    responseEntity.getTempatLahir(),
                    responseEntity.getTanggalLahir(),
                    responseEntity.getNomorHp()
            );
            return responseDTO;
    };

    public boolean existsByNomorKtp(Long nomorKtp) {
        return nasabahRepository.existsById(String.valueOf(nomorKtp));
    }
}
