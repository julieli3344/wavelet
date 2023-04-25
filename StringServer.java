import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> list = new ArrayList<>();
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            String s = "";
            for(String ss : list){
                s = ss + "\n";
            }
            return "Strings are: \n" + s;
            // return String.format("Julie Li: %d", num);
        } else if (url.getPath().equals("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")){
                list.add(parameters[1]);
            }
            return String.format(parameters[1] + "\n");
            // num += 1;
            // return String.format("Number incremented!");
        } else {
            return "404 Not Found!";
            // System.out.println("Path: " + url.getPath());
            // if (url.getPath().contains("/add-message")) {
            //     String[] parameters = url.getQuery().split("=");
            //     if (parameters[0].equals("s")) {
            //         num += Integer.parseInt(parameters[1]);
            //         // list.add(parameters[1]);
            //         String ii = "";
            //         for(String i : list){
            //             ii = i + "\n";
            //         }
            //         return "Strings are: \n" + ii;
            //         // return String.format("%s and %d", parameters[1], num);
            //     }
            // }
            
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
