package com.puzzle.initializer.impl

import com.puzzle.BoardModel
import com.puzzle.initializer.Initializer

class RandomInitializer implements Initializer {
    def valPull = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]

    private def max = BoardModel.SIZE * BoardModel.SIZE
    private def random = new Random()

    def getNextValue() {
        def index = random.nextInt(max)
        def cellValue = valPull[index]
        valPull.remove(index)
        max--
        cellValue
    }
}
