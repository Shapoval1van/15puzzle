package com.puzzle.initializer;

import com.puzzle.initializer.impl.RandomInitializer;
import com.puzzle.initializer.impl.ShiftInitializer;

public class InitializerFactory {

    public Initializer getInitializer(InitializerType type) {
        switch (type){
            case Random:
                return new RandomInitializer();
            case Shift:
                return new ShiftInitializer();
            default:
                return new ShiftInitializer();
        }
    }
}
