package battleship;

public class Game {
    public void run() {
        Field field = new Field();
        field.clearField();
        field.printField();

        for (Ship ship : Ship.values()) {
            System.out.printf(Messages.ENTER_SHIP.toString(), ship.getName(), ship.getLength());
            field.placeShip(ship);
            field.printField();
        }

        System.out.println(Messages.GAME_STARTS);
        field.setCopyField();
        field.clearField();
        field.printField();
        System.out.println(Messages.TAKE_SHOT);
        field.takeShot();
        field.setField();
        field.printField();
    }
}
