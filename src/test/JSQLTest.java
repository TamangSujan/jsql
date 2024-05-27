import jsql.JSQL;
import jsql.connection.JSQLConnection;
import jsql.connection.JSQLConnectionType;
import jsql.out.model.Table;
import jsql.out.printer.JSQLPrinter;

import java.sql.SQLException;

public class JSQLTest {
    public static void main(String[] args) {
        String query = "SELECT p.id AS Id,Name , Age, City FROM person p INNER JOIN address a ON p.id = a.name_id";
        JSQLConnection connection = new JSQLConnection(
                JSQLConnectionType.MYSQL,
                "localhost",
                "3306",
                "jsql"
        );
        connection.setUsername("root");
        connection.setPassword("root");
        try {
            Table table = JSQL.getTable(connection, query);
            String printBuffer = JSQLPrinter.getPrintBuffer(table, '-', '|');
            System.out.println(printBuffer);

            String jsonBuffer = JSQLPrinter.getJson(table);
            System.out.println(jsonBuffer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
