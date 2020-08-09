package com.decerto.rekrutacja.api;

import com.decerto.rekrutacja.operation.IntAddOperation;
import com.decerto.rekrutacja.source.INumberSource;
import com.decerto.rekrutacja.source.IntDatabaseSource;
import com.decerto.rekrutacja.source.IntRandomOrgSource;
import com.decerto.rekrutacja.source.IntRandomSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TestRestApi {

    @InjectMocks
    RestApi restApi;


    @Mock
    IntDatabaseSource intDatabaseSource;

    @Mock
    IntRandomOrgSource intRandomOrgSource;

    @Mock
    IntRandomSource intRandomSource;

    @Mock
    IntAddOperation intAddOperation;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void test() {
        when(intDatabaseSource.getNumber()).thenReturn(1);
        when(intRandomOrgSource.getNumber()).thenReturn(2);
        when(intRandomSource.getNumber()).thenReturn(3);

        assertEquals(RestApi.BRAK_IMPLEMENTACJI, restApi.doOperaion("wrongOperation", null).getBody());
        assertEquals(RestApi.BRAK_ZRODEL, restApi.doOperaion("addIntegers", null).getBody());

        List<INumberSource<Integer>> list = new ArrayList<>();
        list.add(intDatabaseSource);
        when(intAddOperation.doOperation(list)).thenReturn(1);
        assertEquals("Wynik dodwania liczb całkowitych ze źródeł: BazaDanych wynosi: 1", restApi.doOperaion("addIntegers", "S1").getBody());

        list.add(intRandomOrgSource);
        when(intAddOperation.doOperation(list)).thenReturn(3);
        assertEquals("Wynik dodwania liczb całkowitych ze źródeł: BazaDanych + Random.org wynosi: 3", restApi.doOperaion("addIntegers", "S1+S2").getBody());

        list.add(intRandomSource);
        when(intAddOperation.doOperation(list)).thenReturn(6);
        assertEquals("Wynik dodwania liczb całkowitych ze źródeł: BazaDanych + Random.org + Radnom() wynosi: 6", restApi.doOperaion("addIntegers", "S1+S2+S3").getBody());

    }

}
