package com.example.BD_de_PsicoAppMPAZ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas_detalle")
public class RespuestaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String opcionSeleccionada;

    private LocalDateTime fechaRespuesta = LocalDateTime.now();

    @Column
    private Integer valorPuntaje; // Puntos otorgados por esta opción individual (opcional)

    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aplicacion_id", nullable = false)
    @JsonIgnore
    private AplicacionEvaluacion aplicacionEvaluacion;

    public RespuestaDetalle() {}

    public RespuestaDetalle(Long id, String opcionSeleccionada, LocalDateTime fechaRespuesta, Integer valorPuntaje, Pregunta pregunta, AplicacionEvaluacion aplicacionEvaluacion) {
        this.id = id;
        this.opcionSeleccionada = opcionSeleccionada;
        this.fechaRespuesta = fechaRespuesta;
        this.valorPuntaje = valorPuntaje;
        this.pregunta = pregunta;
        this.aplicacionEvaluacion = aplicacionEvaluacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOpcionSeleccionada() { return opcionSeleccionada; }
    public void setOpcionSeleccionada(String opcionSeleccionada) { this.opcionSeleccionada = opcionSeleccionada; }

    public LocalDateTime getFechaRespuesta() { return fechaRespuesta; }
    public void setFechaRespuesta(LocalDateTime fechaRespuesta) { this.fechaRespuesta = fechaRespuesta; }

    public Integer getValorPuntaje() { return valorPuntaje; }
    public void setValorPuntaje(Integer valorPuntaje) { this.valorPuntaje = valorPuntaje; }

    public Pregunta getPregunta() { return pregunta; }
    public void setPregunta(Pregunta pregunta) { this.pregunta = pregunta; }

    public AplicacionEvaluacion getAplicacionEvaluacion() { return aplicacionEvaluacion; }
    public void setAplicacionEvaluacion(AplicacionEvaluacion aplicacionEvaluacion) { this.aplicacionEvaluacion = aplicacionEvaluacion; }
}