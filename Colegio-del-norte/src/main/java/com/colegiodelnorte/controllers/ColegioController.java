package com.colegiodelnorte.controllers;

import com.colegiodelnorte.models.Colegio;
import com.colegiodelnorte.responses.ErrorResponse;
import com.colegiodelnorte.services.ColegioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colegios")
@Tag(name = "Gestión de Colegios", description = "Endpoints para gestionar Colegios")
public class ColegioController {

    @Autowired
    private ColegioService colegioService;

    @Operation(summary = "Obtener la lista de todos los Colegios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Colegio>> getAllColegios() {
        try {
            List<Colegio> colegios = colegioService.findAll();
            return ResponseEntity.ok(colegios);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Obtener un Colegio por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colegio encontrado", content = @Content(schema = @Schema(implementation = Colegio.class))),
            @ApiResponse(responseCode = "404", description = "Colegio no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{colegioId}")
    public ResponseEntity<Colegio> getColegioById(@PathVariable Long colegioId) {
        try {
            Colegio colegio = colegioService.findById(colegioId);
            return ResponseEntity.ok(colegio);
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear un nuevo Colegio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Colegio creado correctamente", content = @Content(schema = @Schema(implementation = Colegio.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Colegio> createColegio(@RequestBody Colegio colegio) {
        try {
            Colegio creado = colegioService.save(colegio);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Actualizar un Colegio por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colegio actualizado correctamente", content = @Content(schema = @Schema(implementation = Colegio.class))),
            @ApiResponse(responseCode = "404", description = "Colegio no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{colegioId}")
    public ResponseEntity<Colegio> updateColegio(@PathVariable Long colegioId, @RequestBody Colegio colegioActualizado) {
        try {
            Colegio actualizado = colegioService.update(colegioId, colegioActualizado);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un Colegio por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Colegio eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Colegio no encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{colegioId}")
    public ResponseEntity<Void> deleteColegio(@PathVariable Long colegioId) {
        try {
            colegioService.deleteById(colegioId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException err) {
            return ResponseEntity.notFound().build();
        }
    }
}