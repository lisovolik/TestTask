package ua.lisovolik.model;

/**
 * Created by Alexandr Lisovolik on  26.09.2024
 */

public class QuestionType {
    private final int questionTypeId;
    private final int categoryId;
    private final int subCategoryId;

    public QuestionType(int questionTypeId, int categoryId, int subCategoryId) {
        this.questionTypeId = questionTypeId;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }
}
