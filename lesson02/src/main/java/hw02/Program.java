package hw02;

import hw02.client.ClientWindow;
import hw02.client.User;
import hw02.server.ServerWindow;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Ivan", "127.0.0.1", "8989", "12345");
        User user2 = new User("Petr", "128.0.0.1", "9089", "123467");

        ServerWindow serverWindow = new ServerWindow();
        new ClientWindow(serverWindow, user1);
        new ClientWindow(serverWindow, user2);
    }
}