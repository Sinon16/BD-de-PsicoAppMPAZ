package com.example.BD_de_PsicoAppMPAZ.controller;

import com.example.BD_de_PsicoAppMPAZ.model.Asistencia;
import com.example.BD_de_PsicoAppMPAZ.service.AsistenciaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping
    public ResponseEntity<List<Asistencia>> listarPorFechaYCurso(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) String curso) {
        if (curso != null && !curso.isBlank()) {
            return ResponseEntity.ok(asistenciaService.obtenerPorFechaYCurso(fecha, curso));
        }
        return ResponseEntity.ok(asistenciaService.obtenerPorFecha(fecha));
    }

    @PostMapping
    public ResponseEntity<List<Asistencia>> registrarAsistencias(@RequestBody List<Asistencia> asistencias) {
        return ResponseEntity.ok(asistenciaService.registrarAsistencias(asistencias));
    }
}