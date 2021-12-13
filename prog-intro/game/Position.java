package game;

public interface Position {
    Cell getCell(int row, int column);
    boolean isValid(Move move);
    int getN();
    int getM();
    int getK();
}
