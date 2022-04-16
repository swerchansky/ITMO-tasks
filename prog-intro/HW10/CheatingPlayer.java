package game;

import java.util.Scanner;

public class CheatingPlayer implements Player {
    @Override
    public Move makeMove(Position position, Cell cell) {
        final MNKBoard board = (MNKBoard) position;
        Move first = null;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    if (first == null) {
                        first = move;
                    } else {
                        board.makeMove(move);
                    }
                }
            }
        }
        return first;
    }
}
