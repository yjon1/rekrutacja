package com.decerto.rekrutacja.operation;

import com.decerto.rekrutacja.source.INumberSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntAddOperation implements IOperation<Integer> {

    public Integer doOperation(List<INumberSource<Integer>> sources) {
        return sources.stream().map(INumberSource::getNumber).mapToInt(Integer::intValue).sum();
    }
}
