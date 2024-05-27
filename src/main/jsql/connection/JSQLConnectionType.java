package jsql.connection;

public enum JSQLConnectionType {
    MYSQL("jdbc:mysql://", "com.mysql.cj.jdbc.Driver");
    private final String databaseUrlPrefix;
    private final String driverName;
    JSQLConnectionType(String databaseUrlPrefix, String driverName){
        this.databaseUrlPrefix = databaseUrlPrefix;
        this.driverName = driverName;
    }

    public String getDatabaseUrlPrefix(){
        return databaseUrlPrefix;
    }

    public String getDriverName() {
        return driverName;
    }
}
