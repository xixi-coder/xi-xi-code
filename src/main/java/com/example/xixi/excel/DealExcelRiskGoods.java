package com.example.xixi.excel;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * 高危品处理
 */
public class DealExcelRiskGoods {
    public static void main(String[] args) throws Exception {
        DealExcelRiskGoods.testXlsx();
    }
    public static void testXlsx() throws Exception {
        String path = "/Users/shiheng/Downloads/";
        File finance = new File(path + "riskgoods.xlsx");
        FileInputStream fileInputStream = new FileInputStream(finance);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        ArrayList<DubRiskGoodsConfigVo> list = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
       a: for (int i = 1; i < lastRowNum + 1; i++)  {
            DubRiskGoodsConfigVo dubRiskGoodsConfigVo = new DubRiskGoodsConfigVo();
            Row row = sheet.getRow(i);
            b :for (int i1 = row.getFirstCellNum(); i1 < row.getLastCellNum() + 1; i1++) {
                Cell cell = row.getCell(i1);
                if (Objects.isNull(cell)){
                    continue b ;
                }
                cell.setCellType(CellType.STRING);
            }
            String stringCellValue = row.getCell(0).getStringCellValue();
            if (StringUtils.isEmpty(stringCellValue)){
                continue;
            }
            dubRiskGoodsConfigVo.setSerialNo(Integer.valueOf(stringCellValue));
            String goodName = row.getCell(2).getStringCellValue();
            dubRiskGoodsConfigVo.setGoodName(goodName);
            String contentInfo = row.getCell(3).getStringCellValue();
            dubRiskGoodsConfigVo.setContentInfo(contentInfo);
            String alias1 = row.getCell(4).getStringCellValue();
            dubRiskGoodsConfigVo.setAlias1(alias1);
            String alias2 = row.getCell(5).getStringCellValue();
            dubRiskGoodsConfigVo.setAlias2(alias2);
            String alias3 = row.getCell(6).getStringCellValue();
            dubRiskGoodsConfigVo.setAlias3(alias3);
            String alias4 = row.getCell(7).getStringCellValue();
            dubRiskGoodsConfigVo.setAlias4(alias4);
            dubRiskGoodsConfigVo.setCasNo(row.getCell(8).getStringCellValue());
            String remark = row.getCell(9).getStringCellValue();
            dubRiskGoodsConfigVo.setRemark(remark);
            list.add(dubRiskGoodsConfigVo);
        }
        fileInputStream.close();
        System.out.println(JSONObject.toJSONString(list));



    }
}
