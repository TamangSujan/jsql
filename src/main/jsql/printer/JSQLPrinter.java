package jsql.printer;

import jsql.out.Field;
import jsql.out.Row;
import jsql.out.Table;
import jsql.utils.StringUtils;

import java.util.*;

public class JSQLPrinter {
    private final char horizontalSeparator;
    private final char verticalSeparator;
    private final StringBuilder outputBuffer;

    public JSQLPrinter(char horizontalSeparator, char verticalSeparator){
        this.horizontalSeparator = horizontalSeparator;
        this.verticalSeparator = verticalSeparator;
        outputBuffer = new StringBuilder();
    }

    public void print(Table table){
        if(!Objects.isNull(table) && !table.getRows().isEmpty()){
            createTable(table.getRows());
            System.out.println(outputBuffer);
            clearBuffer();
        }
    }

    public void createTable(List<Row> rows){
        Map<String, Integer> fieldGapValues = getFieldsGapMap(rows);
        String horizontalLine = getHorizontalLine(fieldGapValues.values());
        addHeaderDataToBuffer(rows.get(0), fieldGapValues);
        addHorizontalLineToBuffer(horizontalLine);
        addRowDataToBuffer(rows, fieldGapValues);
    }

    private void addHeaderDataToBuffer(Row headerRow, Map<String, Integer> fieldGapValues){
        outputBuffer.append(verticalSeparator);
        for(Field field: headerRow.getFields())
            outputBuffer.append(getStringWithPadding(field.getName(), fieldGapValues.get(field.getName())))
                    .append(verticalSeparator);
        outputBuffer.append('\n');
    }

    private void addHorizontalLineToBuffer(String horizontalLine){
        outputBuffer.append(horizontalLine).append('\n');
    }

    private void addRowDataToBuffer(List<Row> rows, Map<String, Integer> fieldGapValues){
        for(Row row: rows){
            outputBuffer.append(verticalSeparator);
            for(Field field: row.getFields())
                outputBuffer.append(getStringWithPadding(field.getValue(), fieldGapValues.get(field.getName())))
                        .append(verticalSeparator);
            outputBuffer.append('\n');
        }
    }

    private String getStringWithPadding(String value, int totalSize){
        return value +
                StringUtils.getRepeatedValue(" ", totalSize - value.length());
    }

    private String getHorizontalLine(Collection<Integer> fieldGapValues) {
        int separatorCount = fieldGapValues.size() + 1;
        for(int gapValue: fieldGapValues)
            separatorCount += gapValue;
        return StringUtils.getRepeatedValue(String.valueOf(horizontalSeparator), separatorCount);
    }

    private Map<String, Integer> getFieldsGapMap(List<Row> rows){
        List<Field> totalFields = rows.get(0).getFields();
        Map<String, Integer> fieldsGapMap = new HashMap<>(totalFields.size());
        initMaxFieldGapMap(fieldsGapMap, totalFields);
        updateMaxFieldGapMap(fieldsGapMap, rows);
        return fieldsGapMap;
    }

    private void initMaxFieldGapMap(Map<String, Integer> fieldsGapMap, List<Field> totalFields){
        totalFields.forEach(field -> fieldsGapMap.put(field.getName(), field.getName().length()));
    }

    private void updateMaxFieldGapMap(Map<String, Integer> maxFieldGapMap, List<Row> rows){
        List<Field> totalFields = rows.get(0).getFields();
        String currentFieldName;
        for(int currentFieldIndex=0; currentFieldIndex < totalFields.size(); currentFieldIndex++){
            currentFieldName = totalFields.get(currentFieldIndex).getName();
            int currentFieldMaxValue = getMaxLengthFromFields(
                    rows,
                    currentFieldIndex,
                    maxFieldGapMap.get(currentFieldName));
            maxFieldGapMap.put(currentFieldName, currentFieldMaxValue);
        }
    }

    private int getMaxLengthFromFields(List<Row> rows, int fieldIndex, int startValue){
        for(Row row: rows){
            Field field = row.getFields().get(fieldIndex);
            if(startValue <= field.getValue().length())
                startValue = field.getValue().length();
        }
        return startValue;
    }

    private void clearBuffer(){
        outputBuffer.delete(0, outputBuffer.length());
    }
}
