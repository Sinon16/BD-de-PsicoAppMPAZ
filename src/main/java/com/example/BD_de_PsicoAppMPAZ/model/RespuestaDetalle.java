package com.example.BD_de_PsicoAppMPAZ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas_detalle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String opcionSeleccionada; // "Muy bien", "Bien", "Medio", "Mal", "Muy mal"

    private LocalDateTime fechaRespuesta = LocalDateTime.now(); // Marca temporal por cada respuesta individual

    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aplicacion_id", nullable = false)
    @JsonIgnore
    private AplicacionEvaluacion aplicacionEvaluacion;
}