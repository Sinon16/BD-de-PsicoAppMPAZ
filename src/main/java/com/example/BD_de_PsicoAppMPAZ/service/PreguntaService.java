package com.example.BD_de_PsicoAppMPAZ.service;

import com.example.BD_de_PsicoAppMPAZ.model.Pregunta;
import com.example.BD_de_PsicoAppMPAZ.repository.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    public List<Pregunta> obtenerTodas() {
        return preguntaRepository.findAll();
    }

    public Optional<Pregunta> obtenerPorId(Long id) {
        return preguntaRepository.findById(id);
    }

    public List<Pregunta> obtenerPorCuestionarioId(Long cuestionarioId) {
        return preguntaRepository.findByCuestionarioIdOrderByOrdenAsc(cuestionarioId);
    }

    public Pregunta guardar(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    public void eliminar(Long id) {
        preguntaRepository.deleteById(id);
    }
}