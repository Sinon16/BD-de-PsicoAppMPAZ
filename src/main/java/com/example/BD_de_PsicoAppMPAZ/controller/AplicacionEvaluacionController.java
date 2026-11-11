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

    // ÚNICO ENDPOINT DE RESPUESTA: Soporta recibir el puntaje Opcional enviado desde Android
    @PostMapping("/responder/{codigoTemporal}")
    public ResponseEntity<AplicacionEvaluacion> responderYFinalizar(
            @PathVariable String codigoTemporal,
            @RequestParam(required = false) Integer puntajeCalculado,
            @RequestBody List<RespuestaDetalle> respuestas) {
        
        Optional<AplicacionEvaluacion> optApp = aplicacionService.obtenerPorCodigoTemporal(codigoTemporal);
        if (optApp.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AplicacionEvaluacion app = optApp.get();

        for (RespuestaDetalle r : respuestas) {
            r.setAplicacionEvaluacion(app);
            if (r.getFechaRespuesta() == null) {
                r.setFechaRespuesta(LocalDateTime.now());
            }
        }

        respuestaDetalleService.guardarTodas(respuestas);

        if (puntajeCalculado != null) {
            app.setPuntajeTotal(puntajeCalculado);
        }

        app.setFechaFinalizacion(LocalDateTime.now());
        app.setEstado("COMPLETADA");
        
        return ResponseEntity.ok(aplicacionService.crearAplicacion(app));
    }

    @PutMapping("/feedback/{id}")
    public ResponseEntity<AplicacionEvaluacion> agregarFeedback(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {
        String observaciones = payload.get("observaciones");
        String indicaciones = payload.get("indicaciones");
        return ResponseEntity.ok(aplicacionService.agregarFeedbackPsicologo(id, observaciones, indicaciones));
    }
}