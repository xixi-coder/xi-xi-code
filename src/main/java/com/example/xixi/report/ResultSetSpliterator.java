package com.example.xixi.report;

import java.sql.ResultSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ResultSetSpliterator implements Spliterator<RowData> {
    private final ResultSet resultSet;

    public ResultSetSpliterator(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public boolean tryAdvance(Consumer<? super RowData> action) {
        try {
            if (resultSet.next()) {
                RowData rowData = new RowData(resultSet);
                action.accept(rowData);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Spliterator<RowData> trySplit() {
        return null; // 不支持拆分
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE; // 估计大小为无限大
    }

    @Override
    public int characteristics() {
        return Spliterator.ORDERED | Spliterator.IMMUTABLE | Spliterator.NONNULL;
    }
}

