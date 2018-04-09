package com.example.pc.staysafe.utils;

import android.graphics.Color;

public class ColorUtils {

    public static int lighten(int color, double fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = Color.alpha(color);

        red = lightenColor(red, fraction);
        green = lightenColor(green, fraction);
        blue = lightenColor(blue, fraction);

        return Color.argb(alpha, red, green, blue);
    }

    public static int darken(int color, double fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = Color.alpha(color);

        red = darkenColor(red, fraction);
        green = darkenColor(green, fraction);
        blue = darkenColor(blue, fraction);

        return Color.argb(alpha, red, green, blue);
    }

    private static int darkenColor(int color, double fraction) {
        return (int) Math.max(color - (color * fraction), 0);
    }

    private static int lightenColor(int color, double fraction) {
        return (int) Math.min(color + (color * fraction), 255);
    }
}
