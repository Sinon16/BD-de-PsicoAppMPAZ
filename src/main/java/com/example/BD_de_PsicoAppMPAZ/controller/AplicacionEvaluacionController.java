package com.example.BD_de_PsicoAppMPAZ.controller;

import com.example.BD_de_PsicoAppMPAZ.model.AplicacionEvaluacion;
import com.example.BD_de_PsicoAppMPAZ.model.RespuestaDetalle;
import com.example.BD_de_PsicoAppMPAZ.service.AplicacionEvaluacionService;
import com.example.BD_de_PsicoAppMPAZ.service.RespuestaDetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/aplicaciones")
public class AplicacionEvaluacionController {

    private final AplicacionEvaluacionService aplicacionService;
    private final RespuestaDetalleService respuestaDetalleService;

    // Inyección de ambos servicios requeridos
    public AplicacionEvaluacionController(
            AplicacionEvaluacionService aplicacionService,
            RespuestaDetalleService respuestaDetalleService) {
        this.aplicacionService = aplicacionService;
        this.respuestaDetalleService = respuestaDetalleService;
    }

    @GetMapping
    public ResponseEntity<List<AplicacionEvaluacion>> listarTodas() {
        return ResponseEntity.ok(aplicacionService.obtenerTodas());
    }

    // Endpoint LIBRE: El estudiante consulta su test usando el código temporal (ej. EST-101)
    @GetMapping("/codigo/{codigoTemporal}")
    public ResponseEntity<AplicacionEvaluacion> obtenerPorCodigo(@PathVariable String codigoTemporal) {
        return aplicacionService.obtenerPorCodigoTemporal(codigoTemporal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AplicacionEvaluacion> crearAplicacion(@RequestBody AplicacionEvaluacion aplicacion) {
        return ResponseEntity.ok(aplicacionService.crearAplicacion(aplicacion));
    }

    // Endpoint LIBRE: El estudiante envía sus respuestas usando el RespuestaDetalleService
    @PostMapping("/responder/{codigoTemporal}")
    public ResponseEntity<AplicacionEvaluacion> responderYFinalizar(
            @PathVariable String codigoTemporal,
            @RequestBody List<RespuestaDetalle> respuestas) {
        
        Optional<AplicacionEvaluacion> optApp = aplicacionService.obtenerPorCodigoTemporal(codigoTemporal);
        if (optApp.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AplicacionEvaluacion app = optApp.get();

        // 1. Enlazar las respuestas con la aplicación encontrada
        for (RespuestaDetalle r : respuestas) {
            r.setAplicacionEvaluacion(app);
            if (r.getFechaRespuesta() == null) {
                r.setFechaRespuesta(LocalDateTime.now());
            }
        }

        // 2. Guardar las respuestas mediante RespuestaDetalleService
        respuestaDetalleService.guardarTodas(respuestas);

        // 3. Actualizar el estado y fecha de la sesión del test
        app.setFechaFinalizacion(LocalDateTime.now());
        app.setEstado("COMPLETADA");
        
        return ResponseEntity.ok(aplicacionService.crearAplicacion(app));
    }

    // EXCLUSIVO PSICÓLOGO: Agrega feedback clínico e indicaciones para el profesor
    @PutMapping("/feedback/{id}")
    public ResponseEntity<AplicacionEvaluacion> agregarFeedback(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {
        String observaciones = payload.get("observaciones");
        String indicaciones = payload.get("indicaciones");
        return ResponseEntity.ok(aplicacionService.agregarFeedbackPsicologo(id, observaciones, indicaciones));
    }
}