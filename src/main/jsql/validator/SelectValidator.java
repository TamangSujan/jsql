package jsql.validator;

public class SelectValidator {
    public static boolean isValidQuery(String query){
        return isQueryStartWithSelect(query);
    }

    private static boolean isQueryStartWithSelect(String query){
        return query.startsWith("SELECT ");
    }
}
