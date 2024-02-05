package worter;

public class HexToRgb {
    public static void main(String[] args) {
        String hexColor = "#FF0000"; // 示例16进制颜色值
        int r = hexColorToRgb(hexColor, 0);
        int g = hexColorToRgb(hexColor, 2);
        int b = hexColorToRgb(hexColor, 4);
        System.out.println("RGB Color: (" + r + ", " + g + ", " + b + ")");
    }

    public static int hexColorToRgb(String hexColor, int index) {
        // 删除开头的#号
        hexColor = hexColor.replace("#", "");
        // 将16进制颜色值转换为10进制整数
        int value = Integer.parseInt(hexColor.substring(index, index + 2), 16);
        // 返回RGB格式的颜色值
        return value;
    }
}
