package Controller;

import Model.TicTacToeInteractions;
import View.DisplayGame;

public class SimplePlay implements PlayGame{

    private final DisplayGame view;
    private final TicTacToeInteractions model;

    private final Readable input;

    public SimplePlay(DisplayGame view, TicTacToeInteractions model, Readable read){

        this.view = view;
        this.model = model;
        this.input  =read;
    }

    public void playGame() {

    }


}
