package cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.service;


import cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.dto.Flowerdto;
import cat.itacademy.barcelonactiva.rucker.dario.s05.t01.n03.dto.FlowerdtoList;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApiRestService {

    private final RestTemplate restTemplate;

    public List<Flowerdto> listAll() {
        //List<Flowerdto> allFlowerApi = new ArrayList<>();
        ResponseEntity<List<FlowerdtoList>> responseEntity = restTemplate
                .getForEntity("http://localhost:9001/flower/getAll", Flowerdto.class);

        Flowerdto flowerdto = responseEntity.getBody();
        allFlowerApi.add(flowerdto);
        return allFlowerApi;
    }

    public Flowerdto findById(int id) throws RestClientException {

        Flowerdto flowerdto = new Flowerdto();
        ResponseEntity<Flowerdto> responseEntity = restTemplate
                .getForEntity("http://localhost:9001/flower/getOne/" + id, Flowerdto.class);
        flowerdto = responseEntity.getBody();
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        return flowerdto;
    }

}
//  +flowerdto.getId()