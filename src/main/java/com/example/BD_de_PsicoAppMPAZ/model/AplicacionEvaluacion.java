package com.example.BD_de_PsicoAppMPAZ.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aplicaciones_evaluacion")
public class AplicacionEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigoTemporal;

    private String estado = "PENDIENTE";

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinalizacion;
    private LocalDateTime fechaRevision;

    private Integer puntajeTotal;
    private String interpretacionPuntaje;

    @Column(columnDefinition = "TEXT")
    private String observacionesPsicologo;

    @Column(columnDefinition = "TEXT")
    private String indicacionesParaProfesor;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "cuestionario_id", nullable = false)
    private Cuestionario cuestionario;

    @ManyToOne
    @JoinColumn(name = "evaluador_id")
    private Usuario usuarioResponsable;

    @OneToMany(mappedBy = "aplicacionEvaluacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RespuestaDetalle> respuestas = new ArrayList<>();

    public AplicacionEvaluacion() {}

    public AplicacionEvaluacion(Long id, String codigoTemporal, String estado, LocalDateTime fechaInicio, LocalDateTime fechaFinalizacion, LocalDateTime fechaRevision, Integer puntajeTotal, String interpretacionPuntaje, String observacionesPsicologo, String indicacionesParaProfesor, Estudiante estudiante, Cuestionario cuestionario, Usuario usuarioResponsable, List<RespuestaDetalle> respuestas) {
        this.id = id;
        this.codigoTemporal = codigoTemporal;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.fechaRevision = fechaRevision;
        this.puntajeTotal = puntajeTotal;
        this.interpretacionPuntaje = interpretacionPuntaje;
        this.observacionesPsicologo = observacionesPsicologo;
        this.indicacionesParaProfesor = indicacionesParaProfesor;
        this.estudiante = estudiante;
        this.cuestionario = cuestionario;
        this.usuarioResponsable = usuarioResponsable;
        if (respuestas != null) this.respuestas = respuestas;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoTemporal() { return codigoTemporal; }
    public void setCodigoTemporal(String codigoTemporal) { this.codigoTemporal = codigoTemporal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaFinalizacion() { return fechaFinalizacion; }
    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) { this.fechaFinalizacion = fechaFinalizacion; }

    public LocalDateTime getFechaRevision() { return fechaRevision; }
    public void setFechaRevision(LocalDateTime fechaRevision) { this.fechaRevision = fechaRevision; }

    public Integer getPuntajeTotal() { return puntajeTotal; }
    public void setPuntajeTotal(Integer puntajeTotal) { this.puntajeTotal = puntajeTotal; }

    public String getInterpretacionPuntaje() { return interpretacionPuntaje; }
    public void setInterpretacionPuntaje(String interpretacionPuntaje) { this.interpretacionPuntaje = interpretacionPuntaje; }

    public String getObservacionesPsicologo() { return observacionesPsicologo; }
    public void setObservacionesPsicologo(String observacionesPsicologo) { this.observacionesPsicologo = observacionesPsicologo; }

    public String getIndicacionesParaProfesor() { return indicacionesParaProfesor; }
    public void setIndicacionesParaProfesor(String indicacionesParaProfesor) { this.indicacionesParaProfesor = indicacionesParaProfesor; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public Cuestionario getCuestionario() { return cuestionario; }
    public void setCuestionario(Cuestionario cuestionario) { this.cuestionario = cuestionario; }

    public Usuario getUsuarioResponsable() { return usuarioResponsable; }
    public void setUsuarioResponsable(Usuario usuarioResponsable) { this.usuarioResponsable = usuarioResponsable; }

    public List<RespuestaDetalle> getRespuestas() { return respuestas; }
    public void setRespuestas(List<RespuestaDetalle> respuestas) { this.respuestas = respuestas; }
}