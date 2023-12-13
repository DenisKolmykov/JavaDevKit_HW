package hw01;

import java.util.ArrayList;

public class Chat {
    private ArrayList<ClientWindow> clientList = new ArrayList<>();

    public void addClienttoChat(ClientWindow client){
        clientList.add(client);
    }
    public ArrayList<ClientWindow> getClientList(){
        return clientList;
    }

}
