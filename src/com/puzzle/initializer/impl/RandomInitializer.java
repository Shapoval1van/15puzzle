package com.puzzle.initializer.impl;

import com.puzzle.BoardModel;
import com.puzzle.initializer.Initializer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class RandomInitializer implements Initializer {
    private final LinkedList<Integer> valPull = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));

    private Integer max = BoardModel.SIZE * BoardModel.SIZE;
    private Random random = new Random();

    private Integer getNextValue() {
        Integer index = random.nextInt(max);
        Integer cellValue = valPull.get(index);
        valPull.remove(index);
        max--;
        return cellValue;
    }

    @Override
    public Integer[][] getInitialState() {
        Integer[][] initial = new Integer[BoardModel.SIZE][BoardModel.SIZE];
        for (int i = 0; i < BoardModel.SIZE; i++) {
            for (int j = 0; j < BoardModel.SIZE; j++) {
                    initial[i][j] = getNextValue();
                }
            }
        return initial;
    }
}
