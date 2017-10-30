package com.puzzle.initializer

import com.puzzle.initializer.impl.RandomInitializer

class InitializerFactory {

    Initializer getInitializer(InitializerType type) {
        switch (InitializerType.Random){
            case 1:
                return new RandomInitializer()
            default:
                return new RandomInitializer() //TODO add default initializer
        }
    }
}
