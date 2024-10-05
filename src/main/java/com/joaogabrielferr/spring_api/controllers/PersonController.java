package com.joaogabrielferr.spring_api.controllers;

import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.data.VO.v2.PersonVOV2;
import com.joaogabrielferr.spring_api.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "Person", description = "Endpoints for managing people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds a person",description = "Finds a person",tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200",content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No content",responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized",responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not found",responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal error",responseCode = "500",content = @Content),
            }
    )
    public PersonVO findById(@PathVariable(value = "id")Long id){
        return personService.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds all people",description = "Finds all people",tags = {"People"},
            responses = {
            @ApiResponse(description = "Success",responseCode = "200",content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
            }),
            @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized",responseCode = "401",content = @Content),
            @ApiResponse(description = "Not found",responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal error",responseCode = "500",content = @Content),
    }
    )
    public List<PersonVO> findAll(){
        return personService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Creates a person",description = "Creates a person",tags = {"People"},
            responses = {
                    @ApiResponse(description = "Created",responseCode = "200",content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized",responseCode = "401",content = @Content),
                    @ApiResponse(description = "Internal error",responseCode = "500",content = @Content),
            }
    )
    public PersonVO create(@RequestBody PersonVO person){
        return personService.create(person);
    }

    @PostMapping(value = "/v2",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
        return personService.createV2(person);
    }


    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates a person",description = "Updates a person",tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated",responseCode = "200",content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized",responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not found",responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal error",responseCode = "500",content = @Content),
            }
    )
    public ResponseEntity<PersonVO> update(@RequestBody PersonVO person,@PathVariable(value = "id")String id){
        PersonVO p = personService.update(person);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a person",description = "Deletes a person",tags = {"People"},
            responses = {
                    @ApiResponse(description = "No content",responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized",responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not found",responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal error",responseCode = "500",content = @Content),
            }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }





}
