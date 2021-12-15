import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*Trida database na praci s knihovnamu. Tato trida mi eviduje vsechny knihovny a knihy/hry. Ze souboru Knihovny nacte knihovny a vytvori je.
* Pote nacte ze souboru Knihy/hry polozky knihovny a ulozi je podle umisteni do prislusnych knihoven. */
public class Database {
    private String filenameBooks;
    private String filenameBookshelves;
    private List<Bookshelf<ItemOfBookshelf>> bookshelves;

    public Database(String filenameBooks, String filenameBookshelves)
    {
        this.filenameBooks=filenameBooks;
        this.filenameBookshelves=filenameBookshelves;
        this.bookshelves = new ArrayList<>();
    }


    public List<Bookshelf<ItemOfBookshelf>> getBookshelves()
    {
        return this.bookshelves;
    }

    public int findBookshelf(String placement)
    {
        for (Bookshelf<ItemOfBookshelf> bookshelf:this.bookshelves)
        {
            if(bookshelf.getPlace().equals(placement))
            {
                return 0;
            }
        }
        return -1;
    }

    public Bookshelf<ItemOfBookshelf> returnBookshelf(String placement)
    {
        Bookshelf<ItemOfBookshelf> returnBookshelf = new Bookshelf<ItemOfBookshelf>("", 0);
        for (Bookshelf<ItemOfBookshelf> bookshelf:this.bookshelves)
        {
            if(bookshelf.getPlace().equals(placement))
            {
                return bookshelf;
            }
        }
        return returnBookshelf;
    }


    //METODY NA CTENI SOUBORU - ROVNOU MI DATA ROZTRIDI
    //nejprve nactu knihy ze souboru do items - tam uchovavam vsechny knihy a hry
    //prijima seznam mych knihoven
    //Vim kolik mam knihoven a mam soubor s knihama - ten staci jen upravovat - do teto fce predam knihovny a ty budu naplnovat + delat dalsi upravy
    public int readItems(List<Bookshelf<ItemOfBookshelf>> bookshelves) throws Exception
    {
        try{
            FileReader bfile = new FileReader(this.filenameBooks);
            BufferedReader br = new BufferedReader(bfile);
            String i;
            String splitter=";";
            while((i= br.readLine()) != null)
            {
                String[] Item = i.split(splitter);
                if(Item[1].equals("K"))
                {
                    Book book = makeBook(Item);
                    FindRightShelf(bookshelves, book, Item[0]);
                }
                else
                {
                    DeskGame game = makeDeskGame(Item);
                    FindRightShelf(bookshelves, game, Item[0]);
                }
            }
            bfile.close();
        }
        catch (Exception e)
        {
            return -1;
        }
        return 0;
    }

    public int readBookshelves() throws Exception
    {
        try{
            List<Bookshelf<ItemOfBookshelf>> bookshelves = new ArrayList<>();
            //vyhazuje to divnou chybu, kdyz chci cist ze souboru, ktery neexistuje
            FileReader bfile = new FileReader(this.filenameBookshelves);
            BufferedReader br = new BufferedReader(bfile);
            String i;
            String splitter=";";
            while((i= br.readLine()) != null)
            {
                String[] Item = i.split(splitter);
                Bookshelf<ItemOfBookshelf> bookshelf = makeBookshelf(Item);
                bookshelves.add(bookshelf);

            }
            this.bookshelves=bookshelves;
        }
        catch (Exception e)
        {
            return -1;
        }
        return 0;
    }


    public int numOfAllBooks()
    {
        int sum=0;
        for (Bookshelf<ItemOfBookshelf> bookshelf:this.bookshelves)
        {
                sum += bookshelf.numOfBook();
        }
        return sum;
    }

    public int numOfAllGames()
    {
        int sum=0;
        for (Bookshelf<ItemOfBookshelf> bookshelf: this.bookshelves)
        {
            sum += bookshelf.numOfGames();
        }
        return sum;
    }



    /*Pomocne metody na vytvoreni knihy/hry pro cteni ze souboru*/
    public Book makeBook(String[] Item)
    {
        String name = Item[2];
        String author = Item[3];
        int year = Integer.parseInt(Item[4]);
        String ISBN = Item[5];
        Genre genre = Genre.valueOf(Item[6]);
        Boolean read = Boolean.valueOf(Item[7]);
        Book book = new Book(name, author, year, ISBN, genre, read);
        return book;
    }

    public DeskGame makeDeskGame(String[] Item)
    {
        String name = Item[2];
        GameType gameType = GameType.valueOf(Item[3]);
        int year = Integer.parseInt(Item[4]);
        String numOfPlayers = Item[5];
        Genre genre = Genre.valueOf(Item[6]);
        DeskGame game = new DeskGame(year, name, gameType, numOfPlayers, genre);
        return game;
    }

    public Bookshelf<ItemOfBookshelf> makeBookshelf(String[] Item)
    {
        String place=Item[0];
        int capacity = Integer.parseInt(Item[1]);
        Bookshelf<ItemOfBookshelf> bookshelf = new Bookshelf<ItemOfBookshelf>(place, capacity);
        return bookshelf;
    }

    /*metoda, ktera mi najde spravnou knohovnu, do ktere vlozit knizku*/
    public void FindRightShelf(List<Bookshelf<ItemOfBookshelf>> bookshelves, ItemOfBookshelf item, String placement)
    {
        for (Bookshelf<ItemOfBookshelf> bookshelf : bookshelves)
        {
            if(bookshelf.getPlace().equals(placement))
            {
                if(item instanceof Book){bookshelf.InsertBook((Book) item);}
                else{bookshelf.InsertDeskGame((DeskGame) item);}
            }
        }
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Tato databaze obsahuje tolik knihoven:");
        builder.append(this.bookshelves.size());
        builder.append(System.lineSeparator());
        builder.append("Zde jsou podrobnejsi informace:");
        builder.append(System.lineSeparator());
        for (Bookshelf<ItemOfBookshelf> bookshelf:this.bookshelves) {
            builder.append(bookshelf.toString());
            builder.append(System.lineSeparator());
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
