package ua.lisovolik.model;

import java.time.LocalDate;

/**
 * Created by Alexandr Lisovolik on  25.09.2024
 */

public class WaitingInfo extends BaseDataString {
    private int minutes;

    public WaitingInfo(ServiceType serviceType, QuestionType questionType, String responseType, LocalDate date, int minutes) {
        super(LineType.WAITING, serviceType, questionType, date, responseType);
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public String toString() {
        return "OneLine{" + "type=C" +
                "serviceId=" + serviceType.getServiceId() +
                ", varId=" + serviceType.getVariationId() +
                ", questionId=" + questionType.getQuestionTypeId() +
                ", cat=" + questionType.getCategoryId() +
                ", subCat=" + questionType.getSubCategoryId() +
                ", resp='" + responseType +
                ", dat='" + date + "-"  +
                ", min='" + minutes +
                '}';
    }
}
