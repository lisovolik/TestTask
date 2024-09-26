package ua.lisovolik.model;

/**
 * Created by Alexandr Lisovolik on  26.09.2024
 */

public enum LineType {
    WAITING('C'), QUERY('D');

    private final char asChar;

    LineType(char asChar) {
        this.asChar = asChar;
    }

    public char asChar() {
        return asChar;
    }


}
