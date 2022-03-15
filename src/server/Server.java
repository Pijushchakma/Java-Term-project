package server;

import utilServer.NetworkUtilServer;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int clientCount=0;
    private int port=56789;
    private ServerSocket serverSocket;

    public Server(){
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                serveGame();
            }
        } catch (IOException e) {
            /**
            * show in GUI
            * Server not open
            */
            System.out.println("Exception in opening server socket");
            //e.printStackTrace();
        }

    }

    public void serveGame() {
        Socket firstPlayerSocket =  null;
        Socket secondPlayerSocket = null;
        Socket thirdPlayerSocket = null;
        Socket forthPlayerSocket = null;

        NetworkUtilServer networkUtil1=null;
        NetworkUtilServer networkUtil2=null;
        NetworkUtilServer networkUtil3=null;
        NetworkUtilServer networkUtil4=null;

        while (true){
            try {
                if(firstPlayerSocket==null){

                    System.out.println("In first ");
                    firstPlayerSocket = serverSocket.accept();
                    networkUtil1=new NetworkUtilServer(firstPlayerSocket);
                    System.out.println((clientCount+1)+" th player connected");
                    clientCount++;
                }
            } catch (Exception e) {
                System.out.println("Client not connected");
                //e.printStackTrace();
                continue;
            }

            try {
                if(networkUtil2==null){
                    System.out.println("In second ");
                    secondPlayerSocket = serverSocket.accept();
                    networkUtil2=new NetworkUtilServer(secondPlayerSocket);
                    System.out.println((clientCount+1)+" th player connected");
                    clientCount++;
                }
            }catch (Exception e){

                System.out.println("Client not connected");
                continue;
                //e.printStackTrace();
            }
            try {
                if(networkUtil3==null){
                    System.out.println("In third ");
                    secondPlayerSocket = serverSocket.accept();
                    networkUtil3=new NetworkUtilServer(secondPlayerSocket);
                    System.out.println((clientCount+1)+" th player connected");
                    clientCount++;
                }
            }catch (Exception e){

                System.out.println("Client not connected");
                continue;
                //e.printStackTrace();
            }try {
                if(networkUtil4==null){
                    System.out.println("In forth ");
                    secondPlayerSocket = serverSocket.accept();
                    networkUtil4=new NetworkUtilServer(secondPlayerSocket);
                    System.out.println((clientCount+1)+" th player connected");
                    clientCount++;
                }
            }catch (Exception e){

                System.out.println("Client not connected");
                continue;
                //e.printStackTrace();
            }


            if(networkUtil1!=null){
                if(!networkUtil1.write("start+0")){
                    System.out.println((clientCount-1)+" Disconnected");
                    clientCount--;
                    networkUtil1=networkUtil2;
                    networkUtil2=null;
                    firstPlayerSocket=secondPlayerSocket;
                    secondPlayerSocket=null;
                    continue;
                }
            }

            if(networkUtil2!=null){
                if(!networkUtil2.write("start+1")){
                    System.out.println((clientCount-1)+" Disconnected");
                    clientCount--;
                    networkUtil2=networkUtil3;
                    networkUtil3=null;
                    secondPlayerSocket=thirdPlayerSocket;
                    thirdPlayerSocket=null;
                    continue;
                }
            }
            if(networkUtil3!=null){
                if(!networkUtil3.write("start+2")){
                    System.out.println((clientCount-1)+" Disconnected");
                    clientCount--;
                    networkUtil3=networkUtil4;
                    networkUtil4=null;
                    thirdPlayerSocket=forthPlayerSocket;
                    forthPlayerSocket=null;
                    continue;
                }
            }
            if(networkUtil4!=null){
                if(!networkUtil4.write("start+3")){
                    System.out.println((clientCount-1)+" Disconnected");
                    clientCount--;
                    networkUtil4=null;
                    forthPlayerSocket=null;
                    continue;
                }
            }


            System.out.println("Start Game");

            new NewGame(networkUtil1,networkUtil2,networkUtil3,networkUtil4);
            networkUtil1=null;
            networkUtil2=null;
            networkUtil3=null;
            networkUtil4=null;
            firstPlayerSocket =  null;
            secondPlayerSocket = null;
            thirdPlayerSocket = null;
            forthPlayerSocket = null;
        }
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public String toString() {
        return "Server{" +
                "clientCount=" + clientCount +
                ", port=" + port +
                ", serverSocket=" + serverSocket +
                '}';
    }
}

