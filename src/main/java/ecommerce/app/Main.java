package ecommerce.app;

import ecommerce.customUI.UIColorMath;

public class Main {

    public static void colorPrint(int r, int g, int b) {
        int[] ui = UIColorMath.modernDirtyRgb(r,g,b);
        System.out.printf("UI Color: %s\n\n", UIColorMath.toHex(ui[0],ui[1],ui[2]));
    }
    public static void main(String[] args) {
        colorPrint(255, 255, 0);

    }
}