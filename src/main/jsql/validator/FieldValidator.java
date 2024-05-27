package jsql.validator;

import java.util.List;

public class FieldValidator {
    public static boolean hasValidFields(List<String> fields){
        return fields.contains("");
    }
}
