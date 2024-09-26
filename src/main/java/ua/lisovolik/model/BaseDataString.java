package ua.lisovolik.model;

import java.time.LocalDate;

/**
 * Created by Alexandr Lisovolik on  25.09.2024
 */

public abstract class BaseDataString {
    protected ServiceType serviceType;
    protected QuestionType questionType;
    protected LineType type; // 'C' or 'D'
    protected String responseType; //'P' or 'N'

    protected LocalDate date;

    public BaseDataString(LineType type, ServiceType serviceType, QuestionType questionType, LocalDate date, String responseType) {
        this.type = type;
        this.serviceType = serviceType;
        this.questionType = questionType;
        this.date = date;
        this.responseType = responseType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public LineType getType() {
        return type;
    }

    public String getResponseType() {
        return responseType;
    }

    public LocalDate getDate() {
        return date;
    }
}
