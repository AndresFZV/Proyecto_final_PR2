package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.model.Moderator;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.HashMap;
import java.util.Map;

public class ChatController implements Moderator {

    @FXML
    private TabPane tabPane;

    private final Map<String, ChatTabController> chatControllers = new HashMap<>();

    @FXML
    public void initialize() {
        addChatTab("Andrés");
        addChatTab("John");
        addChatTab("Stiven");
        addChatTab("Angélica");
        addChatTab("David");
    }

    private void addChatTab(String sellerName) {
        if (chatControllers.containsKey(sellerName)) {
            System.out.println("El vendedor ya tiene una pestaña activa.");
            return;
        }

        Tab tab = new Tab(sellerName);
        ChatTabController controller = new ChatTabController(sellerName, this);
        tab.setContent(controller.getChatContent());

        tabPane.getTabs().add(tab);
        chatControllers.put(sellerName, controller);

        updateRecipientLists();
    }

    @Override
    public void updateRecipientLists() {
        for (ChatTabController controller : chatControllers.values()) {
            controller.updateRecipientList();
        }
    }

    @Override
    public void sendMessage(String from, String to, String message) {
        ChatTabController recipientController = chatControllers.get(to);
        if (recipientController != null) {
            recipientController.receiveMessage(from, message);
        } else {
            System.out.println("El destinatario no está disponible: " + to);
        }
    }

    public Map<String, ChatTabController> getChatControllers() {
        return chatControllers;
    }
}
