import java.util.Objects;
import java.util.Scanner;

public class KanyeWestQuoteApp {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        while (!Objects.equals(KanyeWestQuoteParser.getOPTION(), "EXIT")){
            printOption();
            KanyeWestQuoteParser.setOPTION(scanner.nextLine().toUpperCase());
            switch (KanyeWestQuoteParser.getOPTION()) {
                case "NEXT" -> KanyeWestQuoteParser.getQuote();
                case "EXIT" -> System.out.println("Koniec programu!");
                default -> System.out.println("Nie ma takiej opcji!");
            }
        }
        scanner.close();
    }

    private static void printOption(){
        System.out.println();
        System.out.println("GENERATOR CYTATÓW KANE WESTA!");
        System.out.println("Podaj jedną z dostępnych komend:");
        System.out.println("NEXT -> Wygeneruj nowy cytat Kanye Westa");
        System.out.println("EXIT -> Wyjście z programu");
    }
}
