package battleship;

import java.util.Scanner;

public class Game {
    private final Scanner sc = new Scanner(System.in);

    public void run() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        initializePlayer(player1);
        initializePlayer(player2);

        System.out.println(Messages.GAME_STARTS);
        System.out.println(Messages.TAKE_SHOT);

        while (true) {
            if (playTurn(player1, player2)) break;
            if (playTurn(player2, player1)) break;
        }
    }

    private void initializePlayer(Player player) {
        System.out.printf(Messages.PLAYER.toString(), player.getName());
        player.getField().init();
        player.getField().print(false);
        player.getField().placeShips();
        changeTurn();
    }

    private boolean playTurn(Player currentPlayer, Player opponentPlayer) {
        opponentPlayer.getField().print(true);
        System.out.println("---------------------");
        currentPlayer.getField().print(false);
        System.out.printf(Messages.TURN.toString(), currentPlayer.getName());

        if (opponentPlayer.getField().takeShot()) {
            return true;
        }

        changeTurn();
        return false;
    }

    private void changeTurn() {
        System.out.println(Messages.ENTER);
        sc.nextLine();
    }
}
