package tictactoe;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;
    private static final Controller controller = Controller.getController();
    private static boolean AI_TURN = false;
    private static AI ai = new AI();

    public static void main(String[] args) {
        out.println("Welcome to TicTacToe.");
        out.println(controller);
        while (controller.isRunning()) {
            informPlayersOfNextTurn();
            makeNextMove(AI_TURN);
            out.println(controller);
        }
        out.println(controller.getResult());
    }

    private static void informPlayersOfNextTurn() {
        String message = "Player %s, it's your turn. Make your move!\n";
        out.printf(message, controller.getCurrentPlayer());
    }

    private static void makeNextMove(boolean ai_turn) {
        if (!ai_turn) {
            Move move = askForMove();
            while (!controller.isMoveAllowed(move)) {
                out.println("Illegal move, try again.");
                move = askForMove();
            }
            controller.makeMove(move);
            AI_TURN = true;
        } else {
            ai.aiMakeMove();
            AI_TURN = false;
        }
    }

    private static Move askForMove() {
        int x = askForCoordinate("horizontal");
        int y = askForCoordinate("vertical");
        return new Move(x - 1, y - 1);
    }

    private static int askForCoordinate(String coordinate) {
        out.printf("Enter the %s coordinate of the cell [1-3]: ", coordinate);
        while (!in.hasNextInt()) {
            out.print("Invalid number, re-enter: ");
            in.next();
        }
        return in.nextInt();
    }
}
