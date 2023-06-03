import Controller.PlayGame;
import Controller.SimplePlay;
import Model.TicTacToeInteractions;
import Model.TicTacToeModel;
import View.DisplayGame;
import View.TextView;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args){
        //Configure IO
        Appendable append = System.out;
        Readable read = new InputStreamReader(System.in);

        //Configure MVC Architecture
        TicTacToeInteractions model = new TicTacToeModel();
        DisplayGame view = new TextView(append, model);
        PlayGame controller = new SimplePlay(view, model, read);

        controller.playGame();
    }

}