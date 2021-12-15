import java.util.ArrayList;
import java.util.List;

public class Bookshelf <T extends ItemOfBookshelf>{
    public String placement;
    public int capacity;
    private int freeSpace;
    //zavadim dve odlisne casti pro knihy a hry - tak to mam i ve sve knihovne, hry mam zvlast od knizek
    //pri dalsim rozsireni staci zavest opet dalsi arraylist
    //pripadne lze toto rozsireni zrusit ale nebudu schopna použivat nektere komparatory..coz ale nyni chci
    private ArrayList<Book> books;
    private ArrayList<DeskGame> games;
    private ArrayList<String> authors;

    /*Obecna trida knihovna - evisuji si zvlast knihy a hry a pro rychlejsi kontrolu take autory. Pri vkladani hlidam, jestli nebyla prekrocena kapacita knihovny.
    * do budoucna planovane rozsireni, aby i uzivatel mohl vlozit svou knihu, pripadne nejakou knihu smazat.*/

    public Bookshelf(String place, int capacity)
    {
        this.books = new ArrayList<Book>();
        this.games=new ArrayList<DeskGame>();
        this.authors = new ArrayList<String>();
        this.placement = place;
        this.capacity = capacity;
        this.freeSpace = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public String getPlace() {
        return placement;
    }

    public ArrayList<Book> getBooks()
    {
        ArrayList<Book> output;
        output = this.books;
        return output;
    }

    public ArrayList<DeskGame> getGames()
    {
        ArrayList<DeskGame> output;
        output = this.games;
        return games;
    }

    public int findAuthors(String author)
    {
        if(this.authors.contains(author))
        {
            return 0;
        }
        return -1;
    }

    public int numOfBook()
    {
        return this.books.size();
    }

    public int numOfGames()
    {
        return this.games.size();
    }

    public List<Book> BooksOfGenre(Genre genre)
    {
        List<Book> books = new ArrayList<>();
        for (Book book: this.books)
        {
            if(book.genre==genre)
            {
                books.add(book);
            }
        }
        return books;
    }

    public List<Book> BooksByAuthor(String author)
    {
        List<Book> books = new ArrayList<Book>();
        for (Book book:this.books)
        {
            if(book.author.equals(author))
            {
                books.add(book);
            }
        }
        return books;
    }

    public List<Book> ReadOrUnreadBooks(boolean read)
    {
        List<Book> books = new ArrayList<>();
        for (Book book:this.books)
        {
            if(book.read==read)
            {
                books.add(book);
            }
        }
        return books;
    }

    public void sortByAuthor()
    {
        BookAuthorComparator comparator = new BookAuthorComparator();
        this.books.sort(comparator);
    }

    public void InsertBook(Book book)
    {
        if(freeSpace>0)
        {
            this.books.add(book);
            this.freeSpace--;
        }
        this.authors.add(book.author);
    }

    public void DeleteBook(Book book)
    {
        if(freeSpace != capacity)
        {
            this.books.remove(book);
            this.freeSpace++;
        }
    }

    public void InsertDeskGame(DeskGame game)
    {
        if(freeSpace>0)
        {
            this.games.add(game);
            this.freeSpace--;
        }
    }

    public void DeleteDeskGame(DeskGame game)
    {
        if(freeSpace !=capacity)
        {
            this.games.remove(game);
            this.freeSpace++;
        }
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Tato knihovna se nachazi v miste: ");
        builder.append(this.placement);
        builder.append(System.lineSeparator());
        builder.append("Jeji kapacita je: ");
        builder.append(this.capacity);
        builder.append(System.lineSeparator());
        builder.append("Vejde se do ni jeste tolik knih/her: ");
        builder.append(this.freeSpace);
        builder.append(System.lineSeparator());
        builder.append("Obsahuje tyto knihy: ");
        builder.append(System.lineSeparator());
        for (Book book: this.books) {
            builder.append(book.toString());
            builder.append(System.lineSeparator());
        }
        builder.append(System.lineSeparator());
        builder.append("Obsahuje tyto hry: ");
        for (DeskGame game: this.games) {
            builder.append(game.toString());
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

}
