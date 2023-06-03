package View;

import Model.Coord;
import Model.TicTacToeState;

import java.io.IOException;
import java.util.Objects;

/**
 * Implementation of the View interface DisplayGame
 * Takes in a model and appendable (where to display the game on)
 * Model allows to access the board at specific coordinates.
 * Appendable is destination of the output as text
 * Model is read-only interface.
 */
public class TextView implements DisplayGame {

    private final Appendable output;
    private final TicTacToeState model;

    public TextView(Appendable out, TicTacToeState mod){
        Objects.requireNonNull(this.output = out);
        Objects.requireNonNull(this.model = mod);
    }

    @Override
    public void displayBoard() {
        int sizeOfBoard = this.model.getSize();

        StringBuilder build = new StringBuilder();

        for(int i = 0; i < sizeOfBoard; i++) {
            for(int j = 0; j < sizeOfBoard; j++) {
                build.append(this.model.getTileAt(new Coord(i,j)).toString());
                if(j != (sizeOfBoard -1)) {
                    build.append(" ");
                }
            }
            if(i != (sizeOfBoard -1)) {
                build.append("\n");
            }

        }

        this.displayMessage(build.toString());
    }

    @Override
    public void displayMessage(String message) {
        try {
            this.output.append(message);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to append Message");
        }
    }
}
