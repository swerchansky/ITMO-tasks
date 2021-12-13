package game;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Matches extends TwoPlayerGame {
    private int countFirst = 0;
    private int countSecond = 0;
    private int countGames = 0;

    public Matches(Board board, Player player1, Player player2) {
        super(board, player1, player2);
    }

    public int play(boolean log, boolean fl) {
        Scanner scan;
        System.out.println("Введите количество побед для выигрыша: ");
        int win = 0;
        while (win < 1) {
            try {
                scan = new Scanner(System.in);
                win = Integer.parseInt(scan.nextLine());
                if (win < 1) {
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
        while (countFirst < win && countSecond < win) {
            int result = super.play(log, fl);
            countGames++;
            switch (result) {
                case 1 -> {
                    countFirst++;
                    System.out.println("Первый игрок выиграл");
                }
                case 2 -> {
                    countSecond++;
                    System.out.println("Второй игрок выиграл");
                }
                case 0 -> System.out.println("Ничья");
                default -> throw new AssertionError("Unknown result " + result);
            }
            System.out.println("Количество побед первого игрока: " + countFirst);
            System.out.println("Количество побед второго игрока: " + countSecond);
            fl = countGames % 2 != 0;
            board.clear(fl);
        }
        if (countFirst == win) {
            return 1;
        } else {
            return 2;
        }
    }

}
