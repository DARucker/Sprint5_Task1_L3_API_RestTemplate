package cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.controller;

import cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.dto.Flowerdto;
import cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.service.ApiRestService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clientFlower")
public class ApiRestController {

    private static Logger LOG = LoggerFactory.getLogger(ApiRestController.class);

    @Autowired
    private final ApiRestService apiRestService;

    public ApiRestController(ApiRestService apiRestService) {
        this.apiRestService = apiRestService;
    }

    @GetMapping(value = "getOne/{id}")
    public ResponseEntity<Flowerdto> findById (@PathVariable int id){

        Flowerdto flowerdto = apiRestService.findById(id);
        LOG.info("flowerdto: " + flowerdto);
        return ResponseEntity.ok(flowerdto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Flowerdto>> getAllFlowersFromApi(){
        List<Flowerdto> flowerdtoList = apiRestService.listAll();
        return ResponseEntity.ok(flowerdtoList);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Flowerdto> create(@Valid @RequestBody Flowerdto flowerdto, BindingResult result){
        LOG.info("Using method: createFlower " + flowerdto);

        // TODO : Controlar excepción que llega desde la validación

        return  ResponseEntity.status(HttpStatus.CREATED).body(apiRestService.create(flowerdto).getBody());
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Flowerdto> update(@Valid @RequestBody Flowerdto flowerdto, BindingResult result, @PathVariable int id){
        LOG.info("Using method: updateFlower " + flowerdto);
        flowerdto.setId(id);
        apiRestService.update(flowerdto, id);
        return  ResponseEntity.ok(flowerdto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Flowerdto> delete(@PathVariable int id){
        LOG.info("Using method delete");
        apiRestService.delete(id);
        return ResponseEntity.ok().build();
    }



}
