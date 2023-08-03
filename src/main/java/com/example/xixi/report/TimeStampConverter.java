package com.example.xixi.report;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author zxb 2022/4/12 17:52
 */
@Component
public class TimeStampConverter implements Converter<Timestamp> {

    /**
     * 开启对 Character 类型的支持
     */
    @Override
    public Class<?> supportJavaTypeKey() {
        return Timestamp.class;
    }


    /**
     * 自定义对 Character 类型数据的处理
     * 我这里就拿 String 去包装了下
     */
    @Override
    public WriteCellData<?> convertToExcelData(Timestamp value,
                                               ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) throws Exception {

        return new WriteCellData<Timestamp>(stampToDate(value.getTime()));
    }


    public static String stampToDate(Long value) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(value);
        return simpleDateFormat.format(date);
    }
}
