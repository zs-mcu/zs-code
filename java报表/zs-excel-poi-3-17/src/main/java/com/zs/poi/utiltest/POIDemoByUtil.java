package com.zs.poi.utiltest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//导出一个有样式的excel
//    1、边框线 2、合并单元格 3、行高列宽 4、对齐方式 5、字体
public class POIDemoByUtil {

    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("有样式的数据");

        //大标题
        Row bigTitleRow = sheet.createRow(0);
        //设置单元格数据
        Cell cell00 = bigTitleRow.createCell(0);

        //合并单元格
        ExcelExportUtil_3_17.addMergedRegion(workbook, sheet, 0, 0, 0, 4);
        //设置数据
        CellStyle titleStyle = ExcelExportUtil_3_17.setTitleStyle(workbook);
        ExcelExportUtil_3_17.setCellValue(titleStyle, cell00, "用户信息数据");
        //自适应高度
        ExcelExportUtil_3_17.calcAndSetRowHeight(cell00, 40);
        bigTitleRow.setHeightInPoints(42);//固定高度

        //小标题
        //编号	姓名	手机号	入职日期	现住址
        Row titleRow = sheet.createRow(1);
        String[] titles = new String[]{"编号", "姓名", "手机号", "入职日期", "现住址"};
        CellStyle littleTitleStyle = ExcelExportUtil_3_17.setLittleTitleStyle(workbook);
        for (int i = 0; i < titles.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(littleTitleStyle);
        }

        //存储最大列宽
        Map<Integer, Integer> maxWidth = new HashMap<>();
        // 初始化标题的列宽,字体
        for (int i = 0; i < titles.length; i++) {
            double calcCellWidth = ExcelExportUtil_3_17.calcCellWidth(titleRow.getCell(i));
            maxWidth.put(i, (int) (calcCellWidth + 200));
        }

        //绘制单元格数据
        String[] users = new String[]{"1", "大一", "13800000001", "2001-03-29", "北京市西城区宣武大街1号院"};
        for (int rowNum = 0; rowNum < 5; rowNum++) {
            Row row = sheet.createRow(2 + rowNum);
            for (int colNum = 0; colNum < titles.length; colNum++) {
                Cell cell = row.createCell(colNum);
                ExcelExportUtil_3_17.setCellValue(workbook, cell, users[colNum]);

                int length = (int) (ExcelExportUtil_3_17.calcCellWidth(cell));
                length = Math.min(length, 15000);
                maxWidth.put(colNum, Math.max(length, maxWidth.get(colNum)));
                //单元格注释
                ExcelExportUtil_3_17.setCellPatriarch(cell, "注释测试");
            }
        }


        //设置统计好的自适应列宽
        for (int i = 0; i < titles.length; i++) {
            sheet.setColumnWidth(i, maxWidth.get(i));
        }

        //设置水印
        ExcelExportUtil_3_17.setWatermarkImage(sheet);

//        //去除水印
//        ExcelExportUtil_3_17.removeWatermarkImage(sheet);

        //设置下拉框
        ExcelExportUtil_3_17.dynamicOptions(
                new ExcelExportUtil_3_17.DynamicOptionInfo()
                        .setSheet(sheet)
                        .setOptions(new String[]{"a", "b", "c", "d"})
                        .setPromptBoxShow(true)
                        .setPromptBoxMessage("请选择")
                        .setFirstCol(1)
                        .setLastCol(1)
        );

        String filename = "testStyle--" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString() + ".xlsx";
        FileOutputStream fos = new FileOutputStream("./" + filename);
        //设置密码
        ExcelExportUtil_3_17.setPassword(workbook, fos, "1234");
        fos.close();

        //取消密码
        FileInputStream fis2 = new FileInputStream(new File("./testStyle-pwd.xlsx"));
        FileOutputStream fos2 = new FileOutputStream("./testStyle-pwd-3.xlsx");
        ExcelExportUtil_3_17.removePassword(fis2,fos2,"1234");

        //
        ExcelExportUtil_3_17.excel2image("./file.xlsx","./");
    }
}
