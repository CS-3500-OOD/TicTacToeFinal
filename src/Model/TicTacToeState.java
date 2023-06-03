package Model;

public interface TicTacToeState {

    GameStatus isGameOver();

    TileType getTileAt(Coord location);

    int getSize();

}
