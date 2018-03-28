package tictactoe;

public enum Player {
    X, O, Blank {
        @Override // to give us a blank space on the board
        public String toString() {
            return " ";
        }
    };

    public boolean isWinner = false;

    public Player getNextPlayer() {
        return this == Player.X ? Player.O : Player.X;
    }
}
