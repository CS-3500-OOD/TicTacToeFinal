package Controller;

import Model.Coord;
import Model.GameStatus;
import Model.TicTacToeInteractions;
import Model.TileType;
import View.DisplayGame;

import java.util.Scanner;

public class SimplePlay implements PlayGame{

    private final DisplayGame view;
    private final TicTacToeInteractions model;

    private final Readable input;

    private GameStatus gameStat;

    private boolean player = true;

    public SimplePlay(DisplayGame view, TicTacToeInteractions model, Readable read){

        if (model == null || view == null || read == null) {
            throw new IllegalArgumentException("The model or view or input provided is null");
        }

        this.view = view;
        this.model = model;
        this.input  =read;
        this.gameStat = GameStatus.NoWINNER;
    }

    public void playGame() {
        Scanner scan = new Scanner(this.input);

        while (this.gameStat == GameStatus.NoWINNER) {

            view.displayMessage("Game Board: " + "\n");

            view.displayBoard();

            view.displayMessage("\n");

            view.displayMessage("Press q or Q to quit anytime" + "\n");

            view.displayMessage("Player 1 starts");

            view.displayMessage("Enter two numbers for row, column between 1 and 3" + "\n");


            int[] listofPositions = new int[2];
            playGameHelper(scan, listofPositions);
        }
    }


    private void playGameHelper(Scanner scan, int[] list) throws IllegalStateException {
        int count = 0; // which count are we on
        player = true; // true means player 1
        while (scan.hasNext()) {

            String next = scan.next();

            // if game is quit
            if (next.equals("q") || next.equals("Q")) {
                this.gameStat = GameStatus.QUIT; // stop the game, would ultimately stop the method

                view.displayMessage("Game quit!\n");
                view.displayMessage("No winner! \n");
                return;
            }

            else if (count < 1 && isNumber(next) && Integer.parseInt(next) > 0) {
                list[count] = Integer.parseInt(next);
                count++;
            }

            else if (count == 1 && isNumber(next) && Integer.parseInt(next) > 0) {
                list[count] = Integer.parseInt(next);
                count = 0;

                //TODO: Extract to exception helper
                this.processMove(list);
//                if(player){ //player 1 moves
//                    try {
//                        this.model.placeTile(new Coord(list[0] - 1, list[1] - 1), TileType.P1);
//                        gameStat = this.model.isGameOver();
//                        player = false;
//                    } catch (IllegalArgumentException e){
//                        view.displayMessage("Invalid input, Player 1 please try again. Reason it was invalid: \n"
//                                + e.getMessage());
//                    }
//                }
//                else{ //player 2 moves
//                    try {
//                        this.model.placeTile(new Coord(list[0] - 1, list[1] - 1), TileType.P2);
//                        gameStat = this.model.isGameOver();
//                        player = true;
//                    } catch (IllegalArgumentException e){
//                        view.displayMessage("Invalid input, Player 2 please try again. Reason it was invalid: \n"
//                                + e.getMessage());
//                    }
//                }


                if(gameStat != GameStatus.NoWINNER){
                    endGameWinner();
                    return; // end the method
                }

                view.displayMessage("\n");
                view.displayBoard();
                list = new int[2];

            } else {
                view.displayMessage("Please re-enter the value. Should be a number in range 1-3 \n");
            }
        }

        throw new IllegalStateException("No more input left to read.");


    }

    private void endGameWinner() {
        view.displayMessage("Game Ends!! \n");
        view.displayBoard();

        if(gameStat == GameStatus.P1WINS) {
            view.displayMessage("Winner: Player 1 :)");
        }
        else {
            view.displayMessage("Winner: Player 2 :)");
        }

    }

    /**
     * Determines if the string passed in is a number or not.
     *
     * @param s the string to be evaluated.
     * @return true if the string is a number.
     */
    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void processMove(int[] inputs){
        String format = "2";
        if (player){
            format = "1";
        }
        try {
            this.model.placeTile(new Coord(inputs[0] - 1, inputs[1] - 1), TileType.P2);//TODO: BUG!!!! Indexes are
            //flipped
            gameStat = this.model.isGameOver();
            player = !player; //TODO: BUG!!!! player should be switched
        } catch (IllegalArgumentException e){
            view.displayMessage("Invalid input, Player" + format + "please try again. Reason it was invalid: \n"
                    + e.getMessage());
        }
    }
}


