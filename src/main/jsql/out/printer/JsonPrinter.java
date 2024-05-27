package jsql.out.printer;

import jsql.out.model.Field;
import jsql.out.model.Row;
import jsql.out.model.Table;
import jsql.out.model.meta.JsonDataType;

class JsonPrinter {
    private StringBuilder jsonBuffer = new StringBuilder();

    public String getJson(Table table){
        table.getRows().forEach(row -> {
            addObjectToJsonBufferFromRow(row, table);
        });
        removeEndCommaValue();
        wrapWithBigBraces();
        return jsonBuffer.toString();
    }

    private void wrapWithBigBraces(){
        jsonBuffer = new StringBuilder(jsonBuffer.length())
                .append("[").append(jsonBuffer).append("]");
    }

    private void removeEndCommaValue(){
        if(jsonBuffer.toString().endsWith(","))
            jsonBuffer.delete(jsonBuffer.length() - 1, jsonBuffer.length());
    }

    private void addKeyInObject(Field field){
        jsonBuffer.append("\"").append(field.getName()).append("\": ");
    }

    private void addValueInObject(Field field, JsonDataType jsonDataType){
        jsonBuffer.append(jsonDataType.getValueWrapper())
                .append(field.getValue())
                .append(jsonDataType.getValueWrapper())
                .append(",");
    }

    private void addObjectToJsonBufferFromRow(Row row, Table table){
        jsonBuffer.append("{");
        row.getFields().forEach(field ->{
            addKeyInObject(field);
            addValueInObject(field, table.getFieldDataType(field));
        });
        removeEndCommaValue();
        jsonBuffer.append("},");
    }


}
