package hw02.server;

import hw02.client.Client;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server {
    private boolean work;
    private static final String LOG_PATH = "src/main/java/hw02/server/log.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private List<Client> clientList = new ArrayList<>();
    private ServerWindow serverWindow;



    public Server(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
    }
    public ServerWindow getServerWindow(){
        return serverWindow;
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
        serverWindow.appendLog(text);
        answerAll(text);
        Date date = new Date();
        saveInLog(sdf.format(date) + ": " + text); //не совсем правильно здесь записывать дату создания (нао при создании/отправке сообщения)
    }

    private void answerAll(String text){
        for (Client client: clientList){
            client.answerFromServer(text);
        }
    }

    private void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getHistory() {
        return readLog();
    }
    private String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length(), stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
