package jsql;

import jsql.connection.JSQLConnection;
import jsql.exception.JSQLException;
import jsql.out.model.Field;
import jsql.out.model.Row;
import jsql.out.model.Table;
import jsql.validator.FieldValidator;
import jsql.validator.SelectParser;
import jsql.validator.SelectValidator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class JSQL {
    public static Table getTable(JSQLConnection connection, String selectQuery) throws SQLException {
        if(Objects.isNull(connection) || !SelectValidator.isValidQuery(selectQuery))
            return Table.empty;
        List<String> fieldsName = SelectParser.getFieldsName(selectQuery);
        if(FieldValidator.hasValidFields(fieldsName))
            throw new JSQLException("Invalid query, cannot have empty field name!");
        return getTableByConvertingResultSet(connection.getNewConnection(), selectQuery, fieldsName);
    }

    private static Table getTableByConvertingResultSet(Connection connection, String query,List<String> fieldsName) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        List<Row> rows = new LinkedList<>();
        while(resultSet.next()){
             Row row = new Row();
            for (String fieldName : fieldsName) {
                row.add(new Field(fieldName, resultSet.getString(fieldName)));
            }
            rows.add(row);
        }
        connection.close();
        return new Table(rows);
    }
}
