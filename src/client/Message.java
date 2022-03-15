package client;

public interface Message{

    /**
     *   Override your own code here
     *   Here in GUI show that server not connected
     *
     */
    default void serverNotConnected() {}

    /**
     * Override your code here
     * Here show Player disconnected/game disconnected in GUI
     *
     */
    default void otherPlayerDisconnected(){}


}
