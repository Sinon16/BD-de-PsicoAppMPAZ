package com.example.BD_de_PsicoAppMPAZ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    private Integer orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuestionario_id")
    @JsonIgnore
    private Cuestionario cuestionario;

    public Pregunta() {}

    public Pregunta(Long id, String enunciado, Integer orden, Cuestionario cuestionario) {
        this.id = id;
        this.enunciado = enunciado;
        this.orden = orden;
        this.cuestionario = cuestionario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }

    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }

    public Cuestionario getCuestionario() { return cuestionario; }
    public void setCuestionario(Cuestionario cuestionario) { this.cuestionario = cuestionario; }
}