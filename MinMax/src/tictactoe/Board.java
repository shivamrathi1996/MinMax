package tictactoe;

import java.util.Arrays;

public class Board {
    private final int LENGTH = 3;
    private final Player[][] cells = new Player[LENGTH][LENGTH];
    private int numberOfMoves = 0;
    private static Board board = null;

    private Board() {
        for (Player[] row : cells)
            Arrays.fill(row, Player.Blank);
    }

    public static Board getBoard() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public void setCell(int x, int y, Player player) {
        cells[x][y] = player;
        numberOfMoves++;
        int row = 0, column = 0, diagonal = 0, antiDiagonal = 0;
        for (int i = 0; i < LENGTH; i++) {
            if (cells[x][i] == player) column++;
            if (cells[i][y] == player) row++;
            if (cells[i][i] == player) diagonal++;
            if (cells[i][LENGTH - i - 1] == player) antiDiagonal++;
        }
        player.isWinner = isAnyLongEnough(row, column, diagonal, antiDiagonal);
    }

    public void resetCell(int x, int y, Player player) {
        cells[x][y] = Player.Blank;
        numberOfMoves--;
    }

    public Player getAt(int i, int j) {
        return cells[i][j];
    }

    private boolean isAnyLongEnough(int... combinationLengths) {
        Arrays.sort(combinationLengths);
        return Arrays.binarySearch(combinationLengths, LENGTH) >= 0;
    }

    public boolean isCellEmpty(int x, int y) {
        boolean isInsideBoard = x < LENGTH && y < LENGTH && x >= 0 && y >= 0;
        return isInsideBoard && cells[x][y] == Player.Blank;
    }

    public boolean isFull() {
        return numberOfMoves == LENGTH * LENGTH;
    }

    @Override
    public String toString() {
        final String horizontalLine = "-+-+-\n";
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                builder.append(cells[column][row]);
                if (column < cells[row].length - 1)
                    builder.append('|');
            }
            if (row < cells.length - 1)
                builder.append('\n').append(horizontalLine);
        }
        return builder.toString();
    }

}
