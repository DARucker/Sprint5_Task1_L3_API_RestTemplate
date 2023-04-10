package cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.service;


import cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.dto.Flowerdto;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiRestService {

    private static final Logger log = LoggerFactory.getLogger(ApiRestService.class);
    private final RestTemplate restTemplate;

    public Flowerdto findById(int id)  {
        String url = "http://localhost:9001/flower/getOne/";
        Flowerdto flowerdto = restTemplate.getForEntity( url + id, Flowerdto.class)
                .getBody();
        return flowerdto;
    }

    public List<Flowerdto> listAll() {
        String url = "http://localhost:9001/flower/getAll";
        Flowerdto[] flowersDtoArrary = restTemplate.getForObject(url, Flowerdto[].class);
        return Arrays.asList(flowersDtoArrary);
    }

    public ResponseEntity<Flowerdto> create(Flowerdto flowerdto) {
        String url = "http://localhost:9001/flower/add";
        ResponseEntity<Flowerdto> flowerdtoPost = restTemplate.postForEntity(url, flowerdto, Flowerdto.class);
        return flowerdtoPost;
    }

    public void update(Flowerdto flowerdto, int id) {
        String url = "http://localhost:9001/flower/update/";
        restTemplate.put(url + id, flowerdto, Flowerdto.class);
    }

    public void delete(int id) {
        String url = "http://localhost:9001/flower/delete/";
        restTemplate.delete(url+id);
    }
    public ResponseEntity<Flowerdto> updateBIS(Flowerdto flowerdto, int id, BindingResult result) {
        String url = "http://localhost:9001/flower/update/";
        flowerdto.setId(id);
        //restTemplate.put(url + id, flowerdto, Flowerdto.class);
        ResponseEntity<Flowerdto> flowerdtoUpdate = restTemplate.postForEntity(url + id, flowerdto, Flowerdto.class);
        return flowerdtoUpdate;



    }








}
