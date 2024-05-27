package jsql.out;

import java.util.LinkedList;
import java.util.List;

public class Row {
    private final List<Field> fields;

    public Row(){
        this.fields = new LinkedList<>();
    }

    public List<Field> getFields(){
        return fields;
    }

    public void add(Field field){
        fields.add(field);
    }
}
