package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Field {
    private char[][] field;
    private char[][] fogField;
    private final static int FIELD_SIZE = 10;
    private static final char FOG = '~';
    private static final char SHIP = 'O';
    private static final char HIT = 'X';
    private static final char MISS = 'M';
    private Ship[] ships;

    private final Scanner sc = new Scanner(System.in);

    public void init() {
        field = new char[FIELD_SIZE][FIELD_SIZE];
        fogField = new char[FIELD_SIZE][FIELD_SIZE];

        for (char[] chars1 : field) {
            Arrays.fill(chars1, FOG);
        }

        for (char[] chars : fogField) {
            Arrays.fill(chars, FOG);
        }

        ships = new Ship[]{
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Cruiser", 3),
                new Ship("Destroyer", 2)};
    }

    public void print(boolean fogOfWar) {
        char letter = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] chars : fogOfWar ? fogField : field) {
            System.out.print(letter++);
            for (char c : chars) {
                System.out.print(" " + c);
            }
            System.out.println();
        }
    }

    public void placeShips() {
        for (Ship ship : ships) {
            System.out.printf(Messages.ENTER_SHIP.toString(), ship.getName(), ship.getLength());
            while (true) {
                try {
                    int[] coord = getCoordinates();
                    validateShipPlacement(coord, ship);
                    placeShipOnField(coord, ship);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.print(e.getMessage());
                }
            }
            print(false);
        }
    }

    private void validateShipPlacement(int[] coord, Ship ship) {
        if (coord[0] != coord[2] && coord[1] != coord[3]) {
            throw new IllegalArgumentException(Messages.ERROR_LOCATION.toString());
        }
        if ((Math.abs(coord[1] - coord[3]) + 1 != ship.getLength()) &&
                (Math.abs(coord[0] - coord[2]) + 1 != ship.getLength())) {
            throw new IllegalArgumentException(String.format(Messages.ERROR_LENGTH.toString(), ship.getName()));
        }
        checkSurroundingArea(coord);
    }

    private void checkSurroundingArea(int[] coord) {
        for (int i = coord[0] - 2; i <= coord[2]; i++) {
            for (int j = coord[1] - 2; j <= coord[3]; j++) {
                if (isWithinBounds(i, j) && field[i][j] == SHIP) {
                    throw new IllegalArgumentException(Messages.ERROR_CLOSE.toString());
                }
            }
        }
    }

    private boolean isWithinBounds(int i, int j) {
        return (i >= 0 && i < FIELD_SIZE) && (j >= 0 && j < FIELD_SIZE);
    }

    private void placeShipOnField(int[] coord, Ship ship) {
        for (int i = coord[0] - 1; i <= coord[2] - 1; i++) {
            for (int j = coord[1] - 1; j <= coord[3] - 1; j++) {
                field[i][j] = SHIP;
            }
        }
        ship.setCoordinate(coord);
    }

    public boolean takeShot() {
        int[] coord;

        try {
            coord = getCoordinate(sc.next());

            switch (field[coord[0] - 1][coord[1] - 1]) {
                case SHIP -> {
                    field[coord[0] - 1][coord[1] - 1] = HIT;
                    fogField[coord[0] - 1][coord[1] - 1] = HIT;

                    if (isSunk()) {
                        int count = 0;
                        for (Ship ship : ships) {
                            if (ship.isChecked()) {
                                if (++count == 5) {
                                    System.out.println(Messages.WON);
                                    return true;
                                }
                            }
                        }

                        System.out.println(Messages.SANK);
                        return false;
                    } else {
                        System.out.println(Messages.HIT);
                    }
                }
                case FOG -> {
                    field[coord[0] - 1][coord[1] - 1] = MISS;
                    fogField[coord[0] - 1][coord[1] - 1] = MISS;
                    System.out.println(Messages.MISS);
                }
                case HIT -> System.out.println(Messages.HIT_SHOT);
                case MISS -> System.out.println(Messages.HIT_MISS);
            }
        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
        }

        return false;
    }

    private boolean isSunk() {
        for (Ship ship : ships) {
            int count = 0;

            if (!ship.isChecked()) {
                for (int i = ship.getCoordinate()[0] - 1; i <= ship.getCoordinate()[2] - 1; i++) {
                    for (int j = ship.getCoordinate()[1] - 1; j <= ship.getCoordinate()[3] - 1; j++) {
                        if (field[i][j] == HIT) {
                            if (++count == ship.getLength()) {
                                ship.setChecked(true);
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private int[] getCoordinate(String coord) {
        int x = coord.toUpperCase().charAt(0) - 'A' + 1;

        int y;
        try {
            y = Integer.parseInt(coord.substring(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Messages.ERROR_INPUT.toString());
        }

        if (x < 1 || x > 10 || y < 1 || y > 10) {
            throw new IllegalArgumentException(Messages.ERROR_INPUT.toString());
        }

        return new int[]{x, y};
    }

    private int[] getCoordinates() {
        int[] coord1 = getCoordinate(sc.next());
        int[] coord2 = getCoordinate(sc.next());
        return (coord1[1] > coord2[1] || coord1[0] > coord2[0])
                ? new int[]{coord2[0], coord2[1], coord1[0], coord1[1]}
                : new int[]{coord1[0], coord1[1], coord2[0], coord2[1]};
    }
}
