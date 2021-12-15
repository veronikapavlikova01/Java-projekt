public class Book extends ItemOfBookshelf implements Comparable<Book>{
    public String author;
    public String ISBN;
    public Genre genre;
    //public int numOfPages; - mozne rozsireni do budoucna
    public boolean read;

    /*Obecna trida kniha. Jsou implementovane zakladni metody pro praci s knihou. Ne vsechny jsou aktualne vyuzity, pri pozdejsim rozsirovani je ale pouziji.*/
    public Book(String name, String author, int year, String ISBN, Genre genre, boolean read)
    {
        super(year, name);
        this.author=author;
        this.ISBN=ISBN;
        this.genre=genre;
        this.read=read;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }


    public String getISBN() {
        return ISBN;
    }

    public boolean isRead() {
        return read;
    }


    @Override
    public int compareTo(Book book) {
        return this.name.compareTo(book.name);
    }

    public boolean equals(Object object)
    {
        if (object instanceof Book) {
            Book book = (Book)object;
            if(this.name==book.name && this.author==book.author && this.year==book.year) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Nazev: ");
        builder.append(this.name);
        builder.append(", Autor: ");
        builder.append(this.author);
        builder.append(", Rok: ");
        builder.append(this.year);
        builder.append(", ISBN: ");
        builder.append(this.ISBN);
        builder.append(", Zanr: ");
        builder.append(this.genre);
        builder.append(", Precteno: ");
        builder.append(this.read);
        return builder.toString();
    }

}
