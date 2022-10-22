import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class KanyeWestQuoteParser {

    private static final Set<String> quotes = new HashSet<>();
    private static String OPTION;
    private static final int QUOTES_NUMBER = getJsonFileSize();
    private static int searchingForNewQuoteCount = 0;

    private static String parseNewKanyeWestPost(String post) {
        JSONObject jsonPost = new JSONObject(post);
        return jsonPost.getString("quote");
    }

    private static String checkIsNewKanyeWestQuote() {

        try {
            String newKanyeWestQuote = parseNewKanyeWestPost(KanyeWestGetPost.getPost());
            String codeQuote = getQuoteCode(newKanyeWestQuote);
            if (searchingForNewQuoteCount == 50){
                searchingForNewQuoteCount = 0;
                return "next";
            } else if (QUOTES_NUMBER != 0 && quotes.size() == QUOTES_NUMBER) {
                return "end";
            } else if (!quotes.contains(codeQuote)) {
                quotes.add(codeQuote);
                searchingForNewQuoteCount = 0;
                return '"' + newKanyeWestQuote + '"';
            }
        }catch (NullPointerException e){
            System.out.println("Nie udało się pobrać nowego cytatu. Błąd połączenia z serwerem!\n" +
                    "Zamykanie programu!");
            return "EXIT";
        }
        searchingForNewQuoteCount++;
        return checkIsNewKanyeWestQuote();
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
            return 0;
        }
    }

    public static int getNumberOfQuotes(){return getJsonFileSize();}

    public static String getQuote(){
     return checkIsNewKanyeWestQuote();
    }

    public static String getOPTION(){
        return OPTION;
    }

    public static void setOPTION(String OPTION) {
        KanyeWestQuoteParser.OPTION = OPTION;
    }
}
