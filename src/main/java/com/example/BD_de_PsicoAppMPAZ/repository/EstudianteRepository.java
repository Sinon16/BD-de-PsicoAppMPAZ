package com.example.BD_de_PsicoAppMPAZ.repository;

import com.example.BD_de_PsicoAppMPAZ.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByCurso(String curso);
    List<Estudiante> findByEsTemporal(Boolean esTemporal);
}