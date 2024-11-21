package co.edu.uniquindio.marketplace.marketplace.services;

import java.util.Map;
import co.edu.uniquindio.marketplace.marketplace.viewcontroller.ChatTabController;

public interface Moderator {
    void sendMessage(String from, String to, String message);
    void updateRecipientLists();
    Map<String, ChatTabController> getChatControllers(); // Agregar este método
}
