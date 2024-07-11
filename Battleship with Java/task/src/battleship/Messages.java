package battleship;

public enum Messages {
    ENTER_SHIP("Enter the coordinates of the %s (%d cells):\n"),
    ERROR_INPUT("Error! You entered the wrong coordinates! Try again:"),
    ERROR_LENGTH("Error! Wrong length of the %s! Try again:\n"),
    ERROR_LOCATION("Error! Wrong ship location! Try again:"),
    ERROR_CLOSE("Error! You placed it too close to another one. Try again:");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
