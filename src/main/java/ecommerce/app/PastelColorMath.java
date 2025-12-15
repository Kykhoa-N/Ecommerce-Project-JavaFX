package ecommerce.app;

public class PastelColorMath {

    public static int[] modernDirtyRgb(int r, int g, int b) {
        double[] hsl = rgbToHsl(r, g, b);
        double h = hsl[0], s = hsl[1], l = hsl[2];

        double sMul, lBoost;

        // Hue-aware tuning (prevents orange->beige, green->neon)
        if (h >= 20 && h <= 65) {              // orange/yellow
            sMul = 0.76;
            lBoost = 0.30;
            h = h - 4.0;   // tiny warm shift toward amber
            if (h < 0) h += 360;
        } else if (h < 20 || h >= 330) {       // red
            sMul = 0.55;
            lBoost = 0.20;
        } else if (h >= 90 && h <= 160) {      // green
            sMul = 0.40;
            lBoost = 0.25;
        } else {                               // blue/purple/etc
            sMul = 0.45;
            lBoost = 0.22;
        }

        s = clamp01(s * sMul);
        l = clamp01(l + (1.0 - l) * lBoost);

        return hslToRgb(h, s, l);
    }

    private static double clamp01(double x) {
        return Math.max(0.0, Math.min(1.0, x));
    }

    // h in [0,360), s,l in [0,1]
    public static double[] rgbToHsl(int r, int g, int b) {
        double rd = r / 255.0, gd = g / 255.0, bd = b / 255.0;

        double max = Math.max(rd, Math.max(gd, bd));
        double min = Math.min(rd, Math.min(gd, bd));
        double delta = max - min;

        double h = 0.0;
        double l = (max + min) / 2.0;
        double s = 0.0;

        if (delta != 0.0) {
            s = delta / (1.0 - Math.abs(2.0 * l - 1.0));

            if (max == rd) {
                h = 60.0 * (((gd - bd) / delta) % 6.0);
            } else if (max == gd) {
                h = 60.0 * (((bd - rd) / delta) + 2.0);
            } else { // max == bd
                h = 60.0 * (((rd - gd) / delta) + 4.0);
            }
        }

        if (h < 0) h += 360.0;
        return new double[]{h, s, l};
    }

    public static int[] hslToRgb(double h, double s, double l) {
        double c = (1.0 - Math.abs(2.0 * l - 1.0)) * s;
        double x = c * (1.0 - Math.abs(((h / 60.0) % 2.0) - 1.0));
        double m = l - c / 2.0;

        double r1 = 0, g1 = 0, b1 = 0;

        if (0 <= h && h < 60) { r1 = c; g1 = x; b1 = 0; }
        else if (60 <= h && h < 120) { r1 = x; g1 = c; b1 = 0; }
        else if (120 <= h && h < 180) { r1 = 0; g1 = c; b1 = x; }
        else if (180 <= h && h < 240) { r1 = 0; g1 = x; b1 = c; }
        else if (240 <= h && h < 300) { r1 = x; g1 = 0; b1 = c; }
        else { r1 = c; g1 = 0; b1 = x; }

        int r = (int)Math.round((r1 + m) * 255.0);
        int g = (int)Math.round((g1 + m) * 255.0);
        int b = (int)Math.round((b1 + m) * 255.0);

        r = Math.max(0, Math.min(255, r));
        g = Math.max(0, Math.min(255, g));
        b = Math.max(0, Math.min(255, b));

        return new int[]{r, g, b};
    }
}