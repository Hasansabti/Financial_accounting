package tech.sabtih.financial_accounting.utils;

public enum ContEntry {
    TABLE_NAME("contracts"),
    COLUMN_ID("ID")
    ,COLUMN_USER("userid")
    ,COLUMN_AMOUNT("amount")
    ,COLUMN_DATE("date")
    ,COLUMN_FOR("for")
    ,COLUMN_DET("details");

    private String colname;

    ContEntry(String s) {
    colname = s;
    }

    public String getText() {
        return colname;
    }
}

