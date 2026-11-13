package com.tecsup.service;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Paciente;
import com.tecsup.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public List<Paciente> listar() {
        return repository.findAll();
    }

    public Paciente obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + id));
    }

    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente actualizar(Long id, Paciente datos) {
        Paciente existente = obtenerPorId(id);
        existente.setDni(datos.getDni());
        existente.setNombres(datos.getNombres());
        existente.setApellidos(datos.getApellidos());
        existente.setFechaNacimiento(datos.getFechaNacimiento());
        existente.setSexo(datos.getSexo());
        existente.setTelefono(datos.getTelefono());
        existente.setDireccion(datos.getDireccion());
        existente.setCorreo(datos.getCorreo());
        existente.setEstado(datos.getEstado());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        Paciente paciente = obtenerPorId(id);
        repository.delete(paciente);
    }
}