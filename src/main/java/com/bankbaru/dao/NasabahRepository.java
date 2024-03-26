package com.bankbaru.dao;


import com.bankbaru.dto.NasabahDTO;
import com.bankbaru.entity.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NasabahRepository extends JpaRepository<Nasabah, String> {
    @Query("""
        SELECT new com.bankbaru.dto.NasabahDTO(
        nas.nomorKtp,
        nas.namaLengkap,
        nas.alamat,
        nas.tempatLahir,
        nas.tanggalLahir,
        nas.nomorHp)   
        FROM Nasabah AS nas
        """)
    public List<NasabahDTO> getAllDataNasabah();

}
