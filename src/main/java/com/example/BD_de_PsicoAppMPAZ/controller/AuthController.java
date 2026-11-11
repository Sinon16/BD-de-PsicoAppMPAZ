package com.example.BD_de_PsicoAppMPAZ.controller;

import com.example.BD_de_PsicoAppMPAZ.model.Usuario;
import com.example.BD_de_PsicoAppMPAZ.repository.UsuarioRepository;
import com.example.BD_de_PsicoAppMPAZ.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya está en uso.");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado exitosamente.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        var foundUser = usuarioRepository.findByUsername(usuario.getUsername());

        if (foundUser.isPresent() && passwordEncoder.matches(usuario.getPassword(), foundUser.get().getPassword())) {
            String token = jwtUtils.generateToken(foundUser.get().getUsername(), foundUser.get().getRol());
            
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("rol", foundUser.get().getRol());
            response.put("nombreCompleto", foundUser.get().getNombreCompleto());
            
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Credenciales inválidas.");
    }
}