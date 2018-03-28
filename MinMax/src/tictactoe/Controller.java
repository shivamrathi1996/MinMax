package tictactoe;

public class Controller {
    private static Controller controller = null;
    private final Board board = Board.getBoard();
    private Player currentPlayer = Player.X;

    private Controller() {

    }

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public boolean isRunning() {
        return !currentPlayer.isWinner && !board.isFull();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isMoveAllowed(Move move) {
        return isRunning() && board.isCellEmpty(move.X, move.Y);
    }

    public void makeMove(Move move) {
        if (!isRunning()) {
            throw new IllegalStateException("Controller has ended!");
        }
        board.setCell(move.X, move.Y, currentPlayer);
        if (!currentPlayer.isWinner) {
            currentPlayer = currentPlayer.getNextPlayer();
        }
    }

    public void resetMove(Move move) {
        board.resetCell(move.X, move.Y, currentPlayer);

    }

    public GameResult getResult() {
        if (isRunning()) {
            throw new IllegalStateException("Controller is still running!");
        }
        return new GameResult(currentPlayer);
    }

    @Override
    public String toString() {
        return board.toString();
    }

}
