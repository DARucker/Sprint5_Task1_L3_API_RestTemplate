package cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.controller;

import cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.dto.Flowerdto;
import cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.service.ApiRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientFlower")
@Tag(name = "Spring 5 - Task 1 - Level 3", description = "API Rest Controller")
public class ApiRestController {

    private static Logger LOG = LoggerFactory.getLogger(ApiRestController.class);

    @Autowired
    private final ApiRestService apiRestService;

    public ApiRestController(ApiRestService apiRestService) {
        this.apiRestService = apiRestService;
    }

    @Operation(summary = "Retrive a flower using the flower id", description = "Find the selected flower using the id as the key search")
    @ApiResponse(responseCode = "200", description = "Flower found", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Flowerdto.class))})
    @ApiResponse(responseCode = "404", description = "Flower not found with supplied id",
            content = @Content)
    @GetMapping(value = "getOne/{id}")
    public ResponseEntity<Flowerdto> findById(@PathVariable int id) {

        Flowerdto flowerdto;
        try {
            flowerdto = apiRestService.findById(id);
            LOG.info("flowerdto: " + flowerdto);
        } catch (Exception e) {
            LOG.info("No item for id " + id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flowerdto);
    }


    @Operation(summary = "Retrives all flowers in database", description = "Find and retrives each flower existing in database")
    @ApiResponse(responseCode = "200", description = "Flowers found", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Flowerdto.class))})
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    @GetMapping("/getAll")
    public ResponseEntity<List<Flowerdto>> getAllFlowersFromApi() {
        List<Flowerdto> flowerdtoList = apiRestService.listAll();
        return ResponseEntity.ok(flowerdtoList);
    }

    @Operation(summary = "Adds a new flower", description = "Add a new flower into the database")
    @ApiResponse(responseCode = "201", description = "Flower created correctly", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Flowerdto.class))})
    @ApiResponse(responseCode = "406", description = "Flower values not valid", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error while creating the flower", content = @Content)
    @PostMapping(value = "/add")
    public ResponseEntity<Flowerdto> create(@Valid @RequestBody Flowerdto flowerdto, BindingResult result) {
        LOG.info("Using method: createFlower " + flowerdto);

        // TODO : Catch exception from validation

        return ResponseEntity.status(HttpStatus.CREATED).body(apiRestService.create(flowerdto).getBody());
    }

    @Operation(summary = "Updates a flower using the new flower data", description = "Updates the data of the selected flower")
    @ApiResponse(responseCode = "201", description = "Flower updated", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = Flowerdto.class))})
    @ApiResponse(responseCode = "404", description = "Flower not found", content = @Content)
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Flowerdto flowerdto, BindingResult result, @PathVariable int id) {
        LOG.info("Using method: updateFlower " + flowerdto);
        try {
            flowerdto.setId(id);
            apiRestService.update(flowerdto, id);
            return ResponseEntity.ok(flowerdto);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        // TODO : Catch exception from validation

    }

    @Operation(summary = "Deletes a flower", description = "Deletes a flower using the id as a key")
    @ApiResponse(responseCode = "200", description = "Flower DELETED", content = @Content)
    @ApiResponse(responseCode = "404", description = "Flower not found", content = @Content)
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Flowerdto> delete(@PathVariable int id) {
        LOG.info("Using method delete");
        try {
            apiRestService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
}