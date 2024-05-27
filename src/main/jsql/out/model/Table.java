package jsql.out.model;

import jsql.out.model.meta.JsonDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private Map<String, JsonDataType> metaFields;
    private List<String> metaFieldCacheKeys;
    private boolean isCacheLoaded = false;
    private final List<Row> rows;

    public Table(List<Row> rows){
        this.rows = rows;
        generateDefaultHeaderMetaInformation();
    }

    public List<Row> getRows(){
        return rows;
    }

    public void setFieldDataType(int index, JsonDataType jsonDataType){
        loadMetaFieldKeys();
        if(index < metaFieldCacheKeys.size())
            metaFields.put(metaFieldCacheKeys.get(index), jsonDataType);
    }

    public JsonDataType getFieldDataType(Field field){
        return metaFields.get(field.getName());
    }

    public static Table empty = new Table(new ArrayList<>());


    private void loadMetaFieldKeys(){
        if(!isCacheLoaded){
            metaFieldCacheKeys = new ArrayList<>(metaFields.keySet());
            isCacheLoaded = true;
        }
    }

    private void generateDefaultHeaderMetaInformation(){
        if(!rows.isEmpty()) {
            List<Field> fields = rows.get(0).getFields();
            metaFields = new HashMap<>(fields.size());
            for(Field field: fields){
                metaFields.put(field.getName(), JsonDataType.STRING);
            }
        }
    }
}
