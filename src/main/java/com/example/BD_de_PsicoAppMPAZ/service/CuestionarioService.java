package com.example.BD_de_PsicoAppMPAZ.service;

import com.example.BD_de_PsicoAppMPAZ.model.Cuestionario;
import com.example.BD_de_PsicoAppMPAZ.model.Pregunta;
import com.example.BD_de_PsicoAppMPAZ.repository.CuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuestionarioService {

    @Autowired
    private CuestionarioRepository cuestionarioRepository;

    public List<Cuestionario> obtenerTodos() {
        return cuestionarioRepository.findAll();
    }

    public Optional<Cuestionario> obtenerPorId(Long id) {
        return cuestionarioRepository.findById(id);
    }

    public Cuestionario guardar(Cuestionario cuestionario) {
        // Enlazar bidireccionalmente las preguntas antes de persistir
        if (cuestionario.getPreguntas() != null) {
            for (Pregunta pregunta : cuestionario.getPreguntas()) {
                pregunta.setCuestionario(cuestionario);
            }
        }
        return cuestionarioRepository.save(cuestionario);
    }

    public void eliminar(Long id) {
        cuestionarioRepository.deleteById(id);
    }
}