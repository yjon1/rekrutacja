package com.decerto.rekrutacja.api;

import com.decerto.rekrutacja.operation.IntAddOperation;
import com.decerto.rekrutacja.source.INumberSource;
import com.decerto.rekrutacja.source.IntDatabaseSource;
import com.decerto.rekrutacja.source.IntRandomOrgSource;
import com.decerto.rekrutacja.source.IntRandomSource;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@Log
@Service
@Validated
public class RestApi {

    static final String BRAK_IMPLEMENTACJI = "Brak implementacji";
    static final String BRAK_ZRODEL = "Błędny syntax";
    private final IntAddOperation intAddOperation;
    private final IntDatabaseSource intDatabaseSource;
    private final IntRandomOrgSource intRandomOrgSource;
    private final IntRandomSource intRandomSource;

    public RestApi(IntAddOperation intAddOperation, IntDatabaseSource intDatabaseSource, IntRandomOrgSource intRandomOrgSource, IntRandomSource intRandomSource) {
        this.intAddOperation = intAddOperation;
        this.intDatabaseSource = intDatabaseSource;
        this.intRandomOrgSource = intRandomOrgSource;
        this.intRandomSource = intRandomSource;
    }

    private void addToStringBuilder(StringBuilder stringBuilder, String strigToAdd) {
        if (stringBuilder.length() != 0) {
            stringBuilder.append(" + ");
        }
        stringBuilder.append(strigToAdd);
    }

    @GetMapping(value = "/operation/{operation}/{sources}")
    public ResponseEntity<String> doOperaion(@PathVariable("operation") String operation, @PathVariable("sources") String sources) {

        if (!operation.equals("addIntegers")) {
            log.info(BRAK_IMPLEMENTACJI);
            return ResponseEntity.ok(BRAK_IMPLEMENTACJI);
        }

        if (sources == null || sources.isEmpty()) {
            log.info(BRAK_ZRODEL);
            return ResponseEntity.ok(BRAK_ZRODEL);
        }

        List<INumberSource<Integer>> iNumberSourceList = new ArrayList<>();
        StringBuilder sourceInfo = new StringBuilder();
        try (Scanner s = new Scanner(sources);) {
            s.useDelimiter("[+]");

            while (s.hasNext()) {
                String next = s.next();
                switch (next) {
                    case "S1":
                        iNumberSourceList.add(intDatabaseSource);
                        addToStringBuilder(sourceInfo, "BazaDanych");
                        break;
                    case "S2":
                        iNumberSourceList.add(intRandomOrgSource);
                        addToStringBuilder(sourceInfo, "Random.org");
                        break;
                    case "S3":
                        iNumberSourceList.add(intRandomSource);
                        addToStringBuilder(sourceInfo, "Radnom()");
                        break;
                    default:
                        log.info(BRAK_ZRODEL);
                        return ResponseEntity.ok(BRAK_ZRODEL);
                }
            }
        }

        if (iNumberSourceList.isEmpty()) {
            log.info(BRAK_ZRODEL);
            return ResponseEntity.ok(BRAK_ZRODEL);
        }

        return ResponseEntity.ok("Wynik dodwania liczb całkowitych ze źródeł: " + sourceInfo.toString() + " wynosi: " + intAddOperation.doOperation(iNumberSourceList));
    }
}
