package com.example.BD_de_PsicoAppMPAZ.service;

import com.example.BD_de_PsicoAppMPAZ.model.AplicacionEvaluacion;
import com.example.BD_de_PsicoAppMPAZ.model.RespuestaDetalle;
import com.example.BD_de_PsicoAppMPAZ.repository.AplicacionEvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AplicacionEvaluacionService {

    @Autowired
    private AplicacionEvaluacionRepository aplicacionRepository;

    public List<AplicacionEvaluacion> obtenerTodas() {
        return aplicacionRepository.findAll();
    }

    public Optional<AplicacionEvaluacion> obtenerPorId(Long id) {
        return aplicacionRepository.findById(id);
    }

    public Optional<AplicacionEvaluacion> obtenerPorCodigoTemporal(String codigo) {
        return aplicacionRepository.findByCodigoTemporal(codigo);
    }

    public AplicacionEvaluacion crearAplicacion(AplicacionEvaluacion aplicacion) {
        aplicacion.setEstado("PENDIENTE");
        return aplicacionRepository.save(aplicacion);
    }

    // Registrar respuestas y finalizar test desde la App Estudiante
    public AplicacionEvaluacion registrarRespuestasYFinalizar(String codigoTemporal, List<RespuestaDetalle> respuestas) {
        Optional<AplicacionEvaluacion> opt = aplicacionRepository.findByCodigoTemporal(codigoTemporal);
        if (opt.isPresent()) {
            AplicacionEvaluacion app = opt.get();
            for (RespuestaDetalle r : respuestas) {
                r.setAplicacionEvaluacion(app);
            }
            app.getRespuestas().addAll(respuestas);
            app.setFechaFinalizacion(LocalDateTime.now());
            app.setEstado("COMPLETADA");
            return aplicacionRepository.save(app);
        }
        throw new RuntimeException("Aplicación no encontrada con el código: " + codigoTemporal);
    }

    // Registrar feedback pedagógico/técnico desde la App Psicólogo
    public AplicacionEvaluacion agregarFeedbackPsicologo(Long idAplicacion, String observaciones, String indicacionesProfe) {
        Optional<AplicacionEvaluacion> opt = aplicacionRepository.findById(idAplicacion);
        if (opt.isPresent()) {
            AplicacionEvaluacion app = opt.get();
            app.setObservacionesPsicologo(observaciones);
            app.setIndicacionesParaProfesor(indicacionesProfe);
            app.setFechaRevision(LocalDateTime.now());
            app.setEstado("REVISADA");
            return aplicacionRepository.save(app);
        }
        throw new RuntimeException("Aplicación no encontrada con ID: " + idAplicacion);
    }
}