package com.joaogabrielferr.spring_api.controllers;

import com.joaogabrielferr.spring_api.data.VO.v1.BookVO;
import com.joaogabrielferr.spring_api.services.BookService;
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
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for managing books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds a book",description = "Finds a book",
            responses = {
                    @ApiResponse(description = "Success",responseCode = "200",content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "No content",responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized",responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not found",responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal error",responseCode = "500",content = @Content),
            }
    )
    public BookVO findById(@PathVariable(value = "id")Long id){
        return bookService.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds all books",description = "Finds all books",
            responses = {
            @ApiResponse(description = "Success",responseCode = "200",content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))
            }),
            @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
            @ApiResponse(description = "Unauthorized",responseCode = "401",content = @Content),
            @ApiResponse(description = "Not found",responseCode = "404",content = @Content),
            @ApiResponse(description = "Internal error",responseCode = "500",content = @Content),
    }
    )
    public List<BookVO> findAll(){
        return bookService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Creates a book",description = "Creates a book",
            responses = {
                    @ApiResponse(description = "Created",responseCode = "200",content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized",responseCode = "401",content = @Content),
                    @ApiResponse(description = "Internal error",responseCode = "500",content = @Content),
            }
    )
    public BookVO create(@RequestBody BookVO book){
        return bookService.create(book);
    }


    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates a book",description = "Updates a book",
            responses = {
                    @ApiResponse(description = "Updated",responseCode = "200",content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized",responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not found",responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal error",responseCode = "500",content = @Content),
            }
    )
    public ResponseEntity<BookVO> update(@RequestBody BookVO book,@PathVariable(value = "id")Long id){
        BookVO p = bookService.update(book,id);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a book",description = "Deletes a book",
            responses = {
                    @ApiResponse(description = "No content",responseCode = "204",content = @Content),
                    @ApiResponse(description = "Bad Request",responseCode = "400",content = @Content),
                    @ApiResponse(description = "Unauthorized",responseCode = "401",content = @Content),
                    @ApiResponse(description = "Not found",responseCode = "404",content = @Content),
                    @ApiResponse(description = "Internal error",responseCode = "500",content = @Content),
            }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }





}
