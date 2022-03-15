package Pijush;

import client.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;


import java.util.Random;

public class GameController {
    private Main main;
    private static volatile int num;
    @FXML

    int X[]={26,76,126,176,226,276,326,376,426,476,476,426,376,326,276,226,176,126,76,26,26,76,126,176,226,276,326,376,426,476,476,426,376,326,276,226,176,126,76,26,26,76,126,176,226,276,326,376,426,476,476,426,376,326,276,226,176,126,76,26,26,76,126,176,226,276,326,376,426,476,476,426,376,326,276,226,176,126,76,26,26,76,126,176,226,276,326,376,426,476,476,426,376,326,276,226,176,126,76,26};
    int Y[]={476,476,476,476,476,476,476,476,476,476,426,426,426,426,426,426,426,426,426,426,376,376,376,376,376,376,376,376,376,376,326,326,326,326,326,326,326,326,326,326,276,276,276,276,276,276,276,276,276,276,226,226,226,226,226,226,226,226,226,226,176,176,176,176,176,176,176,176,176,176,126,126,126,126,126,126,126,126,126,126,76,76,76,76,76,76,76,76,76,76,26,26,26,26,26,26,26,26,26,26};
    @FXML
    private Circle player1;
    @FXML
    private Circle player2;

    @FXML
    private Circle player3;
     @FXML
     private Circle player4;

     String move;

    private  static int a1=0,b1=0,d=0,e=0;
    private static volatile int moveValue;
    public Player client;


    public void createPlayer(){
        client=new Player() {
            @Override
            public void initialize() {
            }

            @Override
            public <T> T WriteMove() {
                System.out.println("IN WriteMove "+moveValue);
                move=moveValue+"";
                return (T) move;
            }

            @Override
            public <T> void readMove(T data) {
                System.out.println("IN read Move "+(String)data);

                moveValue=Integer.parseInt((String )data);

                performMove();
            }

            @Override
            public void setIp() {
                ipAdress="localhost";
            }
        };
    }


   public int RollDice()
    {
        int n=0;
        Random r= new Random();
        n=r.nextInt(6)+1;
        return (n==0?1:n);
    }
    public void PLAYER(Circle player,int a1)
    {
        player.setLayoutX(X[a1]);
        player.setLayoutY(Y[a1]);

        ///Ladder 1

        if (player.getLayoutX() == 176 && player.getLayoutY() == 476) {

            player.setLayoutX(326);
            player.setLayoutY(426);
        }

        ///Ladder 2
        if (player.getLayoutX() == 176 && player.getLayoutY() == 426) {
            player.setLayoutX(326);
            player.setLayoutY(476);
        }
        ///ladder 3
        if (player.getLayoutX() == 26 && player.getLayoutY() == 426) {
            player.setLayoutX(126);
            player.setLayoutY(326);
        }
        ///ladder4
        if (player.getLayoutX() == 376 && player.getLayoutY() == 376) {
            player.setLayoutX(176);
            player.setLayoutY(76);
        }
        ///ladder5
        if (player.getLayoutX() == 26 && player.getLayoutY() == 326) {
            player.setLayoutX(76);
            player.setLayoutY(226);
        }
        ///ladder6
        if (player.getLayoutX() == 126 && player.getLayoutY() == 176) {
            player.setLayoutX(26);
            player.setLayoutY(76);
        }
        ///ladder7
        if (player.getLayoutX() == 476 && player.getLayoutY() == 226) {
            player.setLayoutX(326);
            player.setLayoutY(176);
        }
        ///ladder8
        if (player.getLayoutX() == 476 && player.getLayoutY() == 126) {
            player.setLayoutX(476);
            player.setLayoutY(26);
        }


        ///Snake 1

        if (player.getLayoutX() == 426 && player.getLayoutY() == 476) {
            player.setLayoutX(476);
            player.setLayoutY(326);
        }
        /// Snake 2
        if (player.getLayoutX() == 76 && player.getLayoutY() == 176) {
            player.setLayoutX(76);
            player.setLayoutY(426);
        }
        /// Snake 3
        if (player.getLayoutX() == 326 && player.getLayoutY() == 76) {
            player.setLayoutX(176);
            player.setLayoutY(376);
        }
        /// Snake 4
        if (player.getLayoutX() == 326 && player.getLayoutY() == 226) {
            player.setLayoutX(326);
            player.setLayoutY(326);
        }
        /// Snake 5
        if (player.getLayoutX() == 176 && player.getLayoutY() == 176) {
            player.setLayoutX(26);
            player.setLayoutY(126);
        }
        /// Snake 6
        if (player.getLayoutX() == 376 && player.getLayoutY() == 26) {
            player.setLayoutX(376);
            player.setLayoutY(126);
        }
        /// Snake 7
        if (player.getLayoutX() == 276 && player.getLayoutY() == 26) {
            player.setLayoutX(276);
            player.setLayoutY(126);
        }
        /// Snake 8
        if (player.getLayoutX() == 76 && player.getLayoutY() == 26) {
            player.setLayoutX(126);
            player.setLayoutY(126);
        }
    }


    public void performMove(){
        System.out.println(moveValue);
        if (client.moveOfPlayer == 1) {
            a1 = a1 + moveValue;
            PLAYER(player1, a1);
        }
        if (client.moveOfPlayer == 2) {
            System.out.println(moveValue + "player2");
            b1 = b1 + moveValue;
            PLAYER(player2, b1);
        }
        if (client.moveOfPlayer == 3) {
            System.out.println(moveValue + "Player3");
            d = moveValue + d;
            PLAYER(player3, d);
        }
        if (client.moveOfPlayer == 0) {

            System.out.println(moveValue+ "Player 4");
            e = e + moveValue;
            PLAYER(player4, moveValue);
        }
        if (player1.getLayoutX() == X[99] && player1.getLayoutY() == X[99]) {
            try {
                main.WinningPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (player2.getLayoutX()==X[99]&&player2.getLayoutY()==X[99])
        {
            try {
                main.WinningPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (player3.getLayoutX()==X[99]&& player3.getLayoutY()==X[99])
        {
            try {
                main.WinningPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (player4.getLayoutX()==X[99]&& player4.getLayoutY()==X[99]) {
            try {
                main.WinningPage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


    public void DiceOnAction(ActionEvent event) {
        if(client.moveOfPlayer==client.playerNumber){
            moveValue=RollDice();
            client.playerGiveMove=true;
            performMove();
        }
        else
            System.out.println("It's not your turn");

    }
    public void setMain(Main main) {

        this.main = main;
    }
}
