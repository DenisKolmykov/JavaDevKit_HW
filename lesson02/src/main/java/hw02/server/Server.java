package hw02.server;

import hw02.client.Client;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server {
    private boolean work;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private List<Client> clientList = new ArrayList<>();

    private Repository repository = new FileStorage();
    public ViewServer viewServer;


    public Server(ViewServer viewServer) {
    this.viewServer = viewServer;
    }

    public void setIsServerWork(boolean work){
        this.work = work;
    }

    public boolean isServerWork(){
        return work;
    }

    public boolean connectUser(Client client){
        if (!work){
            return false;
        }
        clientList.add(client);
        return true;
    }

    public void disconnectUser(Client client){
        clientList.remove(client);
        if (client != null){
            client.disconnectFromServer();
        }
    }

    public void disconnectAll(){
        while (!clientList.isEmpty()){
            disconnectUser(clientList.get(clientList.size()-1));
        }
    }

    public void sendMessage(String text){
        if (!work){
            return;
        }
        viewServer.appendLog(text);
        answerAll(text);
        Date date = new Date();
        repository.saveInLog(sdf.format(date) + ": " + text); //не совсем правильно здесь записывать дату создания (нао при создании/отправке сообщения)

    }

    private void answerAll(String text){
        for (Client client: clientList){
            client.answerFromServer(text);
        }
    }

    public String getHistory(){
        return repository.getHistory();
    }
}
