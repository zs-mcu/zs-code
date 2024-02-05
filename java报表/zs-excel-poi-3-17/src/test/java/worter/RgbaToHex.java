package worter;

public class RgbaToHex {
    public static void main(String[] args) {
        int red = 255;
        int green = 200;
        int blue = 150;
        float alpha = 0.8f;
        String hexColor = rgbaToHex(red, green, blue, alpha);
        System.out.println("Hex Color: " + hexColor);
    }

    public static String rgbaToHex(int red, int green, int blue, float alpha) {
        String hexRed = Integer.toHexString(red);
        String hexGreen = Integer.toHexString(green);
        String hexBlue = Integer.toHexString(blue);
        String hexA = Float.toHexString(alpha * 255);

        // 在需要的情况下，补全为2位16进制数
        if (hexRed.length() == 1) {
            hexRed = "0" + hexRed;
        }
        if (hexGreen.length() == 1) {
            hexGreen = "0" + hexGreen;
        }
        if (hexBlue.length() == 1) {
            hexBlue = "0" + hexBlue;
        }
        if (hexA.length() == 1) {
            hexA = "0" + hexA;
        }

        // 返回16进制颜色值，包括alpha通道
        return "#" + hexRed + hexGreen + hexBlue + hexA;
    }
}
