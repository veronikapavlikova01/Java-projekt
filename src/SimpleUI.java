import java.util.List;
import java.util.Scanner;

/*Trida na praci s databazi a knihovnami. Pta se uzivatele na nazev souboru, kde ma ulozene knihovny a hry, podle tohoutvori databazi a tu si ulozi.
 Databaze si pote sama polozky do knihoven roztridi. SimpleUI pote uzivatele informuje jake akce lze nad databazi provadet a zepta se na akci, kterou chce uzivatel provest.*/
public class SimpleUI {
    Database database;

    public SimpleUI()
    {
    }

    public void loop()
    {
        int end=1;
        String nextMove;
        String bookshelvesFile = "";
        String bookFile="";
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Dobry den, vitejte ve sve databazi knih.");
            System.out.println("Zadejte prosim nazev souboru, kde mate ulozen seznam knihoven");
            bookshelvesFile = scanner.nextLine();
            System.out.println("Dekuji. Nyni prosim zadejte nazev souboru, kde mate ulozene knihy/hry.");
            bookFile = scanner.nextLine();
            System.out.println("Dekuji. Nacitam Vasi knihovnu...");
            this.database = new Database(bookFile, bookshelvesFile);
            int bookshelves = this.database.readBookshelves();
            int items = this.database.readItems(this.database.getBookshelves());
            while(bookshelves ==-1 || items == -1)
            {
                System.out.println("Ouou, S Vami zadanymi soubory se nelze spojit. Zkuste to znovu.");
                System.out.println("Zadejte prosim nazev souboru, kde mate ulozen seznam knihoven");
                bookshelvesFile = scanner.nextLine();
                System.out.println("Dekuji. Nyni prosim zadejte nazev souboru, kde mate ulozene knihy/hry.");
                bookFile = scanner.nextLine();
                this.database = new Database(bookFile, bookshelvesFile);
                bookshelves = this.database.readBookshelves();
                items = this.database.readItems(this.database.getBookshelves());
            }

            System.out.println("Poslusne hlasim, ze soubor s knihovnami byl uspesne nacten!");
            while (end == 1) {
                printDatabaseMenu();
                nextMove = scanner.next();
                switch (nextMove) {
                    case "1":
                        System.out.print("Celkovy pocet knih je: ");
                        System.out.println(this.database.numOfAllBooks());
                        break;
                    case "2":
                        System.out.print("Celkovy pocet her je: ");
                        System.out.println(this.database.numOfAllGames());
                        break;
                    case "3":
                        System.out.println("Zadejte umisteni knihovny, o ktere chcete zjistit podrobnejsi informace");
                        String placement = scanner.next();
                        int found = this.database.findBookshelf(placement);
                        while(found==-1)
                        {
                            System.out.println("Takova knihovna neexistuje. Zkuste to znovu.");
                            System.out.println("Zadejte umisteni knihovny, o ktere chcete zjistit podrobnejsi informace");
                            placement = scanner.next();
                            found = this.database.findBookshelf(placement);
                        }
                        System.out.println("Dekuji. Prepojuji Vas do Vasi kniovny.");
                        Bookshelf<ItemOfBookshelf> returnbookshelf = this.database.returnBookshelf(placement);
                        this.bookshelfLoop(returnbookshelf);
                        break;
                    case "4":
                        System.out.println("Neplecha ukoncena");
                        return;
                }

            }
            //prepsat databazi az se doplni vkladani/odstranovani
        }
        catch (Exception e)
        {
            System.out.println("Kde asi udelali soudruzi z NDR chybu...");
            return;
        }
    }

    public void bookshelfLoop(Bookshelf<ItemOfBookshelf> bookshelf)
    {
        int end=1;
        String nextMove;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dobry den, vitam Vas ve sve knihovne");
        //do budoucna umoznit vkladat knihy
        while (end != 4) {
            printBookshelfMenu();
            nextMove = scanner.next();
            switch (nextMove) {
                case "1":
                    System.out.print("Celkovy pocet knih v teto knihovne je:");
                    System.out.println(bookshelf.numOfBook());
                    break;
                case "2":
                    System.out.print("Celkovy pocet her v teto knihovne je:");
                    System.out.println(bookshelf.numOfGames());
                    break;
                case "3":
                    System.out.println("Zobrazuji vsechny zanry: beletrie, biografie, kucharka, klasika, komiks, detektivka, drama, fantasy, horror, poezie, romance, scifi, osobni_rozvoj, sport, young_adult");
                    System.out.println("Zadejte nazev zanru.");
                    String genre = scanner.next();
                    int check=-1;
                    while(check==-1)
                    {
                        System.out.print("Zanr tohoto nazvu neexistuje. Zkuste to znovu.");
                        System.out.println("Zadejte nazev zanru.");
                        genre = scanner.next();
                        for (Genre g:Genre.values())
                        {
                            if(g.name().equalsIgnoreCase(genre))
                            {
                                check=0;
                            }
                        }
                    }
                    Genre g = Genre.valueOf(genre);
                    List<Book> books=bookshelf.BooksOfGenre(g);
                    for (Book book:books)
                    {
                        System.out.println(book);
                    }
                    break;
                case "4":
                    System.out.println("Zadejte jmeno a prijmeni autora.");
                    //pridat jeste loop na kontrolu autora?
                    String name = scanner.next();
                    int i=-1;
                    while(i==-1)
                    {
                        System.out.print("Od tohoto autora v knihovne zadne knihy nejsou. Zkuste to znovu.");
                        System.out.println("Zadejte jmeno a prijmeni autora.");
                        //pridat jeste loop na kontrolu autora?
                        name = scanner.next();
                        i=bookshelf.findAuthors(name);
                    }
                    List<Book> authorsBooks=bookshelf.BooksByAuthor(name);
                    for (Book book:authorsBooks)
                    {
                        System.out.println(book);
                    }
                    break;
                case "5":
                    List<Book> readBooks=bookshelf.ReadOrUnreadBooks(true);
                    for (Book book:readBooks)
                    {
                        System.out.println(book);
                    }
                    break;
                case "6":
                    List<Book> unreadBooks=bookshelf.ReadOrUnreadBooks(false);
                    for (Book book:unreadBooks)
                    {
                        System.out.println(book);
                    }
                    break;
                case "7":
                    System.out.print(bookshelf.toString());
                    break;
                case "8":
                    System.out.println("Neplecha ukoncena");
                    return;
            }
        }
    }

    public void printBookshelfMenu()
    {
        System.out.println("Vyberte prosim operaci, kterou chcete provest.");
        System.out.println("1. Celkovy pocet knih");
        System.out.println("2. Celkovy pocet her");
        System.out.println("3. Zobrazit vsechny knihy urciteho zanru.");
        System.out.println("4. Zobrazit vsechny knihy urciteho autora.");
        System.out.println("5. Zobrazit prectene knihy.");
        System.out.println("6. Zobrazit neprectene knihy");
        System.out.println("7. Zobrazit informace o knihovne");
        System.out.println("8. Ukoncit praci s knihovnou");
    }

    public void printDatabaseMenu()
    {
        System.out.println("Vyberte prosim operaci, kterou chcete provest.");
        System.out.println("1. Celkovy pocet knih");
        System.out.println("2. Celkovy pocet her");
        System.out.println("3. Zjistit podrobnejsi informace o urcite knihovne");
        System.out.println("4. Ukoncit praci s knihovnou");
        System.out.println("Zadejte Vami zvolenou operaci: ");
    }


}
