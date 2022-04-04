package cl.threeit.api.controllers;

import cl.threeit.api.entity.*;
import cl.threeit.api.handlers.ErrorInformation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("v1/")
@Tag(name = "Genres")
public interface IGenreController {

    @Operation(summary = "Get all genres")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operation executed successfully",
                    content = @Content(schema = @Schema(implementation = GetAllGenresResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorInformation.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content)
    })
    @GetMapping(value = "/genres", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getAll();

    @Operation(summary = "Create genre")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Operation executed successfully",
                    content = @Content(schema = @Schema(implementation = CreateGenreResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorInformation.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content)
    })
    @PostMapping(value = "/genres", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> create(@Valid @RequestBody CreateGenreRequest request);
}
