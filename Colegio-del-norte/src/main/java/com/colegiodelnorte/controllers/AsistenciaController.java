package com.colegiodelnorte.controllers;


import com.colegiodelnorte.models.Asistencia;
import com.colegiodelnorte.responses.ErrorResponse;
import com.colegiodelnorte.services.AsistenciaService;

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

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencias")
@Tag(name = "Gestión de Asistencias", description = "Endpoints para gestionar la asistencia de alumnos")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @Operation(summary = "Obtener la lista de todas las asistencias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de asistencias obtenida correctamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Asistencia>> getAllAsistencias() {
        try {
            List<Asistencia> lista = asistenciaService.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Obtener una asistencia por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asistencia encontrada", content = @Content(schema = @Schema(implementation = Asistencia.class))),
            @ApiResponse(responseCode = "404", description = "Asistencia no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(asistenciaService.findById(id));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Registrar una nueva asistencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Asistencia registrada correctamente", content = @Content(schema = @Schema(implementation = Asistencia.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Asistencia> create(@RequestBody Asistencia asistencia) {
        try {
            Asistencia creada = asistenciaService.save(asistencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Actualizar una asistencia existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asistencia actualizada correctamente", content = @Content(schema = @Schema(implementation = Asistencia.class))),
            @ApiResponse(responseCode = "404", description = "Asistencia no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> update(@PathVariable Long id, @RequestBody Asistencia asistenciaActualizada) {
        try {
            Asistencia actualizada = asistenciaService.update(id, asistenciaActualizada);
            return ResponseEntity.ok(actualizada);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una asistencia por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Asistencia eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Asistencia no encontrada", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            asistenciaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(summary = "Verificar si un alumno asistió a un curso en una fecha específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Asistencia encontrada", content = @Content(schema = @Schema(implementation = Asistencia.class))),
        @ApiResponse(responseCode = "404", description = "No se encontró asistencia", content = @Content)
    })
    @GetMapping("/verificar")
    public ResponseEntity<Asistencia> verificarAsistencia(
            @RequestParam Long alumnoId,
            @RequestParam String curso,
            @RequestParam String fecha // formato esperado: yyyy-MM-dd
    ) {
        try {
            LocalDate fechaConvertida = LocalDate.parse(fecha);
            Optional<Asistencia> asistencia = asistenciaService.verificarAsistenciaPorCursoYFecha(alumnoId, curso, fechaConvertida);

            return asistencia.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());

        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }
}