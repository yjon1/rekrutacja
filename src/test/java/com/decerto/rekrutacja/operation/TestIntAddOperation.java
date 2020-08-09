package com.decerto.rekrutacja.operation;

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

class TestIntAddOperation {

    @InjectMocks
    IntAddOperation intAddOperation;

    @Mock
    IntDatabaseSource intDatabaseSource;

    @Mock
    IntRandomOrgSource intRandomOrgSource;

    @Mock
    IntRandomSource intRandomSource;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void test() {
        when(intDatabaseSource.getNumber()).thenReturn(1);
        when(intRandomOrgSource.getNumber()).thenReturn(2);
        when(intRandomSource.getNumber()).thenReturn(3);

        List<INumberSource<Integer>> list = new ArrayList<>();
        list.add(intDatabaseSource);

        assertEquals(1, intAddOperation.doOperation(list));
        list.add(intDatabaseSource);
        assertEquals(2, intAddOperation.doOperation(list));
        list.add(intRandomOrgSource);
        assertEquals(4, intAddOperation.doOperation(list));
        list.add(intRandomSource);
        assertEquals(7, intAddOperation.doOperation(list));
    }
}
