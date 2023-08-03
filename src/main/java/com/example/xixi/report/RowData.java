package com.example.xixi.report;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class RowData {

    private Integer id;
    private String magic_id;

    public RowData(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.magic_id = resultSet.getString("magic_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
