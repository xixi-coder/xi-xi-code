//package com.example.xixi.finance;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.*;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class ExportData {
//    public static void main(String[] args) throws InterruptedException, IOException {
//        Workbook wb1 = null;
//        InputStream inputStream = Files.newInputStream(Paths.get("/Users/shiheng/Desktop/test2.xlsx"));
//        try {
//            wb1 = WorkbookFactory.create(inputStream);
//        } catch (IOException | InvalidFormatException e) {
//            throw new RuntimeException(e);
//        }
//        JSONArray records = new JSONArray();
//        for (int i = 1; i < 26; i++) {
//            String cookie ="JSESSIONID=06411CA9CB3E5DD82418B0650EAB56F4; UM_distinctid=184cc6c9944804-0f755b9c9d801b-18525635-13c680-184cc6c99451eea; APP_CODE=declare; APP_userid=test_sr2; CNZZDATA1278868508=601052516-1657108390-https%3A%2F%2Fdeclare.smartebao.com%2F|1681278445; UCT_USER_ID=DINGX; token=c49afe26-7fe0-4812-b05d-0c754be8ea23; CNZZDATA1279925767=587580418-1657009757-|1681353783; acw_tc=2f624a4a16813555193224968e1cb215a0346e3fd63c4ad57fba8f1e4b4472; SERVERID=b793808b572c6a6e6bf83b9e4e5ce4b8|1681355573|1681265278";
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Type", "application/json; charset=UTF-8");
//            headers.add("Cookie", cookie);
//            String requestStr = "{\"queryVo\":{\"workNo\":\"\",\"ebaoSeq\":\"\",\"createTime\":\"\",\"customerName\":\"\",\"customerId\":\"\",\"billNo\":\"\"," +
//                    "\"settlementSynStatus\":\"\",\"documenter\":\"\",\"checkState\":\"\",\"platform\":\"\",\"entryNo\":\"\",\"containerNo\":\"\",\"settlementStatus\":" +
//                    "\"\",\"contactName\":\"\",\"ediNo\":\"\",\"salemanNameCn\":\"\",\"trafToolNo\":\"\",\"ieFlag\":\"E\",\"settlementRtnStatus\":\"\",\"ownerType\":\"\"," +
//                    "\"ownerValue\":\"\",\"qgId\":\"\",\"ysId\":\"\",\"checkUser\":\"\",\"region\":\"宁波\"" +
//                    ",\"agentName\":\"\",\"orderTimeGe\":\"\",\"orderTimeLe\":\"\",\"accountingDateGe\":\"\",\"accountingDateLe\":\"" +
//                    "\",\"declareDateGe\":\"2023-03-01 00:00:00\",\"declareDateLe\":\"2023-03-31 23:59:59\"},\"pageVo\":{\"size\":500,\"current\":" + i + "}}";
//            HttpEntity<String> formEntity = new HttpEntity<String>(requestStr, headers);
//            System.out.println(i);
//            String result = restTemplate.postForObject("https://declare.smartebao.com/dub-finance/dubfinance/dub-settlement-record/queryPage", formEntity, String.class);
//            JSONObject jsonObject = JSON.parseObject(result);
//            JSONObject data = jsonObject.getJSONObject("data");
//            records.addAll(data.getJSONArray("records"));
//            Thread.sleep(2000);
//        }
//        Sheet sheetAt = wb1.getSheetAt(0);
//        Row row0 = sheetAt.createRow(0);
//        row0.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("客户名称");
//        row0.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("申报日期");
//        row0.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("报关单号");
//        row0.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("提运单号");
//        row0.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("应付总额cny");
//        row0.createCell(5, Cell.CELL_TYPE_STRING).setCellValue("应付总额usd");
//        row0.createCell(6, Cell.CELL_TYPE_STRING).setCellValue("毛利总额cny");
//        row0.createCell(7, Cell.CELL_TYPE_STRING).setCellValue("毛利总额usd");
//        row0.createCell(8, Cell.CELL_TYPE_STRING).setCellValue("应收总额cny");
//        row0.createCell(9, Cell.CELL_TYPE_STRING).setCellValue("应收总额usd");
//
//        for (int i = 0; i < records.size(); i++) {
//            Row row = sheetAt.createRow(i+1);
//            JSONObject jsonObject = records.getJSONObject(i);
////            System.out.println(jsonObject);
//            //ownerName 客户名称
//            //declareDate 申报日期
//            //entryNo 报关单号
//            //billNo 提运单号
//            //payableCNYTotal
//            //payableUSDTotal
//            //grossProfitCNYTotal
//            //grossProfitUSDTotal
//            //receivableCNYTotal
//            //receivableUSDTotal
//            row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(jsonObject.getString("customerName"));
//            row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(jsonObject.getString("declareDate"));
//            row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(jsonObject.getString("entryNo"));
//            row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue(jsonObject.getString("billNo"));
//            row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue(jsonObject.getString("payableCNYTotal"));
//            row.createCell(5, Cell.CELL_TYPE_STRING).setCellValue(jsonObject.getString("payableUSDTotal"));
//            row.createCell(6, Cell.CELL_TYPE_STRING).setCellValue(jsonObject.getString("grossProfitCNYTotal"));
//            row.createCell(7, Cell.CELL_TYPE_STRING).setCellValue(jsonObject.getString("grossProfitUSDTotal"));
//            row.createCell(8, Cell.CELL_TYPE_STRING).setCellValue(jsonObject.getString("receivableCNYTotal"));
//            row.createCell(9, Cell.CELL_TYPE_STRING).setCellValue(jsonObject.getString("receivableUSDTotal"));
//        }
//        inputStream.close();
//        wb1.write(new BufferedOutputStream(Files.newOutputStream(Paths.get("/Users/shiheng/Desktop/nb-3.xlsx"))));
//
//
//    }
//}
