package ua.lisovolik;

import ua.lisovolik.model.BaseDataString;
import ua.lisovolik.model.LineType;
import ua.lisovolik.model.QueryInfo;
import ua.lisovolik.model.WaitingInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;

import static ua.lisovolik.util.Utils.formatNumber;

/**
 * Created by Alexandr Lisovolik on  25.09.2024
 */

public class Storage {
    private static Storage INSTANCE;
    private final List<BaseDataString> list = new ArrayList<>();

    private Storage(){

    }

    public static Storage getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Storage();
        }
        return INSTANCE;
    }

    public void addLine(BaseDataString line) {
        list.add(line);
    }

    private void calculateAvgMinutes(int index, QueryInfo item) {
        OptionalDouble optional = list.stream()
                .limit(index)
                .filter(one -> one.getType()==LineType.WAITING)
                .filter(getPredicateByService(item))
                .filter(getPredicateByQuestion(item))
                .filter(getByResponseType(item))
                .filter(getPredicateByDate(item))
                .mapToInt(o -> ((WaitingInfo)o).getMinutes())
                .average();

        if (optional.isPresent()){
             System.out.println(formatNumber(optional.getAsDouble()));
        } else {
             System.out.println("-");
        }
    }

    private static Predicate<? super BaseDataString> getByResponseType(QueryInfo item) {
        return one -> one.getResponseType().equals(item.getResponseType());
    }

    private static Predicate<? super BaseDataString> getPredicateByQuestion(QueryInfo item) {
        return one -> {
            if (item.getQuestionType().getQuestionTypeId() > 0){
                if (item.getQuestionType().getCategoryId() > 0){
                    if (item.getQuestionType().getSubCategoryId() > 0){
                        return (one.getQuestionType().getQuestionTypeId() == item.getQuestionType().getQuestionTypeId())
                                && (one.getQuestionType().getCategoryId() == item.getQuestionType().getCategoryId())
                                && (one.getQuestionType().getSubCategoryId() == item.getQuestionType().getSubCategoryId());
                    } else {
                        return (one.getQuestionType().getQuestionTypeId() == item.getQuestionType().getQuestionTypeId())
                                && (one.getQuestionType().getCategoryId() == item.getQuestionType().getCategoryId());
                    }
                } else {
                    return (one.getQuestionType().getQuestionTypeId() == item.getQuestionType().getQuestionTypeId());
                }
            } else {
                return true;
            }
        };
    }

    private Predicate<? super BaseDataString> getPredicateByService(QueryInfo item) {
        return one -> {
            if (item.getServiceType().getServiceId() > 0){
                if (item.getServiceType().getVariationId() > 0){
                    return (one.getServiceType().getServiceId() == item.getServiceType().getServiceId())
                    && (one.getServiceType().getVariationId() == item.getServiceType().getVariationId());
                } else {
                    return (one.getServiceType().getServiceId() == item.getServiceType().getServiceId());
                }
            } else {
                return true;
            }
        };
    }

    private  Predicate<? super BaseDataString> getPredicateByDate(QueryInfo item) {
        return one -> {
            if (item.getDateEnd() == null){
                return (item.getDate().equals(one.getDate()));
            } else {
                return ((one.getDate().isEqual(item.getDate()) || one.getDate().isAfter(item.getDate())) &&
                        (one.getDate().isEqual(item.getDateEnd()) || one.getDate().isBefore(item.getDateEnd())));
            }
        };
    }

    public void calculateAndDisplay() {
        for (int i = 0; i < list.size(); i++) {
            BaseDataString item = list.get(i);
            if (item.getType() == LineType.QUERY){
                if (i > 0) calculateAvgMinutes(i, (QueryInfo) item);
            }
        }
    }
}
