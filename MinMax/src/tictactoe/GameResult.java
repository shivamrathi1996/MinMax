package tictactoe;

public class GameResult {
    private final Player player;

    GameResult(Player lastPlayer) {
        player = lastPlayer;
    }

    public String result() {
        if (player.isWinner) {
            return player.toString();
        } else {
            return "draw";
        }
    }

    @Override
    public String toString() {
        String winner = player.isWinner ? player.toString() : "Nobody";
        return String.format("%s won. Thank you for playing.", winner);
    }
}