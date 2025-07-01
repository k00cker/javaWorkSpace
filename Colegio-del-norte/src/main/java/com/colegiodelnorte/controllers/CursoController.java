package com.colegiodelnorte.controllers;


import com.colegiodelnorte.models.Curso;
import com.colegiodelnorte.responses.ErrorResponse;
import com.colegiodelnorte.services.CursoService;

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
@RequestMapping("/api/cursos")
@Tag(name = "Gestión de Cursos", description = "Endpoints para gestionar Cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Operation(summary = "Obtener la lista de todos los Cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida correctamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        try {
            List<Curso> cursos = cursoService.findAll();
            return ResponseEntity.ok(cursos);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Obtener un Curso por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado", content = @Content(schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{cursoId}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long cursoId) {
        try {
            Curso curso = cursoService.findById(cursoId);
            return ResponseEntity.ok(curso);
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear un nuevo Curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso creado correctamente", content = @Content(schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        try {
            Curso creado = cursoService.save(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Actualizar un Curso por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso actualizado correctamente", content = @Content(schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{cursoId}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long cursoId, @RequestBody Curso cursoActualizado) {
        try {
            Curso actualizado = cursoService.update(cursoId, cursoActualizado);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un Curso por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{cursoId}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long cursoId) {
        try {
            cursoService.deleteById(cursoId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build();
        }
    }
}