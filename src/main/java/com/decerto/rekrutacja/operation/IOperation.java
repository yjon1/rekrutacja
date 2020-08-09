package com.decerto.rekrutacja.operation;

import com.decerto.rekrutacja.source.INumberSource;

import java.util.List;

public interface IOperation<T> {

    T doOperation(List<INumberSource<T>> sources);
}
