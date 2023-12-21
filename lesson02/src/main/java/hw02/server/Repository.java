package hw02.server;


public interface Repository {

    void saveInLog (String message);
    String getHistory();
}
