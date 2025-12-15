package ecommerce.app;

public class Main {

    public static void colorPrint(int r, int g, int b) {
        int[] ui = UIColorMath.modernDirtyRgb(r,g,b);
        System.out.printf("UI Color: %7d, %d, %d\n\n", ui[0],ui[1],ui[2]);
    }

    public static void main(String[] args) {
        System.out.println("ORANGE");
        colorPrint(255, 125, 0);
        System.out.println("RED");
        colorPrint(255, 0, 0);
        System.out.println("YELLOW");
        colorPrint(255, 255, 0);
        System.out.println("BLUE");
        colorPrint(0,0, 255);
        System.out.println("GREEN");
        colorPrint(0, 255, 0);

    }
}