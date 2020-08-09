package com.decerto.rekrutacja.source;

import com.decerto.rekrutacja.db.repository.SampleEntityRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
public class IntDatabaseSource implements INumberSource<Integer> {

    private final SampleEntityRepository sampleEntityRepository;

    public IntDatabaseSource(SampleEntityRepository sampleEntityRepository) {
        this.sampleEntityRepository = sampleEntityRepository;
    }

    public Integer getNumber() {
        Integer wynik = sampleEntityRepository.getOne("UUID-1").getIntValue();
        log.info("Odczyt wartości z bazy danych: " + wynik);
        return wynik;
    }
}
