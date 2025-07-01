package com.colegiodelnorte.controllers;

import com.colegiodelnorte.models.Matricula;
import com.colegiodelnorte.responses.ErrorResponse;
import com.colegiodelnorte.services.MatriculaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@Tag(name = "Gestión de Matrículas", description = "Endpoints para gestionar Matrículas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Operation(summary = "Obtener la lista de todas las Matrículas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Matricula>> getAllMatriculas() {
        try {
            List<Matricula> lista = matriculaService.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Obtener una Matrícula por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada", content = @Content(schema = @Schema(implementation = Matricula.class))),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(matriculaService.findById(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear una nueva Matrícula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matrícula creada correctamente", content = @Content(schema = @Schema(implementation = Matricula.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Matricula> create(@RequestBody Matricula matricula) {
        try {
            Matricula creada = matriculaService.save(matricula);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Actualizar una Matrícula existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula actualizada", content = @Content(schema = @Schema(implementation = Matricula.class))),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update(@PathVariable Long id, @RequestBody Matricula matriculaActualizada) {
        try {
            Matricula actualizada = matriculaService.update(id, matriculaActualizada);
            return ResponseEntity.ok(actualizada);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una Matrícula por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Matrícula eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            matriculaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}