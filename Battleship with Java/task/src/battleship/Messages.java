package battleship;

public enum Messages {
    ENTER_SHIP("Enter the coordinates of the %s (%d cells):\n"),
    ERROR_INPUT("Error! You entered the wrong coordinates! Try again:\n"),
    ERROR_LENGTH("Error! Wrong length of the %s! Try again:\n"),
    ERROR_LOCATION("Error! Wrong ship location! Try again:\n"),
    ERROR_CLOSE("Error! You placed it too close to another one. Try again:\n"),
    GAME_STARTS("The game starts!"),
    TAKE_SHOT("Take a shot!"),
    HIT("You hit a ship!"),
    MISS("You missed!"),
    WON("You sank the last ship. You won. Congratulations!"),
    SANK("You sank a ship! Specify a new target:");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
