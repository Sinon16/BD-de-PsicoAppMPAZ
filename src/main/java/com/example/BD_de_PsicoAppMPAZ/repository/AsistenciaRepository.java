package com.example.BD_de_PsicoAppMPAZ.repository;

import com.example.BD_de_PsicoAppMPAZ.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByFecha(LocalDate fecha);
    List<Asistencia> findByEstudianteId(Long estudianteId);
    List<Asistencia> findByFechaAndEstudianteCurso(LocalDate fecha, String curso);
}