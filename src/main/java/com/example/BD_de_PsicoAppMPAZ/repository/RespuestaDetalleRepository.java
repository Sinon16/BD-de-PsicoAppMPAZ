package com.example.BD_de_PsicoAppMPAZ.repository;

import com.example.BD_de_PsicoAppMPAZ.model.RespuestaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaDetalleRepository extends JpaRepository<RespuestaDetalle, Long> {
    List<RespuestaDetalle> findByAplicacionEvaluacionId(Long aplicacionId);
}