import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KanyeWestQuoteParserTest {

    @Test
    public void getQuoteFromApiTest(){
        String q1 = KanyeWestGetPost.getPost();

        Assertions.assertNotNull(q1);
    }

    @Test
    public void getJsonDataBaseQuoteFileSize(){
        int size = KanyeWestQuoteParser.getNumberOfQuotes();
        int expected = 122;
        Assertions.assertEquals(expected, size);
    }

    @Test
    public void getUniquePostTest(){
        int size = KanyeWestQuoteParser.getNumberOfQuotes();
        String quote1 = KanyeWestQuoteParser.getQuote();
        String[] quotes = new String[size];

        for (String q : quotes) {
            q = KanyeWestQuoteParser.getQuote();
            Assertions.assertNotEquals(quote1,q);
        }
    }
}

