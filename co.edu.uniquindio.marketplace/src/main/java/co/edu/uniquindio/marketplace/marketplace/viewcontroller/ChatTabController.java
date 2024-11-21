package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.services.Moderator;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.stream.Collectors;

public class ChatTabController {

    private final String sellerName;
    private final Moderator moderator;
    private final TextArea chatArea = new TextArea();
    private final TextField inputField = new TextField();
    private final Button sendButton = new Button("Enviar");
    private final ComboBox<String> recipientSelector = new ComboBox<>();

    public ChatTabController(String sellerName, Moderator moderator) {
        this.sellerName = sellerName;
        this.moderator = moderator;

        recipientSelector.setPromptText("Seleccionar destinatario");
        updateRecipientList();
    }

    public VBox getChatContent() {
        VBox chatBox = new VBox(10);
        chatArea.setEditable(false);
        chatBox.getChildren().addAll(chatArea, recipientSelector, inputField, sendButton);

        sendButton.setOnAction(event -> {
            String message = inputField.getText();
            String recipient = recipientSelector.getValue();

            if (!message.isEmpty() && recipient != null) {
                chatArea.appendText("Tú a " + recipient + ": " + message + "\n");
                moderator.sendMessage(sellerName, recipient, message);
                inputField.clear();
            } else if (recipient == null) {
                chatArea.appendText("Selecciona un destinatario antes de enviar un mensaje.\n");
            }
        });

        return chatBox;
    }

    public void receiveMessage(String from, String message) {
        chatArea.appendText(from + ": " + message + "\n");
    }

    public void updateRecipientList() {
        recipientSelector.getItems().clear();
        recipientSelector.getItems().addAll(
                moderator.getChatControllers().keySet().stream()
                        .filter(name -> !name.equals(sellerName))
                        .collect(Collectors.toList())
        );
    }
}
