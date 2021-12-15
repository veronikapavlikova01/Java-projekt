public class DeskGame extends ItemOfBookshelf implements Comparable<DeskGame> {
    public GameType type;
    public String numOfPlayers;
    public Genre genre;

    public DeskGame(int year, String name, GameType type, String pocetHracu, Genre genre) {
        super(year, name);
        this.type=type;
        this.numOfPlayers=pocetHracu;
        this.genre=genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getPocetHracu() {
        return numOfPlayers;
    }

    public GameType getType() {
        return type;
    }

    @Override
    public int compareTo(DeskGame deskGame) {
        return this.name.compareTo(deskGame.getName());
    }

    //u her nepotrebuji dalsi komparator - respektive nemam jak jinak je rovnat nez podle jmena

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Nazev: ");
        builder.append(this.name);
        builder.append(", Rok: ");
        builder.append(this.year);
        builder.append(", Typ: ");
        builder.append(this.type);
        builder.append(", Zanr: ");
        builder.append(this.genre);
        builder.append(", Pocet hracu: ");
        builder.append(this.numOfPlayers);
        return builder.toString();
    }
}
