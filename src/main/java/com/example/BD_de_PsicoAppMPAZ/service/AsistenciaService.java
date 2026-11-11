package com.example.BD_de_PsicoAppMPAZ.service;

import com.example.BD_de_PsicoAppMPAZ.model.Asistencia;
import com.example.BD_de_PsicoAppMPAZ.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    public List<Asistencia> obtenerPorFecha(LocalDate fecha) {
        return asistenciaRepository.findByFecha(fecha);
    }

    public List<Asistencia> obtenerPorFechaYCurso(LocalDate fecha, String curso) {
        return asistenciaRepository.findByFechaAndEstudianteCurso(fecha, curso);
    }

    public List<Asistencia> registrarAsistencias(List<Asistencia> asistencias) {
        return asistenciaRepository.saveAll(asistencias);
    }
}