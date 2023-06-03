package Model;

public class TicTacToeModel implements TicTacToeInteractions{

    private final TileType[][] board;

    public TicTacToeModel(){
        this.board = new TileType[3][3];
        for (int x = 0; x < board.length; x++){
            for (int y = 0; y < board[0].length; y++){
                board[x][y] = TileType.EMPTY;
            }
        }
    }


    @Override
    public void placeTile(Coord c, TileType player) {

        if(player ==null){
            throw new IllegalArgumentException(" player is null");
        }
      if (!this.isValid(c)){
          throw new IllegalArgumentException("Coord is invalid");
      }
      if (this.board[c.x()][c.y()] != TileType.EMPTY){
          throw new IllegalArgumentException("This location is already occupied");
      }
      this.board[c.x()][c.y()] = player;
    }

    @Override
    public GameStatus isGameOver() {
        if(didPlayerWin(TileType.P1)){
            return GameStatus.P1WINS;
        }
        else if (didPlayerWin(TileType.P2)){
            return GameStatus.P2WINS;
        }
        else {return GameStatus.NoWINNER;}
    }

    @Override
    public TileType getTileAt(Coord location) {
        if (!this.isValid(location)){
            throw new IllegalArgumentException("Invalid Location");
        }
        return board[location.x()][location.y()];
    }

    private boolean isValid(Coord location) {
        return location.x() >= 0 && location.x() <= 2 && location.y() >= 0 && location.y() <=2;

    }

    @Override
    public int getSize() {
        return 3;
    }


    private boolean didPlayerWin(TileType current){
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == current && board[row][1] == current && board[row][2] == current) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == current && board[1][col] == current && board[2][col] == current) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == current && board[1][1] == current && board[2][2] == current) {
            return true;
        }
        if (board[0][2] == current && board[1][1] == current && board[2][0] == current) {
            return true;
        }

        return false;
    }
}
