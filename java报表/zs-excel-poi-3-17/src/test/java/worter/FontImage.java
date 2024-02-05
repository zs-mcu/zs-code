package worter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class FontImage {


    public static void main(String[] args) throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(new File("./file2.xlsx"));
        XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
        int rowNums = sheet.getPhysicalNumberOfRows();
        int colNums = sheet.getRow(0).getPhysicalNumberOfCells();//获取物理列数

        List list = new ArrayList<>();
        for (Row row : sheet) {
            Map map = new HashMap();
            for (int c =0; c < row.getPhysicalNumberOfCells(); c++) {
                Cell cell = row.getCell(c);
                String value = getCellValue(cell);
                map.put(c,value);
            }
            map.forEach((k,v) -> {
                System.out.print(v + " 、 ");
            });
            System.out.println();
            list.add(map);
        }
        System.out.println(list);
    }


    /**
     * 读取单元格内容 包括计算公式的结果，引用公式的结果（引用公式值当前的sheet单元格，引用了另一个Excel文件的内容例：='C:\Users\T011921\Desktop\[测试引用.xlsx]Sheet1'!A3）
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
//        System.out.println(cell);
        String value = null;
        if(cell != null){
//            System.out.println(cell.getCellType());
            switch (cell.getCellTypeEnum()){
                case BLANK:
                    value = "";
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    switch (cell.getCachedFormulaResultTypeEnum()){
                        case NUMERIC:
                            if(DateUtil.isCellDateFormatted(cell)){
                                Date date = cell.getDateCellValue();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                value = sdf.format(date);
                            }else{
                                BigDecimal n = new BigDecimal(cell.getNumericCellValue());
                                DecimalFormat decimalFormat = new DecimalFormat("0");
                                decimalFormat.setMaximumFractionDigits(18);
                                value = decimalFormat.format(n.doubleValue());
                            }
                            break;
                        case STRING:
                            value = String.valueOf(cell.getStringCellValue());
                            if(value != null){
                                value = value.trim();
                            }
                            break;
                        case BOOLEAN:
                            value = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case ERROR: value = "";
                            break;
                        default:
                            value = cell.getRichStringCellValue().getString();
                            break;
                    }
                    break;
                case NUMERIC:
                    if(DateUtil.isCellDateFormatted(cell)){
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        value = sdf.format(date);
                    }else{
                        BigDecimal n = new BigDecimal(cell.getNumericCellValue());
                        DecimalFormat decimalFormat = new DecimalFormat("0");
                        decimalFormat.setMaximumFractionDigits(18);
                        value = decimalFormat.format(n.doubleValue());
                    }
                    break;
                case STRING:
                    value = String.valueOf(cell.getStringCellValue());
                    if(value != null){
                        value = value.trim();
                    }
                    break;
                default:
                    value = cell.getRichStringCellValue().getString();
                    break;
            }
        }
        return value;
    }
}
