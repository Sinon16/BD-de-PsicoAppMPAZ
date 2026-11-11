package com.example.BD_de_PsicoAppMPAZ.repository;

import com.example.BD_de_PsicoAppMPAZ.model.AplicacionEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AplicacionEvaluacionRepository extends JpaRepository<AplicacionEvaluacion, Long> {
    Optional<AplicacionEvaluacion> findByCodigoTemporal(String codigoTemporal);
    List<AplicacionEvaluacion> findByEstado(String estado);
    List<AplicacionEvaluacion> findByEstudianteId(Long estudianteId);
    List<AplicacionEvaluacion> findByUsuarioResponsableUsername(String username);
}