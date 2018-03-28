package tictactoe;

public class AI {

    private Board orig = Board.getBoard();
    private Controller controller = Controller.getController();
    private String player = Player.O.toString();
    private String opponent = Player.X.toString();
    private String blank = Player.Blank.toString();
    private int size = 3;

    private int evaluateBoard(String[][] b) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < size; row++) {
            if (b[row][0].equals(b[row][1]) &&
                    b[row][1].equals(b[row][2])) {
                if (b[row][0].equals(player))
                    return +10;
                else if (b[row][0].equals(opponent))
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < size; col++) {
            if (b[0][col].equals(b[1][col]) &&
                    b[1][col].equals(b[2][col])) {
                if (b[0][col].equals(player))
                    return +10;

                else if (b[0][col].equals(opponent))
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0].equals(b[1][1]) && b[1][1].equals(b[2][2])) {
            if (b[0][0].equals(player))
                return +10;
            else if (b[0][0].equals(opponent))
                return -10;
        }

        if (b[0][2].equals(b[1][1]) && b[1][1].equals(b[2][0])) {
            if (b[0][2].equals(player))
                return +10;
            else if (b[0][2].equals(opponent))
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    private boolean isFull(String[][] board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].equals(blank)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int alphaBeta(String[][] board, int depth, int a, int b, boolean maximizingPlayer) {
        int score = evaluateBoard(board);
        if (score == 10 || score == -10) {
            return score;
        }

        if (isFull(board)) {
            return 0;
        }

        if (maximizingPlayer) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j].equals(blank)) {
                        board[i][j] = player;
                        best = Math.max(best, alphaBeta(board, depth + 1, a, b, false));
                        a = Math.max(a, best);
                        board[i][j] = blank;
                        if (b <= a) {
                            break;
                        }
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j].equals(blank)) {
                        board[i][j] = opponent;
                        best = Math.min(best, alphaBeta(board, depth + 1, a, b, true));
                        b = Math.min(b, best);
                        board[i][j] = blank;
                        if (b <= a) {
                            break;
                        }
                    }
                }
            }
            return best;
        }
    }

    private Move findBestMove(String[][] board) {
        int bestVal = Integer.MIN_VALUE;
        Move bestMove = new Move(-1, -1);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].equals(blank)) {
                    board[i][j] = player;
                    int movVal = alphaBeta(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    board[i][j] = blank;
                    if (movVal > bestVal) {
                        bestVal = movVal;
                        bestMove = new Move(i, j);
                    }
                }
            }
        }
        return bestMove;
    }

    private String[][] copyBoard() {
        String[][] board = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = orig.getAt(i, j).toString();
            }
        }
        return board;
    }

    public void aiMakeMove() {
        String[][] board = copyBoard();
        Move move = findBestMove(board);
        controller.makeMove(move);
    }
}
