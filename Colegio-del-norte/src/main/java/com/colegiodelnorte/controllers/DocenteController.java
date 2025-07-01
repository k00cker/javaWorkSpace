package com.colegiodelnorte.controllers;

import com.colegiodelnorte.models.Docente;
import com.colegiodelnorte.responses.ErrorResponse;
import com.colegiodelnorte.services.DocenteService;

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
@RequestMapping("/api/docentes")
@Tag(name = "Gestión de Docentes", description = "Endpoints para gestionar Docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @Operation(summary = "Obtener la lista de todos los Docentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de docentes obtenida correctamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Docente>> getAllDocentes() {
        try {
            List<Docente> docentes = docenteService.findAll();
            return ResponseEntity.ok(docentes);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Obtener un Docente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Docente encontrado", content = @Content(schema = @Schema(implementation = Docente.class))),
            @ApiResponse(responseCode = "404", description = "Docente no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{docenteId}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable Long docenteId) {
        try {
            Docente docente = docenteService.findById(docenteId);
            return ResponseEntity.ok(docente);
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear un nuevo Docente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Docente creado correctamente", content = @Content(schema = @Schema(implementation = Docente.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Docente> createDocente(@RequestBody Docente docente) {
        try {
            Docente creado = docenteService.save(docente);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Actualizar un Docente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Docente actualizado correctamente", content = @Content(schema = @Schema(implementation = Docente.class))),
            @ApiResponse(responseCode = "404", description = "Docente no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{docenteId}")
    public ResponseEntity<Docente> updateDocente(@PathVariable Long docenteId, @RequestBody Docente docenteActualizado) {
        try {
            Docente actualizado = docenteService.update(docenteId, docenteActualizado);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un Docente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Docente eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Docente no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{docenteId}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Long docenteId) {
        try {
            docenteService.deleteById(docenteId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build();
        }
    }
}