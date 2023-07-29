package com.example.factoriohelper

object ItemTable {
    private const val TABLE_NAME = "items"
    private const val COLUMN_ID = "id"
    private const val COLUMN_NAME = "name"
    private const val COLUMN_CATEGORY = "category"
    private const val COLUMN_DESCRIPTION = "description"

    const val CREATE_TABLE_QUERY = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT,
            $COLUMN_CATEGORY TEXT,
            $COLUMN_DESCRIPTION TEXT
        )
    """

    const val DROP_TABLE_QUERY = "DROP TABLE IF EXISTS $TABLE_NAME"
}