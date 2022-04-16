package game;

public interface Board {
    Position getPosition();
    GameResult makeMove(Move move);
    Cell getTurn();
    void clear(boolean fl);
}
