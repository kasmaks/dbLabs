package com.company;

import java.sql.Connection;

public class Model {
    protected DBConnection connection = null;

    public void setConnection(DBConnection connection) {
        this.connection = connection;
    }

    public DBConnection getConnection() {
        return connection;
    }
}
