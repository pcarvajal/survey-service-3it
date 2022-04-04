package cl.threeit.api.controllers;

import cl.threeit.api.entity.CreateSurveyRequest;
import cl.threeit.api.entity.CreateSurveyResponse;
import cl.threeit.api.entity.GetSurveyResumeResponse;
import cl.threeit.api.handlers.ErrorInformation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("v1/")
@Tag(name = "Surveys")
public interface ISurveyController {

    @Operation(summary = "Create survey record")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Operation executed successfully",
                    content = @Content(schema = @Schema(implementation = CreateSurveyResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad input parameter",
                    content = @Content(schema = @Schema(implementation = ErrorInformation.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content)
    })
    @PostMapping(value = "/surveys", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> createRecord(@RequestBody CreateSurveyRequest request);

    @Operation(summary = "Survey resume")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operation executed successfully",
                    content = @Content(schema = @Schema(implementation = GetSurveyResumeResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorInformation.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content)
    })
    @GetMapping(value = "/surveys/resume", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getResume();
}
