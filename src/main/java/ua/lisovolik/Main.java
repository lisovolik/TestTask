package ua.lisovolik;


import static ua.lisovolik.util.Utils.getTestData;

/**
 * Created by Alexandr Lisovolik on  25.09.2024
 */

public class Main {
    public static void main(String[] args) {
        parseStrings();
    }

    public static void parseStrings(){
        String[] lines = getTestData();

        DataParser parser = DataParser.getInstance();
        parser.parseList(lines);

        Storage.getInstance().calculateAndDisplay();
    }
}