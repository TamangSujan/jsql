package jsql.out.printer;

import jsql.out.model.Field;
import jsql.out.model.Row;
import jsql.out.model.Table;

class JsonPrinter {
    private StringBuilder jsonBuffer = new StringBuilder();

    public String getJson(Table table){
        table.getRows().forEach(this::addObjectToJsonBufferFromRow);
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

    private void addValueInObject(Field field){
        jsonBuffer.append("\"").append(field.getValue()).append("\",");
    }

    private void addObjectToJsonBufferFromRow(Row row){
        jsonBuffer.append("{");
        row.getFields().forEach(field ->{
            addKeyInObject(field);
            addValueInObject(field);
        });
        removeEndCommaValue();
        jsonBuffer.append("},");
    }
}
