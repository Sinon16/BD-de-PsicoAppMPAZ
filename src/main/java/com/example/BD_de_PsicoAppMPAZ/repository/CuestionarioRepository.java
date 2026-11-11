package com.example.BD_de_PsicoAppMPAZ.repository;

import com.example.BD_de_PsicoAppMPAZ.model.Cuestionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuestionarioRepository extends JpaRepository<Cuestionario, Long> {
    List<Cuestionario> findByTituloContainingIgnoreCase(String titulo);
}