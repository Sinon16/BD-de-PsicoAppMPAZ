package com.example.BD_de_PsicoAppMPAZ.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aplicaciones_evaluacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AplicacionEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigoTemporal; // Ej: "EST-101"

    private String estado = "PENDIENTE"; 

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinalizacion;
    private LocalDateTime fechaRevision; // Cuándo el psicólogo dejó las observaciones

    
    @Column(columnDefinition = "TEXT")
    private String observacionesPsicologo; // Notas técnicas/clínicas del psicólogo

    @Column(columnDefinition = "TEXT")
    private String indicacionesParaProfesor; // Indicaciones específicas que verá el docente

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "cuestionario_id", nullable = false)
    private Cuestionario cuestionario;

    @ManyToOne
    @JoinColumn(name = "evaluador_id")
    private Usuario usuarioResponsable; // Psicólogo que asignó/revisó

    @OneToMany(mappedBy = "aplicacionEvaluacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RespuestaDetalle> respuestas = new ArrayList<>();
}