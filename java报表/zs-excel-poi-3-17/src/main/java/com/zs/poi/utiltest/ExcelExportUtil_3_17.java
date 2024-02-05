package com.zs.poi.utiltest;

import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create by 张邵
 * 2024/2/1 15:29
 * <p>
 * poi 3.17工具类
 */
public class ExcelExportUtil_3_17 {


    /** ************************************************************ **/
    /** ***************************合并单元格************************* **/
    /** ************************************************************ **/
    /**
     * 合并单元格并设置单元格边框样式
     *
     * @param wb
     * @param sheet
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     */
    public static void addMergedRegion(Workbook wb, Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        //合并单元格
        CellRangeAddress regionB = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(regionB);
        //使用RegionUtil类为合并后的单元格添加边框
        //旧版setBorderBottom为4个参数，参数依次为border、region、sheet、wb， 其中border为CellStyle.BORDER_THIN
        //新版4.1.2setBorderBottom为三个参数，参数依次为border、region、sheet， 其中border为BorderStyle.THIN
        //RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, regionB, sheet, wb);
        RegionUtil.setBorderBottom(BorderStyle.THIN, regionB, sheet);//下边框
        RegionUtil.setBorderLeft(BorderStyle.THIN, regionB, sheet);//左边框
        RegionUtil.setBorderRight(BorderStyle.THIN, regionB, sheet);//有边框
        RegionUtil.setBorderTop(BorderStyle.THIN, regionB, sheet);//上边框
    }


    /** ************************************************************ **/
    /** ***************************样式设置************************** **/
    /** ************************************************************ **/
    /**
     * 统一setCellValue，并设置样式
     *
     * @param wb
     * @param cell
     * @param value
     */
    public static void setCellValue(Workbook wb, Cell cell, String value) {
        cell.setCellValue(value);
        cell.setCellStyle(setCellStyle(wb));
    }

    /**
     * 统一setCellValue，并设置样式
     *
     * @param style
     * @param cell
     * @param value
     */
    public static void setCellValue(CellStyle style, Cell cell, String value) {
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    /**
     * 设置单元格样式
     * <p>
     * 边框、垂直居中、换行
     * 字体：宋体、11pt
     *
     * @param wb
     * @return
     */
    public static CellStyle setCellStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        //设置边框样式
        //setBorderBottom旧版为CellStyle.BORDER_THIN，新版为BorderStyle.THIN
        style.setBorderBottom(BorderStyle.THIN);//下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        //垂直居中setVerticalAlignment旧版为CellStyle.VERTICAL_CENTER，新版为VerticalAlignment.CENTER
        style.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
        //自动换行
        style.setWrapText(true);
        Font font = wb.createFont();//设置字体
        font.setFontName("宋体");//设置字体大小
        font.setFontHeightInPoints((short) 11);//在样式中引用这种字体
        style.setFont(font);
        return style;
    }

    /**
     * 设置title单元格样式
     * <p>
     * 边框、居中、换行
     * 字体：黑体、18pt
     *
     * @param wb
     * @return
     */
    public static CellStyle setTitleStyle(Workbook wb) {

        //  需求：1、边框线：全边框  2、行高：42   3、合并单元格：第1行的第1个单元格到第5个单元格
        //  4、对齐方式：水平垂直都要居中 5、字体：黑体18号字
        CellStyle style = wb.createCellStyle();
        //设置边框样式
        //setBorderBottom旧版为CellStyle.BORDER_THIN，新版为BorderStyle.THIN
        style.setBorderBottom(BorderStyle.THIN);//下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setBorderTop(BorderStyle.THIN);//上边框

        // 对齐方式： 水平对齐  垂直对齐
        //垂直居中setVerticalAlignment旧版为CellStyle.VERTICAL_CENTER，新版为VerticalAlignment.CENTER
        style.setAlignment(HorizontalAlignment.CENTER); //水平居中对齐
        style.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中对齐
        // 创建字体
        Font font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 18);
        font.setBold(true);
        // 把字体放入到样式中
        style.setFont(font);
        //自动换行
        style.setWrapText(true);
        return style;
    }

    /**
     * 设置title单元格样式
     * <p>
     * 边框、居中、换行
     * 字体：宋体、12pt
     *
     * @param wb
     * @return
     */
    public static CellStyle setLittleTitleStyle(Workbook wb) {
        CellStyle cellStyle = setTitleStyle(wb);
        CellStyle style = wb.createCellStyle();
        style.cloneStyleFrom(cellStyle);

        //创建字体  宋体12号字加粗
        Font littleFont = wb.createFont();
        littleFont.setFontName("宋体");
        littleFont.setFontHeightInPoints((short) 12);
        littleFont.setBold(true);
        style.setFont(littleFont);

        return style;
    }

    /** ************************************************************ **/
    /** **************************单元格注释************************* **/
    /** *********************************************************** **/
    /**
     * 添加单元格注释
     * <p>
     * <p>
     * 创建一个新的客户端锚点并设置锚点的左上角和右下角坐标。
     * 注意：Microsoft Excel 似乎有时不允许 y1 高于 y2 或 x1 高于 x2，您可能需要反转它们并垂直或水平翻转绘制形状！
     * 参数：前4个参数单位：1cm = 360000EMUs、1px = 9525EMUs
     * dx1 - 第一个单元格内的 x 坐标。
     * dy1 - 第一个单元格内的 y 坐标。
     * dx2 - 第二个单元格内的 x 坐标。
     * dy2 - 第二个单元格内的 y 坐标。
     * col1 - 第一个单元格的列（从 0 开始）。
     * row1 - 第一个单元格的行（从 0 开始）。
     * col2 - 第二个单元格的列（从 0 开始）。
     * row2 - 第二个单元格的行（从 0 开始）。
     * <p>
     * HSSFSheet
     * XSSFSheet
     * <p>
     * SXSSFSheet 百万级别数据导出，不提供
     */
    public static void setCellPatriarch(final Cell cell, String content) {
        Sheet sheet = cell.getSheet();
        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();
        if (sheet instanceof HSSFSheet) {
            HSSFPatriarch patr = (HSSFPatriarch) sheet.createDrawingPatriarch();
            HSSFComment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) columnIndex, rowIndex, (short) (columnIndex + 2), rowIndex + 2));
            comment.setRow(rowIndex);
            comment.setColumn(columnIndex);
            comment.setString(new HSSFRichTextString(content));
        } else if (sheet instanceof XSSFSheet) {
            XSSFDrawing patr = (XSSFDrawing) sheet.createDrawingPatriarch();
            XSSFComment comment = patr.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) columnIndex, rowIndex, (short) (columnIndex + 2), rowIndex + 2));
            comment.setString(new XSSFRichTextString(content));
        }
    }


    /** ************************************************************ **/
    /** **********************合并单元格后自动换行********************* **/
    /**
     * ***********************************************************
     **/
    public static void calcAndSetRowHeight(Cell cell, float minHeight) {
        short minHeightTwips = (short) (minHeight * 20);

        //声明自适应行高
        short height;

        //单元格的内容 获取string数据
        String cellContent = getCellContentAsString(cell);
        if (null != cellContent && !"".equals(cellContent)) {
            Row row = cell.getRow();
            //获取行高
            double maxHeight = row.getHeight();
            //单元格的宽高及单元格信息
            Map<String, Object> cellInfoMap = getCellInfo(cell);
            Integer cellWidth = (Integer) cellInfoMap.get("width");
            Integer cellHeight = (Integer) cellInfoMap.get("height");
            if (cellHeight > maxHeight) {
                maxHeight = cellHeight;
            }

            //计算cell内容宽度
            double cellContentWidth = calcCellWidth(cell);
            //字符行数 = 字符内容长度/单元格宽度       字符串需要的行数不做四舍五入之类的操作
            double stringNeedsRows = cellContentWidth / cellWidth;//小于一行补足一行
            if (stringNeedsRows < 1.0) {
                stringNeedsRows = 1.0;
            }
            //需要的高度 = 字体高度 * 字符行数
            double stringNeedsHeight = 0;
            if (cell instanceof XSSFCell) {
                XSSFCellStyle cellstyle = (XSSFCellStyle) cell.getCellStyle();
                stringNeedsHeight = (double) cellstyle.getFont().getFontHeight() * stringNeedsRows;
            } else if (cell instanceof HSSFCell) {
                HSSFCellStyle cellstyle = (HSSFCellStyle) cell.getCellStyle();
                stringNeedsHeight = (double) cellstyle.getFont(cell.getSheet().getWorkbook()).getFontHeight() * stringNeedsRows;
            }


            //需要重设行高
            if (stringNeedsHeight > maxHeight) {
                maxHeight = stringNeedsHeight;
                //最后取天花板防止高度不够
                maxHeight = Math.ceil(maxHeight);
                //重新设置行高同时处理多行合并单元格的情况
                Boolean isPartOfRowsRegion = (Boolean) cellInfoMap.get("isPartOfRowsRegion");
                if (isPartOfRowsRegion) {
                    Integer firstRow = (Integer) cellInfoMap.get("firstRow");
                    Integer lastRow = (Integer) cellInfoMap.get("lastRow");//平均每行需要增加的行高
                    double addHeight = (maxHeight - cellHeight) / (lastRow - firstRow + 1);
                    for (int i = firstRow; i <= lastRow; i++) {
                        double rowsRegionHeight = row.getSheet().getRow(i).getHeight() + addHeight;
                        //height = (short) rowsRegionHeight;
                        row.setHeight((short) Math.max(rowsRegionHeight, minHeightTwips));
                    }
                } else {
                    if (maxHeight > cell.getRow().getHeight()) {
                        height = (short) maxHeight;
                        row.setHeight((short) Math.max(height, minHeightTwips));
                    }
                }
            } else {
                height = (short) stringNeedsHeight;
                row.setHeight((short) Math.max(height, minHeightTwips));
            }
        }
    }

    /**
     * 设置自适应行高*@paramcell
     */
    public static void calcAndSetRowHeight(Cell cell) {
        //声明自适应行高
        short height;

        //单元格的内容 获取string数据
        String cellContent = getCellContentAsString(cell);
        if (null != cellContent && !"".equals(cellContent)) {
            Row row = cell.getRow();
            //获取行高
            double maxHeight = row.getHeight();
            //单元格的宽高及单元格信息
            Map<String, Object> cellInfoMap = getCellInfo(cell);
            Integer cellWidth = (Integer) cellInfoMap.get("width");
            Integer cellHeight = (Integer) cellInfoMap.get("height");
            if (cellHeight > maxHeight) {
                maxHeight = cellHeight;
            }

            //计算cell内容宽度
            double cellContentWidth = calcCellWidth(cell);
            //字符行数 = 字符内容长度/单元格宽度       字符串需要的行数不做四舍五入之类的操作
            double stringNeedsRows = cellContentWidth / cellWidth;//小于一行补足一行
            if (stringNeedsRows < 1.0) {
                stringNeedsRows = 1.0;
            }
            //需要的高度 = 字体高度 * 字符行数
            double stringNeedsHeight = 0;
            if (cell instanceof XSSFCell) {
                XSSFCellStyle cellstyle = (XSSFCellStyle) cell.getCellStyle();
                stringNeedsHeight = (double) cellstyle.getFont().getFontHeight() * stringNeedsRows;
            } else if (cell instanceof HSSFCell) {
                HSSFCellStyle cellstyle = (HSSFCellStyle) cell.getCellStyle();
                stringNeedsHeight = (double) cellstyle.getFont(cell.getSheet().getWorkbook()).getFontHeight() * stringNeedsRows;
            }
            //需要重设行高
            if (stringNeedsHeight > maxHeight) {
                maxHeight = stringNeedsHeight;
                //最后取天花板防止高度不够
                maxHeight = Math.ceil(maxHeight);
                //重新设置行高同时处理多行合并单元格的情况
                Boolean isPartOfRowsRegion = (Boolean) cellInfoMap.get("isPartOfRowsRegion");
                if (isPartOfRowsRegion) {
                    Integer firstRow = (Integer) cellInfoMap.get("firstRow");
                    Integer lastRow = (Integer) cellInfoMap.get("lastRow");//平均每行需要增加的行高
                    double addHeight = (maxHeight - cellHeight) / (lastRow - firstRow + 1);
                    for (int i = firstRow; i <= lastRow; i++) {
                        double rowsRegionHeight = row.getSheet().getRow(i).getHeight() + addHeight;
                        //height = (short) rowsRegionHeight;
                        row.getSheet().getRow(i).setHeight((short) (rowsRegionHeight));
                    }
                } else {
                    if (maxHeight > cell.getRow().getHeight()) {
                        height = (short) maxHeight;
                        row.setHeight(height);
                    }
                }
            } else {
                height = (short) stringNeedsHeight;
                row.setHeight(height);
            }
        }
    }

    /**
     * 计算单元格内容所需要的宽度
     * <p>
     * 文字内容宽度 = (英式字符个数 * 256 + 中式字符个数 * 2 * 256) * (fontHeight / 20) * 0.123;
     * 总体计算思路：
     * 获取内容长度：个数
     * 获取单元格宽度：单位：字符
     * 获取字符高度：单位：twips
     * 由于中文和英文的字符长度不同，所以需要统计出英式字符个数
     * 一个中文字符约占用两个英文字符的长度，这里就按2.25来处理
     * 字体高度单位转换：
     * twips --> pt
     * twips 转 pt  1pt = 20twips
     * pt --> 字符
     * 1pt 约为 0.123 个字符
     *
     * @param cell
     * @return
     */
    public static double calcCellWidth(Cell cell) {
        short fontHeight = 0;
        //获取字体对象
        if (cell instanceof XSSFCell) {
            XSSFCellStyle cellstyle = (XSSFCellStyle) cell.getCellStyle();
            XSSFFont font = cellstyle.getFont();
            //字体的高度
            fontHeight = font.getFontHeight();//cell内容字符串总宽度
        } else if (cell instanceof HSSFCell) {
            HSSFCellStyle cellstyle = (HSSFCellStyle) cell.getCellStyle();
            HSSFFont font = cellstyle.getFont(cell.getSheet().getWorkbook());
            //字体的高度
            fontHeight = font.getFontHeight();//cell内容字符串总宽度
        }


        //获取cell内容
        String cellContent = getCellContentAsString(cell);
        //统计英文字符个数
        int engStrCount = calcEnglishStrCount(cellContent);
        //统计中文字符个数
        int cnStrCount = cellContent.length() - engStrCount;

        //计算内容宽度
        return (engStrCount * 256 + cnStrCount * 256 * 2) * (fontHeight / 20.0) * 0.11;
    }

    /**
     * 统计英文字符个数
     *
     * @param str
     * @return
     */
    public static int calcEnglishStrCount(String str) {
        int countChineseCharacters = 0;// 记录中文字母的个数
        int countEnglishChars = 0;// 记录英文字母的个数
        int countNumbers = 0;// 记录数字的个数
        int countEnglishSymbols = 0;//记录英文符号
        int countChineseSymbols = 0;//记录中文符号
        int other = 0;


        Pattern pattern = Pattern.compile("([\\u4e00-\\u9fa5])|" + "([a-zA-Z])|" + "(\\d)|" + "([\\u0021-\\u007e])|" + "([，。、？；’‘“”＇（）．：【】「」·~！￥…＿｀])"); //匹配中文字符的正则表达式
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                countChineseCharacters++;
            } else if (matcher.group(2) != null) {
                countEnglishChars++;
            } else if (matcher.group(3) != null) {
                countNumbers++;
            } else if (matcher.group(4) != null) {
                countEnglishSymbols++;
            } else if (matcher.group(5) != null) {
                countChineseSymbols++;
            } else {
                other++;
            }
        }
//        System.out.println("记录中文字母的个数" + countChineseCharacters);
//        System.out.println("记录英文字母的个数" + countEnglishChars);
//        System.out.println("记录数字的个数" + countNumbers);
//        System.out.println("记录英文符号" + countEnglishSymbols);
//        System.out.println("记录中文符号" + countChineseSymbols);
//        System.out.println("其他" + other);

        return countEnglishChars + countNumbers + countEnglishSymbols + other;
    }

    /**
     * 解析一个单元格得到数据
     *
     * @param cell
     * @return
     */

    /**
     * 读取单元格内容 包括计算公式的结果，引用公式的结果
     *
     * @param cell
     * @return
     */
    public static String getCellContentAsString(Cell cell) {
        String value = null;
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case BLANK:
                    value = "";
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    switch (cell.getCachedFormulaResultTypeEnum()) {
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                value = sdf.format(date);
                            } else {
                                BigDecimal n = BigDecimal.valueOf(cell.getNumericCellValue());
                                DecimalFormat decimalFormat = new DecimalFormat("0");
                                decimalFormat.setMaximumFractionDigits(18);
                                value = decimalFormat.format(n.doubleValue());
                            }
                            break;
                        case STRING:
                            value = String.valueOf(cell.getStringCellValue());
                            if (value != null) {
                                value = value.trim();
                            }
                            break;
                        case BOOLEAN:
                            value = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case ERROR:
                            value = "";
                            break;
                        default:
                            value = cell.getRichStringCellValue().getString();
                            break;
                    }
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        value = sdf.format(date);
                    } else {
                        BigDecimal n = new BigDecimal(cell.getNumericCellValue());
                        DecimalFormat decimalFormat = new DecimalFormat("0");
                        decimalFormat.setMaximumFractionDigits(18);
                        value = decimalFormat.format(n.doubleValue());
                    }
                    break;
                case STRING:
                    value = String.valueOf(cell.getStringCellValue());
                    if (value != null) {
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


    /**
     * 获取单元格及合并单元格的起始行、结束行、起始列、结束列、高度、宽度、是否为合并单元格
     *
     * @param cell
     * @return Map<String, Object>
     */
    private static Map<String, Object> getCellInfo(Cell cell) {
        Sheet sheet = cell.getSheet();
        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();
        boolean isPartOfRegion = false;
        int firstColumn = 0;
        int lastColumn = 0;
        int firstRow = 0;
        int lastRow = 0;
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            firstColumn = ca.getFirstColumn();
            lastColumn = ca.getLastColumn();
            firstRow = ca.getFirstRow();
            lastRow = ca.getLastRow();
            if (rowIndex >= firstRow && rowIndex <= lastRow) {
                if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
                    isPartOfRegion = true;
                    break;
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        Integer width = 0;
        Integer height = 0;
        boolean isPartOfRowsRegion = false;
        if (isPartOfRegion) {
            for (int i = firstColumn; i <= lastColumn; i++) {
                width += sheet.getColumnWidth(i);
            }
            for (int i = firstRow; i <= lastRow; i++) {
                height += sheet.getRow(i).getHeight();
            }
            if (lastRow > firstRow) {
                isPartOfRowsRegion = true;
            }
        } else {
            width = sheet.getColumnWidth(columnIndex);
            height += cell.getRow().getHeight();
        }
        map.put("firstRow", firstRow);
        map.put("lastRow", lastRow);
        map.put("firstColumn", firstColumn);
        map.put("lastColumn", lastColumn);
        map.put("width", width);
        map.put("height", height);
        map.put("isPartOfRowsRegion", isPartOfRowsRegion);
        return map;
    }


    /** ************************************************************ **/
    /** ***********************Excel设置水印************************* **/
    /**
     * **********************************************************
     **/
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Watermark {
        private Integer width = 500;
        private Integer height = 300;
        private String text;//文字换行使用：\n
        private String dateFormat;
        private String bgColor = "#ffffff";
        private Integer bgAlpha = 50;//0-255
        private String fontColor = "#C5CBCF";
        private Integer fontAlpha = 100;//0-255

        @NonNull
        private XSSFSheet xssfSheet;

        @NonNull
        private XSSFWorkbook xssfWorkbook;
    }

    /**
     * 生成水印图片
     *
     * @param watermark
     * @return
     */
    public static BufferedImage createWatermarkImage(Watermark watermark) {
        if (watermark == null) {
            watermark = new Watermark();
            watermark.setText("内部资料");
            watermark.setFontColor("#C5CBCF");
            watermark.setDateFormat("yyyy-MM-dd HH:mm");
        } else {
            if (StringUtils.isEmpty(watermark.getDateFormat())) {
                watermark.setDateFormat("yyyy-MM-dd HH:mm");
            } else if (watermark.getDateFormat().length() == 16) {
                watermark.setDateFormat("yyyy-MM-dd HH:mm");
            } else if (watermark.getDateFormat().length() == 10) {
                watermark.setDateFormat("yyyy-MM-dd");
            }
            if (StringUtils.isEmpty(watermark.getText())) {
                watermark.setText("内部资料");
            }
            if (StringUtils.isEmpty(watermark.getFontColor())) {
                watermark.setFontColor("#C5CBCF");
            }
        }
        String[] textArray = watermark.getText().split("\n");
        java.awt.Font font = new java.awt.Font("microsoft-yahei", java.awt.Font.PLAIN, 20);


        //创建图片：画纸，指定画笔类型，纸张大小
        BufferedImage image = new BufferedImage(watermark.width, watermark.height, BufferedImage.TYPE_INT_ARGB);
        //获取画笔对象
        Graphics2D g = image.createGraphics();

        //设置图片透明度: 方式一
        int bgColorR = hexColorToRgb(watermark.getBgColor(), 0);
        int bgColorG = hexColorToRgb(watermark.getBgColor(), 2);
        int bgColorB = hexColorToRgb(watermark.getBgColor(), 4);
        g.setColor(new Color(bgColorR, bgColorG, bgColorB, watermark.bgAlpha));
        g.fillRect(0, 0, watermark.width, watermark.height);
        g.dispose();

        //设置图片透明度: 方式二
//        image = g.getDeviceConfiguration().createCompatibleImage(watermark.width, watermark.height, Transparency.TRANSLUCENT);
//        g.dispose();

        //拿起画笔
        g = image.createGraphics();
        int colorR = hexColorToRgb(watermark.getFontColor(), 0);
        int colorG = hexColorToRgb(watermark.getFontColor(), 2);
        int colorB = hexColorToRgb(watermark.getFontColor(), 4);
        g.setColor(new Color(colorR, colorG, colorB, watermark.fontAlpha));// 设定画笔颜色
        //g.setColor(new Color(Integer.parseInt(watermark.getColor().substring(1), 16)));// 设定画笔颜色
        g.setFont(font);// 设置画笔字体
        /*
        shx：在 x 轴方向上的倾斜量。正值会使图形向右倾斜，负值会使图形向左倾斜。
        shy：在 y 轴方向上的倾斜量。正值会使图形向下倾斜，负值会使图形向上倾斜
         */
        //画笔倾斜角度
        //g.rotate(Math.toRadians(45), 0, 0);
        //图形变形倾斜参数
        g.shear(-0.25, 0.5);// 设定倾斜度
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//设置字体平滑


        int y = 50;
        for (int i = 0; i < textArray.length; i++) {
            g.drawString(textArray[i], 50, y);// 画出字符串
            y = y + font.getSize();
        }
        String nowFormatTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(watermark.getDateFormat()));
        g.drawString(nowFormatTime, 50, y);// 画出字符串
        g.dispose();// 释放画笔
        return image;
    }

    /**
     * 设置水印
     *
     * @param watermark
     * @return
     */
    @SneakyThrows
    public static void setWatermarkImage(Watermark watermark) {
        BufferedImage watermarkImage = createWatermarkImage(watermark);
        // 导出到字节流B
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(watermarkImage, "png", os);
        int pictureIdx = watermark.xssfWorkbook.addPicture(os.toByteArray(), Workbook.PICTURE_TYPE_PNG);
        String rID = watermark.xssfSheet.addRelation(null, XSSFRelation.IMAGES, watermark.xssfWorkbook.getAllPictures().get(pictureIdx)).getRelationship().getId();
        watermark.xssfSheet.getCTWorksheet().addNewPicture().setId(rID);
    }

    public static void setWatermarkImage(Sheet sheet, ArrayList<String> contentList) {
        if (sheet instanceof XSSFSheet) {
            String content = String.join("\n", contentList);
            setWatermarkImage(new Watermark().setText(content).setDateFormat("yyyy-MM-dd HH:mm:ss").setFontColor("#FF0000").setXssfSheet((XSSFSheet) sheet).setXssfWorkbook((XSSFWorkbook) sheet.getWorkbook()));
        }
    }

    public static void setWatermarkImage(Sheet sheet, String[] contentArr) {
        if (sheet instanceof XSSFSheet) {
            String content = String.join("\n", contentArr);
            setWatermarkImage(new Watermark().setText(content).setDateFormat("yyyy-MM-dd HH:mm:ss").setFontColor("#FF0000").setXssfSheet((XSSFSheet) sheet).setXssfWorkbook((XSSFWorkbook) sheet.getWorkbook()));
        }
    }

    public static void setWatermarkImage(Sheet sheet, String content) {
        if (sheet instanceof XSSFSheet) {
            setWatermarkImage(new Watermark().setText(content).setDateFormat("yyyy-MM-dd HH:mm:ss").setFontColor("#FF0000").setXssfSheet((XSSFSheet) sheet).setXssfWorkbook((XSSFWorkbook) sheet.getWorkbook()));
        }
    }

    public static void setWatermarkImage(Sheet sheet) {
        if (sheet instanceof XSSFSheet) {
            setWatermarkImage(new Watermark().setDateFormat("yyyy-MM-dd HH:mm:ss").setFontColor("#FF0000").setXssfSheet((XSSFSheet) sheet).setXssfWorkbook((XSSFWorkbook) sheet.getWorkbook()));
        }
    }

    /**
     * 16进制转RGB参数
     *
     * @param hexColor
     * @param index
     * @return
     */
    public static int hexColorToRgb(String hexColor, int index) {
        // 删除开头的#号
        hexColor = hexColor.replace("#", "");
        // 将16进制颜色值转换为10进制整数
        int value = Integer.parseInt(hexColor.substring(index, index + 2), 16);
        // 返回RGB格式的颜色值
        return value;
    }

    /**
     * 去除水印
     *
     * @param sheet
     */
    public static void removeWatermarkImage(XSSFSheet sheet) {
        sheet.getCTWorksheet().unsetPicture();
    }

    /** ************************************************************ **/
    /** ************************文档摘要信息************************** **/
    /** ************************************************************ **/
    /**
     * 创建文档信息
     * <p>
     * 只找到了 HSSWorkbook的
     * Excel文档摘要信息(可通过右键->Excel文件->属性看见)
     *
     * @param workbook Excel工作簿
     */
    protected void createDocumentInfo(Workbook workbook) {
        if (workbook instanceof HSSFWorkbook) {
            HSSFWorkbook hssfWorkbook = (HSSFWorkbook) workbook;
            //创建文档信息
            hssfWorkbook.createInformationProperties();
            //摘要信息
            DocumentSummaryInformation information = hssfWorkbook.getDocumentSummaryInformation();
            //设置类别
            information.setCategory("学生导入模板");
            //设置文档管理者名称
            information.setManager("weixiaohuai");
            //设置公司
            information.setCompany("gzly");
            SummaryInformation summaryInformation = hssfWorkbook.getSummaryInformation();
            //作者
            summaryInformation.setAuthor("weixiaohuai");
            //备注
            summaryInformation.setComments("动态生成学生导入模板");
            //主题
            summaryInformation.setSubject("学生模板");
            //标题
            summaryInformation.setTitle("学生导入模板");
        }
    }


    /** ************************************************************ **/
    /** *************************下拉列表**************************** **/
    /**
     * ***********************************************************
     **/
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class DynamicOptionInfo {
        private Sheet sheet;
        private String[] options;
        private String promptBoxTitle = "提示信息";
        private String promptBoxMessage = "";
        private boolean promptBoxShow = false;//是否显示提示信息
        private int firstRow = 1;
        private int lastRow = 65535;
        private int firstCol = 0;
        private int lastCol = 0;
    }

    public static void dynamicOptions(DynamicOptionInfo dynamicOptionInfo) {
        if (dynamicOptionInfo.sheet instanceof HSSFSheet) {
            HSSFSheet hssfSheet = (HSSFSheet) dynamicOptionInfo.sheet;
            //在第1行第9列(学生类别列)，插入下拉选择框,这里将lastRow设置为很大的一个数就是为了使这一列的值只能通过下拉框选择
            CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(dynamicOptionInfo.firstRow, dynamicOptionInfo.lastRow, dynamicOptionInfo.firstCol, dynamicOptionInfo.lastCol);
            //生成下拉框内容
            DVConstraint constraint = DVConstraint.createExplicitListConstraint(dynamicOptionInfo.options);
            //绑定下拉框和作用区域
            HSSFDataValidation hssfDataValidation = new HSSFDataValidation(cellRangeAddressList, constraint);
            //单元格输入提示信息
            hssfDataValidation.createPromptBox(dynamicOptionInfo.promptBoxTitle, dynamicOptionInfo.promptBoxMessage);
            //是否显示提示信息
            hssfDataValidation.setShowPromptBox(dynamicOptionInfo.promptBoxShow);
            //对sheet页生效
            hssfSheet.addValidationData(hssfDataValidation);
        } else {
            XSSFSheet xssfSheet = (XSSFSheet) dynamicOptionInfo.sheet;
            /**
             * 8. 动态生成下拉式菜单
             * 说明: 必要的时候可通过数据库查询动态替换下拉数据
             */
            DataValidationHelper dvHelper = dynamicOptionInfo.sheet.getDataValidationHelper();
            DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(dynamicOptionInfo.options);
            DataValidation validation = dvHelper.createValidation(dvConstraint, new CellRangeAddressList(dynamicOptionInfo.firstRow, dynamicOptionInfo.lastRow, dynamicOptionInfo.firstCol, dynamicOptionInfo.lastCol));
            validation.createPromptBox(dynamicOptionInfo.promptBoxTitle, dynamicOptionInfo.promptBoxMessage);
            validation.setShowPromptBox(dynamicOptionInfo.promptBoxShow);
            //对sheet页生效
            xssfSheet.addValidationData(validation);
        }

    }


//    /**
//     * 设置下拉框数据
//     * @param wb       表格对象
//     * @param typeName 要渲染的sheet名称
//     * @param values   下拉框的值
//     * @param col      下拉列的下标
//     * @author Hower Wong
//     * @date 2022年5月27日
//     */
//    public static void setDropDownBox(XSSFWorkbook wb, String typeName, String[] values, Integer col) {
//        //获取所有sheet页个数
//        int sheetTotal = wb.getNumberOfSheets();
//        //处理下拉数据
//        if (values != null && values.length != 0) {
//            //新建一个sheet页
//            String hiddenSheetName = "hiddenSheet";
//            XSSFSheet hiddenSheet = wb.getSheet(hiddenSheetName);
//            if (hiddenSheet == null) {
//                hiddenSheet = wb.createSheet(hiddenSheetName);
//                sheetTotal++;
//            }
//            // 获取数据起始行
//            int startRowNum = hiddenSheet.getLastRowNum() + 1;
//            int endRowNum = startRowNum;
//            //写入下拉数据到新的sheet页中
//            for (int i = 0; i < values.length; i++)
//                hiddenSheet.createRow(endRowNum++).createCell(0).setCellValue(values[i]);
//            //将新建的sheet页隐藏掉
//            wb.setSheetHidden(sheetTotal - 1, true);
//            //获取新sheet页内容
//            String strFormula = hiddenSheetName + "!$A$" + ++startRowNum + ":$A$" + endRowNum;
//            // 设置下拉
//            XSSFSheet mainSheet = wb.getSheet(typeName);
//            mainSheet.addValidationData(SetDataValidation(wb, strFormula, 1, 65535, col, col));
//        }
//    }
//    /**
//     * 返回类型 DataValidation
//     * @param wb         表格对象
//     * @param strFormula formula
//     * @param firstRow   起始行
//     * @param endRow     终止行
//     * @param firstCol   起始列
//     * @param endCol     终止列
//     * @return 返回类型 DataValidation
//     */
//    public static DataValidation SetDataValidation(Workbook wb, String strFormula, int firstRow, int endRow, int firstCol, int endCol) {
//        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
//        DataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) wb.getSheet("typelist"));
//        DataValidationConstraint formulaListConstraint = dvHelper.createFormulaListConstraint(strFormula);
//        return dvHelper.createValidation(formulaListConstraint, regions);
//    }

    /** ************************************************************ **/
    /** *************************密码设置**************************** **/
    /** ************************************************************ **/
    /**
     * 设置workbook级别密码
     * XSSWorkbook支持，HSSFWorkbook不支持
     *
     * @param workbook
     * @param password
     */
    @SneakyThrows
    public static void setPassword(Workbook workbook, OutputStream os, String password) {
        if (workbook instanceof XSSFWorkbook) {
            //加密
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
            Encryptor enc = info.getEncryptor();
            enc.confirmPassword(password);
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem();
            OutputStream encryptOutPutStream = enc.getDataStream(poifsFileSystem);
            workbook.write(encryptOutPutStream);
            workbook.close();
            poifsFileSystem.writeFilesystem(os);
        } else {
            workbook.write(os);
        }
    }

    /**
     * 移除密码
     * 仅支持 xlsx
     *
     * @param is
     * @param os
     * @param password
     */
    @SneakyThrows
    public static void removePassword(InputStream is, OutputStream os, String password) {
        try {
            if (!is.markSupported()) {
                is = new PushbackInputStream(is, 8);
            }
            POIFSFileSystem fs = new POIFSFileSystem(is);
            EncryptionInfo info = new EncryptionInfo(fs);
            Decryptor d = Decryptor.getInstance(info);
            d.verifyPassword(password);
            is = d.getDataStream(fs);
            XSSFWorkbook wb = new XSSFWorkbook(OPCPackage.open(is));
            //os = new FileOutputStream("./testStyle-pwd-2.xlsx");
            wb.write(os);
            os.flush();
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }


    /** ************************************************************ **/
    /** *************************导出图片**************************** **/
    /** ************************************************************ **/
    /**
     *
     */
    @SneakyThrows
    public static void excel2image(String excelUrl, String path) {
        float widthZoomFactor = 1;
        float heightZoomFactor = 1;

        //获取Excel文件对象
        Workbook wb = WorkbookFactory.create(new File(excelUrl));
        for (Sheet sheet : wb) {
            //TODO sheet中图片集合，图片大小和位置
            Map<String, ImageInfo> maplist = null;
            maplist = getPictures(sheet);

            // 获取整个sheet中合并单元格组合的集合
            List<CellRangeAddress> rangeAddress = sheet.getMergedRegions();

            //检查区域内是否存在数据
            int rowSize = sheet.getPhysicalNumberOfRows();//获取物理行数
            int colSize = sheet.getRow(0).getPhysicalNumberOfCells();//获取物理列数
            if (rowSize == 0 || colSize == 0) {
                continue;
            }

            // 遍历需要扫描的区域
            // 声明二维数组存自定义Excel数据对象
            CustomCell[][] customCells = new CustomCell[rowSize][colSize];

            //行宽集合//列宽集合
            int[] rowPixPos = new int[rowSize + 1];
            int[] colPixPos = new int[colSize + 1];
            rowPixPos[0] = 0;
            colPixPos[0] = 0;
            float height = 0f;//Excel高度 pt
            float width = 0f;//Excel宽度 px
            for (Row row : sheet) {
                for (Cell cell : row) {
                    int rowIndex = cell.getRowIndex();
                    int columnIndex = cell.getColumnIndex();
                    CustomCell customCell = new CustomCell();
                    customCell.setCell(sheet.getRow(rowIndex).getCell(columnIndex));//Cell对象
                    customCell.setRow(rowIndex);//行索引
                    customCell.setCol(columnIndex);//列索引
                    boolean isShow = !(sheet.isColumnHidden(columnIndex) || sheet.getRow(rowIndex).getZeroHeight());
                    customCell.setShow(isShow);//是否隐藏
                    customCells[cell.getRowIndex()][cell.getColumnIndex()] = customCell;

                    // 计算所求区域宽度 如果该单元格是隐藏的，则置宽度为0单位px
                    float widthPix = !isShow ? 0 : sheet.getColumnWidthInPixels(columnIndex);
                    //Excel宽度：统计列宽，即第一行，第一列结束就确认了图片的宽度
                    if (rowIndex == 0) {
                        width += widthPix;
                    }
                    // 各个单元格结束点列宽坐标位置 * 宽度缩放倍数
                    colPixPos[columnIndex + 1] = (int) (widthPix * widthZoomFactor + colPixPos[columnIndex]);
                }
                // 计算所求区域高度 行序列不能隐藏
                boolean ifShow = !sheet.getRow(row.getRowNum()).getZeroHeight();
                // 如果该单元格是隐藏的，则置高度为0 单位pt
                float heightPoint = !ifShow ? 0 : sheet.getRow(row.getRowNum()).getHeightInPoints();
                //Excel高度：所有行行高累加
                height += heightPoint;
                //单元格在图片中高度坐标位置 1px = 0.75pt ==> 1pt = 100/75px
                rowPixPos[row.getRowNum() + 1] = (int) ((heightPoint * 100 / 75.0) * heightZoomFactor + rowPixPos[row.getRowNum()]);
            }
            //创建图片数据模型:图片大小
            ConvertImage convertImage = new ConvertImage();
            convertImage.setImagePath(path);
            //计算图片高度
            convertImage.setWidth((int) (width * widthZoomFactor));
            convertImage.setHeight((int) ((height * 100 / 75.5) * heightZoomFactor));

            //更具表格制作网格
            List<CustomGrid> grids = getCustomGrid(wb, rangeAddress, rowSize, colSize, customCells, rowPixPos, colPixPos, maplist);
            convertImage.setGrids(grids);
            convertImage.setImageInfoMap(maplist);
            // 绘图
            draw(convertImage);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ImageInfo {
        private int row1;
        private int row2;
        private int col1;
        private int col2;
        private double dx1;
        private double dx2;
        private double dy1;
        private double dy2;
        private PictureData pictureData;
    }

    /**
     * 获取图片和位置 (xls)
     *
     * @param sheet
     * @return
     */
    public static Map<String, ImageInfo> getPictures(Sheet sheet) {

        Map<String, ImageInfo> map = new HashMap<>();
        if (sheet instanceof HSSFSheet) {
            HSSFSheet hssfSheet = (HSSFSheet) sheet;
            //获取绘图族长，获取所有图形对象集合
            List<HSSFShape> list = hssfSheet.getDrawingPatriarch().getChildren();
            //如果图形是图片的话
            for (HSSFShape shape : list) {
                if (shape instanceof HSSFPicture) {
                    HSSFPicture picture = (HSSFPicture) shape;
                    //获取图片锚点对象-->获取锚点所在的（行-列）
                    HSSFClientAnchor anchor = (HSSFClientAnchor) picture.getAnchor();
                    // 行号-列号
                    String key = anchor.getRow1() + "-" + anchor.getCol1();
                    map.put(key, new ImageInfo()
                            .setPictureData(picture.getPictureData())
                            .setDx1(anchor.getDx1() / 360000.0 * 28.3 * 0.75)
                            .setDy1(anchor.getDy1() / 360000.0 * 28.3 * 0.75)
                            .setDx2(anchor.getDx2() / 360000.0 * 28.3 * 0.75)
                            .setDy2(anchor.getDy2() / 360000.0 * 28.3 * 0.75)
                            .setRow1(anchor.getRow1())
                            .setCol1(anchor.getCol1())
                            .setRow2(anchor.getRow2())
                            .setCol2(anchor.getCol2())
                    );
                }
            }
        } else if (sheet instanceof XSSFSheet) {
            XSSFSheet xssfSheet = (XSSFSheet) sheet;
            //获取XML文档对象集合
            List<POIXMLDocumentPart> list = xssfSheet.getRelations();
            //循环，如果是 XSSFDrawing 获取 图形集合
            for (POIXMLDocumentPart part : list) {
                if (part instanceof XSSFDrawing) {
                    XSSFDrawing drawing = (XSSFDrawing) part;
                    List<XSSFShape> shapes = drawing.getShapes();
                    for (XSSFShape shape : shapes) {
                        //获取Picture类型对象
                        XSSFPicture picture = (XSSFPicture) shape;
                        //获取图片锚点信息对象
                        XSSFClientAnchor anchor = picture.getPreferredSize();
                        CTMarker marker = anchor.getFrom();
                        // 行号-列号
                        String key = marker.getRow() + "-" + marker.getCol();
                        map.put(key, new ImageInfo()
                                .setDx1(anchor.getDx1() / 360000.0 * 28.3 * 0.75)
                                .setDy1(anchor.getDy1() / 360000.0 * 28.3 * 0.75)
                                .setDx2(anchor.getDx2() / 360000.0 * 28.3 * 0.75)
                                .setDy2(anchor.getDy2() / 360000.0 * 28.3 * 0.75)
                                .setRow1(anchor.getRow1())
                                .setCol1(anchor.getCol1())
                                .setRow2(anchor.getRow2())
                                .setCol2(anchor.getCol2())
                                .setPictureData(picture.getPictureData())
                        );
                    }
                }
            }
        }
        return map;
    }

    /**
     * 根据表格制作网格
     *
     * @param wb           excel
     * @param rangeAddress 合并单元格组合
     * @param rowSize      行大小
     * @param colSize      列大小
     * @param customCells  单元格
     * @param rowPixPos    需要扫描的行大小
     * @param colPixPos    需要扫描的列大小
     * @return 网格数据
     */
    private static List<CustomGrid> getCustomGrid(Workbook wb, List<CellRangeAddress> rangeAddress, int rowSize, int colSize, CustomCell[][] customCells, int[] rowPixPos, int[] colPixPos, Map<String, ImageInfo> dataMap) {
        List<CustomGrid> grids = new ArrayList<>();

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (customCells[i][j] == null) {
                    continue;
                }
                //单元格自定义对象
                Cell cell = customCells[i][j].getCell();
                //设置坐标和宽高
                CustomGrid grid = new CustomGrid();
                //单元格左上角起始坐标
                grid.setX(colPixPos[j]);// 起始x坐标
                grid.setY(rowPixPos[i]);// 起始y坐标
                grid.setWidth(colPixPos[j + 1] - colPixPos[j]);//单元格宽度
                grid.setHeight(rowPixPos[i + 1] - rowPixPos[i]);//单元格高度
                grid.setRow(customCells[i][j].getRow());//单元格坐标
                grid.setCol(customCells[i][j].getCol());//单元格坐标
                grid.setShow(customCells[i][j].isShow());//是否显示

                /*
                 判断是否为合并单元格
                 {-1,-1}:表示不是合并单元格
                 {0,0}:表示是合并单元格，但不是起始单元格
                 {x,x}:合并单元格，末尾位置
                 */
                int[] isInMergedStatus = isInMerged(grid.getRow(), grid.getCol(), rangeAddress);
                if (cell == null || (isInMergedStatus[0] == 0 && isInMergedStatus[1] == 0)) {
                    continue;
                } else if (isInMergedStatus[0] != -1 && isInMergedStatus[1] != -1) {
                    // 此单元格是合并单元格，并且属于第一个单元格，则需要调整网格大小
                    int lastRowPos = Math.min(isInMergedStatus[0], rowSize - 1);
                    int lastColPos = Math.min(isInMergedStatus[1], colSize - 1);
                    grid.setWidth(colPixPos[lastColPos + 1] - colPixPos[j]);
                    grid.setHeight(rowPixPos[lastRowPos + 1] - rowPixPos[i]);
                }
                // 单元格背景颜色
                CellStyle cs = cell.getCellStyle();
                if (cs.getFillPatternEnum() == FillPatternType.SOLID_FOREGROUND) {
                    grid.setBgColor(cell.getCellStyle().getFillForegroundColorColor());
                }
                // 设置字体
                Font font = wb.getFontAt(cs.getFontIndex());
                grid.setFont(font);

                // 设置字体前景色
                if (font instanceof XSSFFont) {
                    XSSFFont xf = (XSSFFont) font;
                    grid.setFtColor(xf.getXSSFColor());
                } else {
                    HSSFFont xf = (HSSFFont) font;
                    grid.setFtColor(xf.getHSSFColor((HSSFWorkbook) cell.getRow().getSheet().getWorkbook()));
                }
                // 设置文本
                String strCell;
                strCell = getCellContentAsString(cell);

                // 如果为空可能单元格是图片
                if ("".equals(strCell)) {
                    ImageInfo imageInfo = dataMap.get(i + "-" + j);

                    //设置图片数据
                    if (imageInfo != null && imageInfo.getPictureData() != null) {
                        //计算出图片起始坐标
                        //前面已经设置过了
                        //grid.setX(colPixPos[j]);// 起始x坐标
                        //grid.setY(rowPixPos[i]);// 起始y坐标

                        //计算出图片的宽高
                        double imageWidth = colPixPos[imageInfo.getCol2()] - colPixPos[imageInfo.getCol1()] - imageInfo.dx1 + imageInfo.dx2;
                        double imageHeight = rowPixPos[imageInfo.getRow2()] - rowPixPos[imageInfo.getRow1()] - imageInfo.dy1 + imageInfo.dy2;
                        grid.setImageWidth(imageWidth);
                        grid.setImageHeight(imageHeight);
                        grid.setImage(imageInfo.getPictureData().getData());
                    }
                }

//                // 处理百分比
//                if (cell.getCellStyle().getDataFormatString().contains("0.00%")) {
//                    try {
//                        double dbCell = Double.parseDouble(strCell);
//                        strCell = new DecimalFormat("#.00").format(dbCell * 100) + "%";
//                    } catch (NumberFormatException ignored) {
//                    }
//                }
//                grid.setText(strCell.matches("\\w*\\.0") ? strCell.substring(0, strCell.length() - 2) : strCell);
                grid.setText(strCell);
                grids.add(grid);
            }
        }
        return grids;
    }

    /**
     * 判断Excel中的单元格是否为合并单元格
     *
     * @param row          当前行号
     * @param col          当前列号
     * @param rangeAddress 整个sheet中合并单元格组合的集合
     * @return 如果不是合并单元格返回{-1,-1},如果是合并单元格并且是一个单元格返回{lastRow,lastCol},
     * 如果是合并单元格并且不是第一个格子返回{0,0}
     */
    private static int[] isInMerged(int row, int col, List<CellRangeAddress> rangeAddress) {
        int[] isInMergedStatus = {-1, -1};
        for (CellRangeAddress cra : rangeAddress) {
            if (row == cra.getFirstRow() && col == cra.getFirstColumn()) {
                isInMergedStatus[0] = cra.getLastRow();
                isInMergedStatus[1] = cra.getLastColumn();
                return isInMergedStatus;
            }
            if (row >= cra.getFirstRow() && row <= cra.getLastRow() && col >= cra.getFirstColumn() && col <= cra.getLastColumn()) {
                isInMergedStatus[0] = 0;
                isInMergedStatus[1] = 0;
                return isInMergedStatus;
            }
        }
        return isInMergedStatus;
    }


    /**
     * 绘图
     *
     * @param convertImage 绘图数据模型
     */
    public static void draw(final ConvertImage convertImage) throws IOException {
        //创建一张2D图片
        BufferedImage image = new BufferedImage(convertImage.getWidth(), convertImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        // 平滑字体 TODO 没看懂
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
//        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
//        g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 140);
//        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
        //绘制背景
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, convertImage.getWidth(), convertImage.getHeight());

        // 绘制表格
        for (CustomGrid g : convertImage.getGrids()) {
            //跳过隐藏内容
            if (!g.isShow()) {
                continue;
            }
            // 绘制单元格背景色
            g2d.setColor(g.getBgColor() == null ? Color.white : g.getBgColor());
            g2d.fillRect(g.getX(), g.getY(), g.getWidth(), g.getHeight());

            // 绘制边框
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawRect(g.getX(), g.getY(), g.getWidth(), g.getHeight());

            // 绘制文字,居中显示
            g2d.setColor(g.getFtColor());
            java.awt.Font font = g.getFont();
            FontMetrics fm = g2d.getFontMetrics(font);
            // 获取将要绘制的文字宽度
            int strWidth = fm.stringWidth(g.getText());
            g2d.setFont(font);
            // 图片
            if (g.getImage() != null && g.getImage().length > 0) {

            }
            // 文字
            else {
                g2d.drawString(g.getText(), g.getX() + (g.getWidth() - strWidth) / 2, g.getY() + (g.getHeight() - font.getSize()) / 2 + font.getSize());
            }
        }

        for (CustomGrid g : convertImage.getGrids()) {
            // 图片
            if (g.getImage() != null && g.getImage().length > 0) {
                InputStream inputStream = new ByteArrayInputStream(g.getImage());
                BufferedImage bg = ImageIO.read(inputStream);
                System.out.println(g.getWidth() + "," + g.getHeight());
                System.out.println(g.getImageWidth() + "," + g.getImageHeight());
                g2d.drawImage(bg.getScaledInstance((int) g.getImageWidth(), (int) g.getImageHeight(), Image.SCALE_DEFAULT), g.getX(), g.getY(), null);
            }
        }
        g2d.dispose();
        String imgUrl = convertImage.getImagePath() + File.separator + System.currentTimeMillis() + ".png";
        ImageIO.write(image, "png", new File(imgUrl));
    }


    @Data
    public static class CustomCell implements Comparable<UserCell> {
        //poi excel 单元格对象
        private Cell cell;
        //第几行
        private int row;
        //第几列
        private int col;
        //是否显示
        private boolean show;

        private String text = "";


        private Color color = null;

        @Override
        public int compareTo(UserCell uc) {
            try {
                double meDouble = Double.parseDouble(this.getText().replace("%", ""));
                double heDouble = Double.parseDouble(uc.getText().replace("%", ""));
                if (meDouble < heDouble) {
                    return -1;
                } else if (meDouble > heDouble) {
                    return 1;
                }
            } catch (Exception ignored) {
            }

            return 0;
        }
    }


    @Data
    public static class UserCell implements Comparable<UserCell> {
        private Cell cell;
        private int row;
        private int col;
        private boolean show;
        private String text = "";
        private Color color = null;

        @Override
        public int compareTo(UserCell uc) {
            try {
                double meDouble = Double.parseDouble(this.getText().replace("%", ""));
                double heDouble = Double.parseDouble(uc.getText().replace("%", ""));
                if (meDouble < heDouble) {
                    return -1;
                } else if (meDouble > heDouble) {
                    return 1;
                }
            } catch (Exception ignored) {
            }
            return 0;
        }
    }

    @Data
    public static class CustomGrid {
        private int row;
        private int col;
        private boolean show;

        private int x;
        private int y;
        private int width;
        private int height;


        private Color bgColor = null;
        private Color ftColor = null;


        /**
         * 字体 new Font("微软雅黑", Font.PLAIN, 12);
         */
        private java.awt.Font font;
        private String text;

        private byte[] image;
        private double imageWidth;
        private double imageHeight;


        public void setFont(Font font) {
            if (font != null) {
                this.font = new java.awt.Font(font.getFontName(), java.awt.Font.PLAIN, font.getFontHeight() / 20);
            }
        }

        public void setFtColor(org.apache.poi.ss.usermodel.Color color) {
            this.ftColor = poiColor2awtColor(color);
        }

        /**
         * 将poi.ss.usermodel.Color 转换成  java.awt.Color
         * <a href="http://home.cnblogs.com/u/309701/" target="_blank">@param</a> color
         */
        public void setBgColor(org.apache.poi.ss.usermodel.Color color) {
            //Excel颜色转awt颜色
            this.bgColor = poiColor2awtColor(color);
        }

        private Color poiColor2awtColor(org.apache.poi.ss.usermodel.Color color) {
            Color awtColor = null;
            //.xlsx
            if (color instanceof XSSFColor) {
                XSSFColor xc = (XSSFColor) color;
                String rgbHex = xc.getARGBHex();
                if (rgbHex != null) {
                    Integer[] argb = setARGBHex(rgbHex);
                    if (argb.length == 4) {
                        awtColor = new Color(argb[1], argb[2], argb[3], argb[0]);
                    } else {
                        awtColor = new Color(argb[1], argb[2], argb[3]);
                    }
                }
            }
            //.xls
            else if (color instanceof HSSFColor) {
                HSSFColor hc = (HSSFColor) color;
                short[] s = hc.getTriplet();
                if (s != null) {
                    awtColor = new Color(s[0], s[1], s[2]);
                }
            }
            return awtColor;
        }

        public Integer[] setARGBHex(String argb) {
            if (argb.length() == 6 || argb.length() == 8) {
                Integer[] rgb = new Integer[argb.length() / 2];
                for (int i = 0; i < rgb.length; i++) {
                    String part = argb.substring(i * 2, (i + 1) * 2);
                    rgb[i] = Integer.parseInt(part, 16);
                }
                return rgb;
            } else {
                throw new IllegalArgumentException("Must be of the form 112233 or FFEEDDCC");
            }
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConvertImage {
        //高度：px
        private int height;
        //宽度：px
        private int width;
        //图片输出路径
        private String imagePath;
        //数据集合
        private List<CustomGrid> grids;
        //图片数据集合
        private Map<String, ImageInfo> imageInfoMap;
    }

}
