package jsql.out.printer;

import jsql.out.model.Table;

public class JSQLPrinter {

    private JSQLPrinter(){}

    public static String getPrintBuffer(Table table, char horizontalSeparator, char verticalSeparator){
        return new BufferedPrinter(horizontalSeparator, verticalSeparator).getPrintBuffer(table);
    }

    public static String getJson(Table table){
        return new JsonPrinter().getJson(table);
    }
}
