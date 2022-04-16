package game;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position, Cell cell) {
        System.out.println();
        System.out.println("Текущие позиции:");
        System.out.println(position);
        System.out.println("Введите ход для символа " + cell);
        while (true) {
            try {
                in = new Scanner(System.in);
                final Move move = new Move(in.nextInt() - 1, in.nextInt() - 1, cell);
                if (position.isValid(move)) {
                    return move;
                }
                throw new IllegalArgumentException();
            } catch (NumberFormatException e) {
                System.out.println("Нужно вводить цифры, введите еще раз: ");
            } catch (NoSuchElementException e) {
                System.out.println("Не надо так делать, введите еще раз: ");
            } catch (IllegalArgumentException e) {
                System.out.println("Ход некорректный");
                System.out.println();
                System.out.println("Введите ход для символа " + cell);
            }
        }
    }
}
