package game;

import javax.management.relation.RelationNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player1 = null;
        Player player2 = null;
        boolean fl = true;
        while (fl) {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите первого игрока: \"Random\" \"Human\" \"Sequential\"");
            String player1In = in.next();
            switch (player1In) {
                case "Human" -> {
                    player1 = new HumanPlayer(new Scanner(System.in));
                    fl = false;
                }
                case "Random" -> {
                    player1 = new RandomPlayer();
                    fl = false;
                }
                case "Sequential" -> {
                    player1 = new SequentialPlayer();
                    fl = false;
                }
                default -> {
                    System.out.println("Некорректный игрок");
                }
            }
        }
        fl = true;
        while (fl) {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите второго игрока: \"Random\" \"Human\" \"Sequential\"");
            String player2In = in.next();
            switch (player2In) {
                case "Human" -> {
                    player2 = new HumanPlayer(new Scanner(System.in));
                    fl = false;
                }
                case "Random" -> {
                    player2 = new RandomPlayer();
                    fl = false;
                }
                case "Sequential" -> {
                    player2 = new SequentialPlayer();
                    fl = false;
                }
                default -> {
                    System.out.println("Некорректный игрок");
                }
            }
        }
        final int result = new Matches(
                new MNKBoard(),
                player1,
                player2
//                new RandomPlayer(),
//                new RandomPlayer()
//                new CheatingPlayer()
//                new HumanPlayer(new Scanner(System.in))
        ).play(false, false);
        switch (result) {
            case 1 -> System.out.println("Первый игрок выиграл в сражении");
            case 2 -> System.out.println("Второй игрок выиграл в сражении");
            case 0 -> System.out.println("Ничья");
            default -> throw new AssertionError("Unknown result " + result);
        }
    }
}
