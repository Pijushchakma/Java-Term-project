package server;

import utilServer.NetworkUtilServer;

import java.util.Arrays;

public class NewGame {
    private Thread thread;
    private static int gameCount = 0;
    private NetworkUtilServer[] players = new NetworkUtilServer[4];
    private int moveOfPlayer = 0;

    public NewGame(NetworkUtilServer player1, NetworkUtilServer player2, NetworkUtilServer player3, NetworkUtilServer player4) {
        this.players[0] = player1;
        this.players[1] = player2;
        this.players[2] = player3;
        this.players[3] = player4;
        System.out.println((gameCount + 1) + " th game start.");
        gameCount++;
        startGame();
    }

    public void startGame() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                while (true) {
                    Object moveData = receiveMoveData();
                    if (moveData == null) {
                        System.out.println("Exiting game");
                        break;
                    }
                    sentMoveData(moveData);

                    moveOfPlayer++;
                    if (moveOfPlayer == 4) {
                        moveOfPlayer = 0;
                    }
                }
            }
        };
        thread = new Thread(runnable);
        thread.start();
    }

    public Object receiveMoveData() {
        Object s;
        s = players[moveOfPlayer].read();
        try {
            if (s == null || ((String) s).equals("EXIT")) {
                closeGame();
                return null;
            } else
                return s;
        } catch (Exception e) {
            System.out.println("gjhjh");
            return s;
        }
    }

    public void sentMoveData(Object data) {

        for (int i = 0; i < 4; i++) {
            if (moveOfPlayer == i)
                continue;

            System.out.println("Writing to "+ i);
            if (!players[i].write(data)) {
                System.out.println("Exiting");
                for(int j=0;j<4;j++){
                    if(i!=j)
                        players[j].write("EXIT");
                }
            }
        }
    }

    public void closeGame() {
        System.out.println("Player disconnected");

        /**
         * write your code here
         * Here show Player disconnected/game disconnected in GUI
         */

        try {
            thread.wait();
        } catch (Exception e) {
            System.out.println("error in waiting");
        }
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public static int getGameCount() {
        return gameCount;
    }

    public static void setGameCount(int gameCount) {
        NewGame.gameCount = gameCount;
    }

    public NetworkUtilServer[] getPlayers() {
        return players;
    }

    public void setPlayers(NetworkUtilServer[] players) {
        this.players = players;
    }

    public int getMoveOfPlayer() {
        return moveOfPlayer;
    }

    public void setMoveOfPlayer(int moveOfPlayer) {
        this.moveOfPlayer = moveOfPlayer;
    }

    @Override
    public String toString() {
        return "NewGame{" +
                "thread=" + thread +
                ", players=" + Arrays.toString(players) +
                ", moveOfPlayer=" + moveOfPlayer +
                '}';
    }
}