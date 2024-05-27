package jsql.out;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final List<Row> rows;

    public Table(List<Row> rows){
        this.rows = rows;
    }

    public List<Row> getRows(){
        return rows;
    }

    public static Table empty = new Table(new ArrayList<>());
}
