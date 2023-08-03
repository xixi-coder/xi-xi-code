package com.example.xixi.excel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColumnDef {
    private String name;
    private String comment;
    private String length;
    private String isNull;

    @Override
    public String toString() {
        //deleted_flag varchar(1)  null comment '删除标志位 1表示删除',
        return
                name + " " + length +
                        " " + isNull +
                        " comment '" + comment + '\'';
    }
}
