//package com.example.xixi.excel;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//
///**
// * 处理
// */
//public class DealExcelInvtHead {
//    public static void main(String[] args) throws Exception {
//        String path = "/Users/shiheng/Desktop/";
////        File invtHead = new File(path + "核注清单表头.xlsx");
//        File invtHead = new File(path + "随附单证表体.xlsx");
//        ArrayList<ColumnDef> columnDefs = readExcelToColumnDef(invtHead);
//        System.out.println(columnDefs);
//    }
//
//    private static ArrayList<ColumnDef> readExcelToColumnDef(File finance) throws IOException {
//        InputStream stream3 = Files.newInputStream(finance.toPath());
//        XSSFWorkbook wb1 = new XSSFWorkbook(stream3);
//        XSSFSheet sheetAt1 = wb1.getSheetAt(0);
//        int lastRowNum1 = sheetAt1.getLastRowNum();
//        ArrayList<ColumnDef> columnDefs = new ArrayList<>();
//        for (int i = 1; i < lastRowNum1 + 1; i++) {
//            //字段名	中文名称	数据类型	长度	是否必填	字段说明
//            //BOND_INVT_NO	清单编号	String	64	否	海关审批通过后系统自动返填
//            XSSFRow row = sheetAt1.getRow(i);
//            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
//            String columName = row.getCell(0).getStringCellValue();
//            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
//            String comment1 = row.getCell(1).getStringCellValue();
//
//            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
//            String type = row.getCell(2).getStringCellValue();
//
//            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
//            String length = row.getCell(3).getStringCellValue();
//            if (type.contains("String")) {
//                length = "varchar(" + length + ")";
//            }else if (type.contains("Decimal")){
//                length = "varchar(" + 255 + ")";
//            }
//            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
//            String isNull = row.getCell(4).getStringCellValue();
//            if (isNull.equals("是")) {
//                isNull="not null ";
//            } else if (isNull.equals("否")){
//                isNull=" null ";
//            }
//            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
//            String comment2 = row.getCell(5).getStringCellValue();
//            ColumnDef columnDef = ColumnDef.builder().name(columName.toLowerCase()).comment(comment1 +","+ comment2).length(length).isNull(isNull).build();
//            columnDefs.add(columnDef);
//        }
//        return columnDefs;
//    }
//}
