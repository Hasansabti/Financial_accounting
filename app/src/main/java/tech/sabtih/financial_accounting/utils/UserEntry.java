package tech.sabtih.financial_accounting.utils;

public enum UserEntry {
    TABLE_NAME("users"),
    COLUMN_ID("ID")
    ,COLUMN_NAME("name")
    ,COLUMN_PHONE("phone");

    private String colname;

    UserEntry(String s) {
    colname = s;
    }

    public String getText() {
        return colname;
    }
}

