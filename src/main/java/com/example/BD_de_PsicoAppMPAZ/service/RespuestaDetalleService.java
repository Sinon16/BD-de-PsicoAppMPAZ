package com.example.BD_de_PsicoAppMPAZ.service;

import com.example.BD_de_PsicoAppMPAZ.model.RespuestaDetalle;
import com.example.BD_de_PsicoAppMPAZ.repository.RespuestaDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaDetalleService {

    @Autowired
    private RespuestaDetalleRepository respuestaDetalleRepository;

    public List<RespuestaDetalle> obtenerTodas() {
        return respuestaDetalleRepository.findAll();
    }

    public Optional<RespuestaDetalle> obtenerPorId(Long id) {
        return respuestaDetalleRepository.findById(id);
    }

    public List<RespuestaDetalle> obtenerPorAplicacionId(Long aplicacionId) {
        return respuestaDetalleRepository.findByAplicacionEvaluacionId(aplicacionId);
    }

    public RespuestaDetalle guardar(RespuestaDetalle respuesta) {
        return respuestaDetalleRepository.save(respuesta);
    }

    public List<RespuestaDetalle> guardarTodas(List<RespuestaDetalle> respuestas) {
        return respuestaDetalleRepository.saveAll(respuestas);
    }

    public void eliminar(Long id) {
        respuestaDetalleRepository.deleteById(id);
    }
}