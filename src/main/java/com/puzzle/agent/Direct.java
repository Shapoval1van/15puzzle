package com.puzzle.agent;

import java.util.Arrays;
import java.util.List;

public enum Direct {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    static public List<Direct> toList(){
        List<Direct> directList = Arrays.asList(UP, DOWN, LEFT, RIGHT);
        return directList;
    }
}