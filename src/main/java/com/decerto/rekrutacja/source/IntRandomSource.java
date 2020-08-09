package com.decerto.rekrutacja.source;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Log
public class IntRandomSource implements INumberSource<Integer> {

    public Integer getNumber() {
        Integer wynik = new Random().nextInt(100);
        log.info("Odczyt losowej wartości: " + wynik);
        return wynik;
    }
}
