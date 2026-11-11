package com.example.BD_de_PsicoAppMPAZ.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuestionarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String instruccionesProfesor; // Guía pedagógica

    @Column(columnDefinition = "TEXT")
    private String especificacionesTecnicasPsicologo; // Pauta psicométrica

    private String rangoEdad;
    private Integer tiempoEstimadoMinutos;

    @OneToMany(mappedBy = "cuestionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas = new ArrayList<>();
}