import java.util.InputMismatchException;
import java.util.Scanner;

class TicTacToe {

    Scanner scanner = new Scanner(System.in);

    String input = "         "; //state of the game
    int player = 1; // 1 - first player, -1 - second player
    String p = "X"; // character of the current player

    //prints state of the game
    void printGame() {
        System.out.println("---------");
        System.out.println("|" + input.substring(0, 3).replace("", " ") + "|");
        System.out.println("|" + input.substring(3, 6).replace("", " ") + "|");
        System.out.println("|" + input.substring(6, 9).replace("", " ") + "|");
        System.out.println("---------");
    }

    //checks if player "c" wins
    boolean Wins(char c) {
        for (int i = 0; i < 3; i++) {
            if (input.substring(i * 3, i * 3 + 3).equals(String.valueOf(new char[] {c, c, c})) ||
                    input.charAt(i) == c && input.charAt(i + 3) == c && input.charAt(i + 6) == c) {
                return true;
            }
        }
        return input.charAt(4) == c && (input.charAt(0) == c &&  input.charAt(8) == c || input.charAt(2) == c && input.charAt(6) == c);
    }

    boolean available(int i, int j) {
        return input.charAt((i - 1) * 3 + (j - 1)) == ' ';
    }

    void step(int i, int j, String p) {
        input = input.substring(0, (i - 1) * 3 + (j - 1)) + p + input.substring((i - 1) * 3 + (j - 1) + 1, input.length());
    }

    public void run() {
        printGame();
        while (true) {
            switch (player) {
                case 1:
                    p = "X";
                    break;
                case -1:
                    p = "O";
                    break;
                default:
                    break;
            }
            try {
                System.out.print("Enter the coordinates: ");
                int i = scanner.nextInt();
                int j = scanner.nextInt();
                if (i > 3 || i < 1 || j > 3 || j < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!available(i, j)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    player *= -1;
                    step(i, j, p);
                    printGame();

                    if (Wins('X')) {
                        System.out.println("X wins");
                        break;
                    } else if (Wins('O')) {
                        System.out.println("O wins");
                        break;
                    }

                    if (!input.contains(" ")) {
                        System.out.println("Draw");
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.next();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.run();
    }
}
