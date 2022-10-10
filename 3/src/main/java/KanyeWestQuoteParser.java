import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class KanyeWestQuoteParser {

    private static final Set<String> quotes = new HashSet<>();
    private static String OPTION;
    private static final int QUOTES_NUMBER = getJsonFileSize();
    private static int searchingForNewQuoteCount = 0;

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

    private static String parseNewKanyeWestPost(String post) {
        JSONObject jsonPost = new JSONObject(post);
        return jsonPost.getString("quote");
    }

    private static void formatQuoteString(String quote) {
        System.out.println();
        System.out.println('"' + quote + '"');
        String name = "- Kanye West";
        System.out.printf("%" + (quote.length()) + "s",name);
        System.out.println();
    }

    private static void checkIsNewKanyeWestQuote() {

        try {
            String newKanyeWestQuote = parseNewKanyeWestPost(getNewKanyeWestPost());
            String codeQuote = getQuoteCode(newKanyeWestQuote);
            if (searchingForNewQuoteCount == 50){
                System.out.println("Nie udało się znaleśc nowego cytatu. " +
                        "Możesz spróbować ponownie lub zakończyć program.");
            }
            else if (quotes.size() == QUOTES_NUMBER) {
                System.out.println("Niestety to już koniec mądrości Kanye Westa - Koniec programu!!!");
                setOPTION("EXIT");
            } else if (!quotes.contains(codeQuote)) {
                quotes.add(codeQuote);
                formatQuoteString(newKanyeWestQuote);
            } else {
                searchingForNewQuoteCount++;
                checkIsNewKanyeWestQuote();
                searchingForNewQuoteCount = 0;
            }
        }catch (NullPointerException e){
            System.out.println("Nie udało się pobrać nowego cytatu. Błąd połączenia z serwerem!\n" +
                    "Zamykanie programu!");
            setOPTION("EXIT");
        }
    }

    private static String getQuoteCode(String newKanyeWestQuote) {
        int sizeString = newKanyeWestQuote.length() + newKanyeWestQuote.hashCode();
        char first = newKanyeWestQuote.charAt(0);
        char last = newKanyeWestQuote.charAt(newKanyeWestQuote.length()-1);
        return first + String.valueOf(sizeString) + last;
    }

    private static int getJsonFileSize(){
        String json;
        try {
            json = IOUtils.toString(URI.create("https://raw.githubusercontent.com/ajzbc/kanye.rest/master/quotes.json"),
                    StandardCharsets.UTF_8);
            JSONArray arrayJson = new JSONArray(json);
            return arrayJson.length();
        } catch (IOException e) {
            System.out.println("Błąd odczytu z serwera! Nie ma połączenia lub brak dostępu " +
                    "do pliku! Sprawdź połączenie i spróbuj ponownie!\n" +
                    "Zamykanie programu");
            setOPTION("EXIT");
        }
        return 0;
    }

    public static void getQuote(){
        checkIsNewKanyeWestQuote();
    }

    public static String getOPTION(){
        return OPTION;
    }

    public static void setOPTION(String OPTION) {
        KanyeWestQuoteParser.OPTION = OPTION;
    }
}
