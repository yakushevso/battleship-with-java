package battleship;

import java.util.*;

public class Field {
    private final char[][] field;
    private final static int FIELD_SIZE = 10;
    private static final char FOG = '~';
    private static final char SHIP = 'O';
    private final Scanner sc = new Scanner(System.in);

    public Field() {
        field = new char[FIELD_SIZE][FIELD_SIZE];
        initial();
    }

    private void initial() {
        for (char[] chars : field) {
            Arrays.fill(chars, FOG);
        }
    }

    public void display(char[][] field) {
        char letter = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] chars : field) {
            System.out.print(letter++);
            for (char c : chars) {
                System.out.print(" " + c);
            }
            System.out.println();
        }
    }

    public void run() {
        initial();
        display(field);
        System.out.println("Enter the coordinates of the ship:");
        fill();
    }

    public void fill() {
        int[] coord;
        int tmpCnt = 0;
        StringBuilder tmpCrds = new StringBuilder();

        try {
            coord = getCoordinates();
            isPossibleToPlaceShip(coord);

            for (int i = coord[0] - 1; i <= coord[2] - 1; i++) {
                for (int j = coord[1] - 1; j <= coord[3] - 1; j++) {
                    tmpCnt++;
                    tmpCrds.append(" ")
                            .append(Character.toChars(i + 65))
                            .append(j + 1);
                }
            }

            System.out.println("Length: " + tmpCnt);
            System.out.println("Parts:" + tmpCrds);

        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
        }
    }

    private void isPossibleToPlaceShip(int[] coord) {
        if (coord[0] != coord[2] && coord[1] != coord[3]) {
            throw new IllegalArgumentException("Error!");
        } else {
            for (int i = coord[0] - 2; i <= coord[2]; i++) {
                for (int j = coord[1] - 2; j <= coord[3]; j++) {
                    if ((i >= 0 && i < field.length) && (j >= 0 && j < field.length)) {
                        if (field[i][j] == SHIP) {
                            throw new IllegalArgumentException("Error!");
                        }
                    }
                }
            }
        }
    }

    private int[] getCoordinate(String coordinate) {
        int x = coordinate.toUpperCase().charAt(0) - 'A' + 1;

        int y;
        try {
            y = Integer.parseInt(coordinate.substring(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error!");
        }

        if (x < 1 || x > 10 || y < 1 || y > 10) {
            throw new IllegalArgumentException("Error!");
        }

        return new int[]{x, y};
    }

    private int[] getCoordinates() {
        int[] coordinate1 = getCoordinate(sc.next());
        int[] coordinate2 = getCoordinate(sc.next());

        if (coordinate1[1] > coordinate2[1] || coordinate1[0] > coordinate2[0]) {
            return new int[]{coordinate2[0], coordinate2[1], coordinate1[0], coordinate1[1]};
        } else {
            return new int[]{coordinate1[0], coordinate1[1], coordinate2[0], coordinate2[1]};
        }
    }
}
