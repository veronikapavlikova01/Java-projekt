import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException
    {
        /*
        Dobry den, posilam muj projekt z Javy.
        Do budoucna planuji jeste nasledujici rozsireni:
        -- aby uzivatel mohl pridat knihu/hru
        --aby uzivatel mohl odstranit knihu/hru
        --to same potom s knihovnami
        --moznost nechat si knihy setridit
        */
        /*
        //nazvy mych souboru
        String bookFile = "D:\\Users\\verup\\Documents\\Github\\Java - projekt\\.idea\\Knihy.csv";
        String bookshelvesFile = "D:\\Users\\verup\\Documents\\Github\\Java - projekt\\.idea\\Knihovny.csv";
        */

        SimpleUI UI= new SimpleUI();
        UI.loop();
    }
}
