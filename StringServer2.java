import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

class Handler implements URLHandler {
    // The one bit of state on the server: a string that will be manipulated by
    // various requests.
    String message = "";
    Set<String> messages = new HashSet<>(); // Keep track of unique messages

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                String newMessage = parameters[1];
                if (!messages.contains(newMessage)) { // Check if message already exists
                    messages.add(newMessage);
                    message += "\n" + newMessage; // Append the new message to the existing string with a new line character
                }
            }
            return message;
        } else {
            return "404 Not Found!";
        }
    }
}