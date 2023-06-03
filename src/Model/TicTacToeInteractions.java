package Model;

public interface TicTacToeInteractions extends TicTacToeState{

    void placeTile(Coord c, TileType player);
}
