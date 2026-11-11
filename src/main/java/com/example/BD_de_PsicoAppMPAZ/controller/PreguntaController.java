package com.example.BD_de_PsicoAppMPAZ.controller;

import com.example.BD_de_PsicoAppMPAZ.model.Pregunta;
import com.example.BD_de_PsicoAppMPAZ.service.PreguntaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {

    private final PreguntaService preguntaService;

    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @GetMapping("/cuestionario/{cuestionarioId}")
    public ResponseEntity<List<Pregunta>> listarPorCuestionario(@PathVariable Long cuestionarioId) {
        return ResponseEntity.ok(preguntaService.obtenerPorCuestionarioId(cuestionarioId));
    }

    @PostMapping
    public ResponseEntity<Pregunta> crearPregunta(@RequestBody Pregunta pregunta) {
        return ResponseEntity.ok(preguntaService.guardar(pregunta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPregunta(@PathVariable Long id) {
        preguntaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}