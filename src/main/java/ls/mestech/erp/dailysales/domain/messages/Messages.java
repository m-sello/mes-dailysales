package ls.mestech.erp.dailysales.domain.messages;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;

public class Messages {
    public static String CreatedMessage(String entityName) {
        return String.format("{0} created successfully", entityName);
    }
}
