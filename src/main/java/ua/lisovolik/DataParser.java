package ua.lisovolik;

import ua.lisovolik.model.*;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.lisovolik.util.Utils.logError;
import static ua.lisovolik.util.Utils.parseDate;

/**
 * Created by Alexandr Lisovolik on  26.09.2024
 */

public class DataParser {
    private static DataParser INSTANCE;

    private final String regexC = "C\\s((?:[1-9]|10)(?:\\.(?:[1-3]))?)\\s((?:[1-9]|10)(?:\\.(?:[1-9]|1[0-9]|20))?(?:\\.(?:[1-5]))?)\\s([PN])\\s(\\d{1,2}\\.\\d{1,2}\\.\\d{4})\\s(\\d+)";
    private final String regexD = "D\\s((?:[1-9]|10)(?:\\.(?:[1-3]))?|\\*)\\s((?:\\*|(?:[1-9]|10)(?:\\.(?:[1-9]|1[0-9]|20))?(?:\\.(?:[1-5]))?))\\s([PN])\\s(\\d{1,2}\\.\\d{1,2}\\.\\d{4})(?:-(\\d{1,2}\\.\\d{1,2}\\.\\d{4}))?";

    private final Pattern patternC = Pattern.compile(regexC);
    private final Pattern patternD = Pattern.compile(regexD);

    private DataParser() {
    }

    public static DataParser getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataParser();
        }
        return INSTANCE;
    }

    public BaseDataString parseOneLine(String line)  {
        if (line.startsWith("C")){
            return parseServices(line);
        } else if (line.startsWith("D")){
            return parseQueries(line);
        }
        return null;
    }

    private QueryInfo parseQueries(String line) {
        Matcher matcher= patternD.matcher(line);
        if (matcher.find()) {
            String date2 = matcher.group(5);

            LocalDate dateStart = parseDate(matcher.group(4));
            LocalDate dateEnd = (date2 != null) ? parseDate(date2) : null;
            return new QueryInfo(
                    getServiceType(matcher.group(1)),
                    getQuestionType(matcher.group(2)),
                    matcher.group(3),
                    dateStart,
                    dateEnd);
        } else {
            logError("Unable to parse string: " + line );
            return null;
        }
    }

    private WaitingInfo parseServices(String line) {
        Matcher matcher= patternC.matcher(line);
        if (matcher.find()) {
            return new WaitingInfo(
                    getServiceType(matcher.group(1)),
                    getQuestionType(matcher.group(2)),
                    matcher.group(3),
                    parseDate(matcher.group(4)),
                    Integer.parseInt(matcher.group(5)));
        } else {
            logError("Unable to parse string: " + line );
            return null;
        }
    }

    private ServiceType getServiceType(String serviceTypeString) {
        String[] parts = serviceTypeString.split("\\.");
        int serviceId = (parts[0].equals("*") ? 0 : Integer.parseInt(parts[0]));
        int variationId = (parts.length > 1) ? Integer.parseInt(parts[1]) : 0;

        return new ServiceType(serviceId, variationId);
    }

    private QuestionType getQuestionType(String questionTypeString) {
        String[] parts = questionTypeString.split("\\.");
        int questionTypeId = (parts[0].equals("*") ? 0 : Integer.parseInt(parts[0]));
        int categoryId = (parts.length > 1) ? Integer.parseInt(parts[1]) : 0;
        int subCategoryId = (parts.length > 2) ? Integer.parseInt(parts[2]) : 0;

        return new QuestionType(questionTypeId, categoryId, subCategoryId);
    }

    public void parseList(String[] lines) {
        Storage storage = Storage.getInstance();

        for (String line: lines) {
            BaseDataString item = parseOneLine(line);
            if (item != null){
                storage.addLine(item);
            }
        }
    }
}
