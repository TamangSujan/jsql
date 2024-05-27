package jsql.out.model.meta;

public enum JsonDataType {
    NUMERIC(""), BOOLEAN(""), STRING("\""), CHARACTER("'"), FLOAT("");
    private final String valueWrapper;

    JsonDataType(String valueWrapper){
        this.valueWrapper = valueWrapper;
    }

    public String getValueWrapper(){
        return valueWrapper;
    }

}
