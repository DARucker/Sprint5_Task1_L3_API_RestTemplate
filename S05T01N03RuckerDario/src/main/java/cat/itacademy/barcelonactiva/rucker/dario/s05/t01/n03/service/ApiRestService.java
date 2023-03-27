package cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.service;


import cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.dto.Flowerdto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiRestService {

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
}
