package com.example.BD_de_PsicoAppMPAZ.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String curso; // Ej: "4° Básico A"

    private Boolean esTemporal = false;

    public Estudiante() {}

    public Estudiante(Long id, String nombre, String curso, Boolean esTemporal) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.esTemporal = esTemporal;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public Boolean getEsTemporal() { return esTemporal; }
    public void setEsTemporal(Boolean esTemporal) { this.esTemporal = esTemporal; }
}