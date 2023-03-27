package cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.controller;

import cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.dto.Flowerdto;
import cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.service.ApiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientFlower")
public class ApiRestController {

    @Autowired
    private final ApiRestService apiRestService;

    public ApiRestController(ApiRestService apiRestService) {
        this.apiRestService = apiRestService;
    }

    @GetMapping(value = "getOne/{id}")
    public ResponseEntity<Flowerdto> findById (@PathVariable int id){

        Flowerdto flowerdto = apiRestService.findById(id);
       // if(flowerdto.getId() == 0){
         //   return ResponseEntity.notFound().build();
        //}
        return ResponseEntity.ok(flowerdto);
    }

    @GetMapping("/all")
    public List<Flowerdto> getAllFlowersFromApi(){
        return apiRestService.listAll();

    }




}
