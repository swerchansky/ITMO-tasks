package game;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MNKBoard implements Position, Board {
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );

    private final Cell[][] field;
    private Cell turn;
    private final int m, n, k;
    private int emptyBoard;

    public MNKBoard() {
        Scanner scan;
        System.out.println("Введите ширину поля для игры: ");
        int m = 0;
        while (m < 1) {
            try {
                scan = new Scanner(System.in);
                m = Integer.parseInt(scan.nextLine());
                if (m < 1) {
                    throw new IllegalArgumentException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Нужно вводить цифры, введите еще раз: ");
            } catch (NoSuchElementException e) {
                System.out.println("Не надо так делать, введите еще раз: ");
            } catch (IllegalArgumentException e) {
                System.out.println("Ширина доски не можеть быть меньше 1, введите еще раз: ");
            }
        }
        System.out.println("Введите высоту поля для игры: ");
        int n = 0;
        while (n < 1) {
            try {
                scan = new Scanner(System.in);
                n = Integer.parseInt(scan.nextLine());
                if (n < 1) {
                    throw new IllegalArgumentException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Нужно вводить цифры, введите еще раз: ");
            } catch (NoSuchElementException e) {
                System.out.println("Не надо так делать, введите еще раз: ");
            } catch (IllegalArgumentException e) {
                System.out.println("Высота доски не можеть быть меньше 1, введите еще раз: ");
            }
        }
        System.out.println("Введите кол-во символов подряд для выигрыша: ");
        int k = 0;
        while (k < 1 || (k > n && k > m)) {
            try {
                scan = new Scanner(System.in);
                k = Integer.parseInt(scan.nextLine());
                if (k < 1 || (k > n && k > m)) {
                    throw new IllegalArgumentException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Нужно вводить цифры, введите еще раз: ");
            } catch (NoSuchElementException e) {
                System.out.println("Не надо так делать, введите еще раз: ");
            } catch (IllegalArgumentException e) {
                System.out.println("Количество ходов для победы не можеть быть меньше 1 и больше самой доски, введите еще раз: ");
            }
        }
        this.m = m;
        this.k = k;
        this.n = n;
        emptyBoard = m * n;
        field = new Cell[n][m];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    public void clear(boolean fl) {
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        this.emptyBoard = n * m;
        if (fl) {
            turn = Cell.O;
        } else {
            turn = Cell.X;
        }
    }

    public Cell getTurn() {
        return turn;
    }

    public Position getPosition() {
        return this;
    }

    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.LOOSE;
        }
        field[move.getRow()][move.getCol()] = move.getValue();
        emptyBoard--;
        if (checkWin(move)) {
            return GameResult.WIN;
        }
        if (emptyBoard == 0) {
            return GameResult.DRAW;
        }
        if (turn == Cell.X) {
            turn = Cell.O;
        } else {
            turn = Cell.X;
        }
        return GameResult.UNKNOWN;
    }

    private boolean checkWin(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        return check(col, row, 0, 1) || check(col, row, 1, 0) ||
                check(col, row, 1, 1) || check(col, row, 1, -1);
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getK() {
        return k;
    }

    private boolean check(int col, int row, int upDown, int leftRight) {
        int count = 1;
        int curRow = row + upDown;
        int curCol = col + leftRight;
        while (curRow >= 0 && curRow < n && count < k && curCol >= 0 && curCol < n) {
            if (field[curRow][curCol] == turn) {
                count++;
            } else {
                break;
            }
            curCol += leftRight;
            curRow += upDown;
        }
        curRow = row - upDown;
        curCol = col - leftRight;
        while (curRow >= 0 && curRow < n && count < k && curCol >= 0 && curCol < n) {
            if (field[curRow][curCol] == turn) {
                count++;
            } else {
                break;
            }
            curCol -= leftRight;
            curRow -= upDown;
        }
        return count >= k;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < m
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder().append("    ");
        for (int i = 0; i < m; i++) {
            if (i < 9) {
                sb.append(i + 1).append("   ");
            } else if (i < 99) {
                sb.append(i + 1).append("  ");
            } else {
                sb.append(i + 1).append(" ");
            }
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < n; r++) {
            if (r < 9) {
                sb.append(" ").append(r + 1).append("  ");
            } else {
                sb.append(r + 1).append("  ");
            }
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell)).append("   ");
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
