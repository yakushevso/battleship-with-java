package battleship;

public class Player {
    private final Field field;
    private final String name;

    public Player(String name) {
        this.field = new Field();
        this.name = name;
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return name;
    }
}