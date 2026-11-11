package com.example.BD_de_PsicoAppMPAZ.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuestionarios")
public class Cuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String instruccionesProfesor;

    @Column(columnDefinition = "TEXT")
    private String especificacionesTecnicasPsicologo;

    private String rangoEdad;
    private Integer tiempoEstimadoMinutos;

    @OneToMany(mappedBy = "cuestionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas = new ArrayList<>();

    public Cuestionario() {}

    public Cuestionario(Long id, String titulo, String descripcion, String instruccionesProfesor, String especificacionesTecnicasPsicologo, String rangoEdad, Integer tiempoEstimadoMinutos, List<Pregunta> preguntas) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.instruccionesProfesor = instruccionesProfesor;
        this.especificacionesTecnicasPsicologo = especificacionesTecnicasPsicologo;
        this.rangoEdad = rangoEdad;
        this.tiempoEstimadoMinutos = tiempoEstimadoMinutos;
        if (preguntas != null) this.preguntas = preguntas;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getInstruccionesProfesor() { return instruccionesProfesor; }
    public void setInstruccionesProfesor(String instruccionesProfesor) { this.instruccionesProfesor = instruccionesProfesor; }

    public String getEspecificacionesTecnicasPsicologo() { return especificacionesTecnicasPsicologo; }
    public void setEspecificacionesTecnicasPsicologo(String especificacionesTecnicasPsicologo) { this.especificacionesTecnicasPsicologo = especificacionesTecnicasPsicologo; }

    public String getRangoEdad() { return rangoEdad; }
    public void setRangoEdad(String rangoEdad) { this.rangoEdad = rangoEdad; }

    public Integer getTiempoEstimadoMinutos() { return tiempoEstimadoMinutos; }
    public void setTiempoEstimadoMinutos(Integer tiempoEstimadoMinutos) { this.tiempoEstimadoMinutos = tiempoEstimadoMinutos; }

    public List<Pregunta> getPreguntas() { return preguntas; }
    public void setPreguntas(List<Pregunta> preguntas) { this.preguntas = preguntas; }
}