package com.example.BD_de_PsicoAppMPAZ.controller;

import com.example.BD_de_PsicoAppMPAZ.model.Cuestionario;
import com.example.BD_de_PsicoAppMPAZ.service.CuestionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuestionarios")
public class CuestionarioController {

    private final CuestionarioService cuestionarioService;

    public CuestionarioController(CuestionarioService cuestionarioService) {
        this.cuestionarioService = cuestionarioService;
    }

    @GetMapping
    public ResponseEntity<List<Cuestionario>> listarTodos() {
        return ResponseEntity.ok(cuestionarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuestionario> obtenerPorId(@PathVariable Long id) {
        return cuestionarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cuestionario> crearCuestionario(@RequestBody Cuestionario cuestionario) {
        return ResponseEntity.ok(cuestionarioService.guardar(cuestionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuestionario(@PathVariable Long id) {
        cuestionarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}