package ua.lisovolik.model;

import java.time.LocalDate;

/**
 * Created by Alexandr Lisovolik on  25.09.2024
 */

public class QueryInfo extends BaseDataString {
    private LocalDate dateEnd;


    public QueryInfo(ServiceType serviceType, QuestionType questionType, String responseType,
                     LocalDate dateStart, LocalDate dateEnd) {
        super(LineType.QUERY, serviceType, questionType, dateStart, responseType);
        this.dateEnd = dateEnd;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    @Override
    public String toString() {
        return "OneLine{" + "type=D" +
                "serviceId=" + serviceType.getServiceId() +
                ", varId=" + serviceType.getVariationId() +
                ", questionId=" + questionType.getQuestionTypeId() +
                ", cat=" + questionType.getCategoryId() +
                ", subCat=" + questionType.getSubCategoryId() +
                ", resp='" + responseType +
                ", dat='" + date + "-" + dateEnd +
                '}';
    }
}
