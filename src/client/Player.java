package client;


import utilClient.NetworkUtilClient;

public abstract class  Player implements Message{
    public boolean playerGiveMove;
    private NetworkUtilClient networkUtil;
    public int moveOfPlayer;
    public int playerNumber;
    private Object receiveData;
    private Thread thread;
    public String ipAdress="localhost";
    private int port=56789;


    public Player(){
        connectToServer();
    }


    public void connectToServer(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                setIp();
                networkUtil =new NetworkUtilClient(ipAdress,port);
                if(networkUtil.isAvaiable()){
                    String str=(String)networkUtil.read();
                    if(str!=null){
                        String string[]=str.split("\\+");
                        if(string[0].equalsIgnoreCase("start")){
                            playerNumber=Integer.parseInt(string[1]);
                            System.out.println("Player number +"+playerNumber);
                            initialize();
                            moveOfPlayer=0;
                        }
                        startGame();
                    }
                }
                else{
                    serverNotConnected();
                }
            }
        };
        thread= new Thread(runnable);
        thread.start();
    }

    public void startGame(){
        while (true){
            if(moveOfPlayer==playerNumber){
                //System.out.println("Please give a move");

                networkUtil.write(WriteMoveData());
            }
            else {
                //System.out.println("Receive a move data");
                receiveData= networkUtil.read();
                if(receiveData==null ){
                    return;
                }
                else{
                    try{
                        if(((String) receiveData).equals("EXIT")){
                            return;
                        }
                    }catch (Exception e){ }
                }
                readMove(receiveData);
            }
            moveOfPlayer++;
            if(moveOfPlayer==4){
                moveOfPlayer=0;
            }
        }
    }


    public <T> T WriteMoveData(){
        //System.out.println("in moveData");
        while(true){
            if(playerGiveMove){
                //System.out.println("Giving move");
                playerGiveMove=false;
                return WriteMove();
            }
            else{
                System.out.print("");
                //wait();
            }
        }
    }


    /**
     * this method will be called for first player only
     *
     */

    public  abstract void initialize();

    /**
     *
     * It's equivalent to write method of networking.
     *
     * Please implements these method.
     * WriteMove method for sending a move to other client via server
     * Here return Object will be transferred to the other client via server.
     * Write your move code here and return the desired object that you write
     * to the other client.
     *
     * a sample declaration of this move method is
     * public Object move(){  //you can also use generics
     *     while(true){
     *         if(playerGiveMove){  //playerGiveMove is a flag for determining whether player give move or not
     *             return "Move";  //this will be your move object.
     *         }
     *     }
     * }
     */


    public abstract <T>  T WriteMove();


    /**
     *
     * It's equivalent to read method of networking
     *
     * Please implements these method.
     * performMove is for performing other's move
     * Means: When other player give move this function will be called
     * and from the parameter you can extract data and do anything as your wish.
     * a sample declaration of this move method is
     *
     * public void readMove(T data){
     *     System.out.println((String) data);//here i just print the move. You have to show the move in GUI
     * }
     *
     */
    public abstract <T> void readMove(T data);

    /**
     *
     * setIp for setup your ip address
     * Example-for your PC only implementation is given below
     * public abstract void setIp(){
     *     ipAdress="localhost";//you just replace "localhost and add your desired ip of server"
     * }
     */
    public abstract void setIp();

}

