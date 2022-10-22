
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class KanyeWestGetPost {

    private static String getNewKanyeWestPost(){
        try {
            String url = "https://api.kanye.rest";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();
        } catch (Exception e){
            System.out.println("Błąd połączenia z serwerem!");
        }
        return null;
    }

    public static String getPost(){
        return getNewKanyeWestPost();
    }
}
