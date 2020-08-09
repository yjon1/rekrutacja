package com.decerto.rekrutacja.source;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Log
public class IntRandomOrgSource implements INumberSource<Integer> {

    static final String URL = "https://www.random.org/integers/?num=1&min=1&max=100&col=1&base=10&rnd=new&format=plain";

    public Integer getNumber() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
        Integer wynik = Integer.parseInt((Objects.requireNonNull(response.getBody()).replace("\n", "")));
        log.info("Odczyt wartości z www.random.org: " + wynik);
        return wynik;
    }
}
