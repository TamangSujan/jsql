package jsql.validator;

import jsql.exception.JSQLParserException;

import java.util.LinkedList;
import java.util.List;

public class SelectParser {
    public static List<String> getFieldsName(String query){
        if(query.contains(" FROM "))
            return getFieldsNameByParsing(query);
        throw new JSQLParserException("Invalid query!");
    }

    private static List<String> getFieldsNameByParsing(String query){
        String parsedField = query.substring("SELECT ".length(), query.indexOf(" FROM "));
        if(!parsedField.contains("*"))
            return getFieldsByTrimming(parsedField.split(","));
        throw new JSQLParserException("Invalid query, * is not supported!");
    }

    private static List<String> getFieldsByTrimming(String[] fields){
        List<String> fieldsList = new LinkedList<>();
        for(String field: fields)
            fieldsList.add(getFieldNameOrAlias(field.trim()));
        return fieldsList;
    }

    private static String getFieldNameOrAlias(String field){
        if(field.contains(" AS "))
            return field.split(" AS ")[1];
        return field;
    }
}
