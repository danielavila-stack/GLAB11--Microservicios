package com.tecsup.controller;

import com.tecsup.dto.PacienteDTO;
import com.tecsup.model.Paciente;
import com.tecsup.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@Valid @RequestBody PacienteDTO dto) {
        Paciente p = mapearDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
        Paciente p = mapearDTO(dto);
        return ResponseEntity.ok(service.actualizar(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Paciente eliminado correctamente");
    }

    private Paciente mapearDTO(PacienteDTO dto) {
        Paciente p = new Paciente();
        p.setDni(dto.getDni());
        p.setNombres(dto.getNombres());
        p.setApellidos(dto.getApellidos());
        p.setFechaNacimiento(dto.getFechaNacimiento());
        p.setSexo(dto.getSexo());
        p.setTelefono(dto.getTelefono());
        p.setDireccion(dto.getDireccion());
        p.setCorreo(dto.getCorreo());
        p.setEstado(dto.getEstado());
        return p;
    }
}