package jsql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JSQLConnection {
    private static boolean isDriverRegistered = false;
    private final JSQLConnectionType connectionType;
    private final String database;
    private final String host;
    private final String port;
    private String username = "root";
    private String password = "root";

    public JSQLConnection(JSQLConnectionType connectionType, String host, String port, String database){
        this.connectionType = connectionType;
        this.host = host;
        this.port = port;
        this.database = database;
        if(!isDriverRegistered){
            registerDriver();
        }
    }

    public Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(getFullConnectionUrl(), username, password);
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    private String getFullConnectionUrl(){
        return connectionType.getDatabaseUrlPrefix() + host + ":" + port + "/" + database;
    }

    private void registerDriver() {
        try {
            Class.forName(connectionType.getDriverName());
            isDriverRegistered = true;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
