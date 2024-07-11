package battleship;

public class Game {
    public void run() {
        Field field = new Field();
        field.printField(field.getField());

        for (Ship ship : Ship.values()) {
            System.out.printf(Messages.ENTER_SHIP.toString(), ship.getName(), ship.getLength());
            field.placeShip(ship);
            field.printField(field.getField());
        }

        System.out.println(Messages.GAME_STARTS);
        field.printField(field.getFogField());

        System.out.println(Messages.TAKE_SHOT);

        switch (field.takeShot()) {
            case HIT -> {
                field.printField(field.getFogField());
                System.out.println(Messages.HIT);
            }
            case MISS -> {
                field.printField(field.getFogField());
                System.out.println(Messages.MISS);
            }
        }

        field.printField(field.getField());
    }
}
