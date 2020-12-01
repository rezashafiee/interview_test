package shafiee.mr.interviewtest.utils;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {

    private static Typeface defaultTypeface;
    private static Typeface SFUIDisplayBold;
    private static Typeface SFUIDisplaySemiBold;
    private static Typeface SFUIDisplayRegular;
    private static Typeface SFUIDisplayLight;

    public static Typeface getTypeface(Context context, int index) {
        switch (index) {
            case 7:
                setSFUIDisplayBoldTypeface(context);
                return SFUIDisplayBold;
            case 8:
                setSFUIDisplaySemiBoldTypeface(context);
                return SFUIDisplaySemiBold;
            case 10:
                setSFUIDisplayRegularTypeface(context);
                return SFUIDisplayRegular;
            case 11:
                setSFUIDisplayLightTypeface(context);
                return SFUIDisplayLight;
            default:
                setDefaultTypeface(context);
                return defaultTypeface;
        }
    }

    private static void setDefaultTypeface(Context context) {
        if (defaultTypeface == null)
            defaultTypeface = Typeface.createFromAsset(context.getAssets(),
                    "fonts/SFUIDisplay_Regular.otf");
    }

    private static void setSFUIDisplayBoldTypeface(Context context) {
        if (SFUIDisplayBold == null)
            SFUIDisplayBold = Typeface.createFromAsset(context.getAssets(),
                    "fonts/SFUIDisplay_Bold.otf");
    }

    private static void setSFUIDisplaySemiBoldTypeface(Context context) {
        if (SFUIDisplaySemiBold == null) {
            SFUIDisplaySemiBold = Typeface.createFromAsset(context.getAssets(),
                    "fonts/SFUIDisplay_Semibold.otf");
        }
    }

    private static void setSFUIDisplayRegularTypeface(Context context) {
        if (SFUIDisplayRegular == null) {
            SFUIDisplayRegular = Typeface.createFromAsset(context.getAssets(),
                    "fonts/SFUIDisplay_Regular.otf");
        }
    }

    private static void setSFUIDisplayLightTypeface(Context context) {
        if (SFUIDisplayLight == null) {
            SFUIDisplayLight = Typeface.createFromAsset(context.getAssets(),
                    "fonts/SFUIDisplay_Light.otf");
        }
    }
}
