package com.puzzle.agent

enum Direct {
    UP,
    DOWN,
    LEFT,
    RIGHT

    def static toList(){
        def directList = [UP, DOWN, LEFT, RIGHT]
        directList
    }
}