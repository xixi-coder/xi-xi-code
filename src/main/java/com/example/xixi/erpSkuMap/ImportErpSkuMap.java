package com.example.xixi.erpSkuMap;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author : xi-xi
 */
public class ImportErpSkuMap {

    private static final String fileName ="/Users/shiheng/Downloads/组合物料对比关系.xlsx";
    public static void main(String[] args) {

//id	parentSkuBom	parentSkuOriginId	childSkuBom	childSkuOriginId
// childSkuQt	unitPriceRate	_version	createdAt	updatedAt	isDeleted	deletedAt	UpdatedBy	CreatedBy
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ArrayList<SkuMap> skuMaps = new ArrayList<>();
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(new FileInputStream(fileName));
            XSSFSheet sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i < lastRowNum; i++) {
                SkuMap skuMap = new SkuMap();
                XSSFRow row = sheet.getRow(i);
//                short lastCellNum = row.getLastCellNum();
//                //parentSkuBom	parentSkuOriginId	childSkuBom	childSkuOriginId	childSkuQt	unitPriceRate
                String parentSkuBom = row.getCell(0).getStringCellValue();
                skuMap.setId((long) i);
                skuMap.setParentSkuBom(parentSkuBom);
                String parentSkuOriginId = row.getCell(1).getStringCellValue();
                skuMap.setParentSkuOriginId(parentSkuOriginId);
                String childSkuBom = row.getCell(2).getStringCellValue();
                String childSkuOriginId = row.getCell(3).getStringCellValue();
                String childSkuQt = row.getCell(4).getNumericCellValue()+"";
                String unitPriceRate = row.getCell(5).getNumericCellValue()+"";
                skuMap.setChildSkuOriginId(childSkuOriginId);
                skuMap.setChildSkuBom(childSkuBom);
                skuMap.setChildSkuQt(childSkuQt);
                skuMap.setUnitPriceRate(unitPriceRate);
                skuMap.setUpdatedAt(format);
                skuMap.setCreatedAt(format);
                skuMaps.add(skuMap);
            }
            System.out.println(skuMaps.size());
            System.out.println(SqlJointUtils.jointBatchInsert(skuMaps, "h3c_bbc_trade__erp_sku_map_b_o"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
