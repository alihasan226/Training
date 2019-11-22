package com.oasissnacks.oasissnacks.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.Hashtable;

public class TypefaceCache {

    private static final String BUZZ_FONT_OPEN_SANS_BOLD = "fonts/OpenSans_Bold.ttf";
    private static final String BUZZ_FONT_OPEN_SANS_BOLD_ITALIC= "fonts/OpenSans_BoldItalic.ttf";
    private static final String BUZZ_FONT_OPEN_SANS_ITALIC = "fonts/OpenSans_Italic.ttf";
    private static final String BUZZ_FONT_OPEN_SANS_LIGHT = "fonts/OpenSans_Light.ttf";
    private static final String BUZZ_FONT_OPEN_SANS_LIGHT_ITALIC = "fonts/OpenSans_LightItalic.ttf";
    private static final String BUZZ_FONT_OPEN_SANS_REGULAR = "fonts/OpenSans_Regular.ttf";
    private static final String BUZZ_FONT_OPEN_SANS_SEMI_BOLD = "fonts/OpenSans_SemiBold.ttf";

    private static final Hashtable<String, Typeface> CACHE = new Hashtable<String, Typeface>();

    public static Typeface get(AssetManager manager, int typefaceCode) {
        synchronized (CACHE) {
            String typefaceName = getTypefaceName(typefaceCode);
            if (!CACHE.containsKey(typefaceName)) {
                Typeface t = Typeface.createFromAsset(manager, typefaceName);
                CACHE.put(typefaceName, t);
            }
            return CACHE.get(typefaceName);
        }
    }

    private static String getTypefaceName(int typefaceCode) {
        String typefaceTemp = "";
        switch (typefaceCode) {
            case 0:
                typefaceTemp =BUZZ_FONT_OPEN_SANS_BOLD;
                break;
            case 1:
                typefaceTemp =BUZZ_FONT_OPEN_SANS_BOLD_ITALIC;
                break;
            case 2:
                typefaceTemp =BUZZ_FONT_OPEN_SANS_ITALIC;
                break;
            case 3:
                typefaceTemp =BUZZ_FONT_OPEN_SANS_LIGHT;
                break;
            case 4:
                typefaceTemp =BUZZ_FONT_OPEN_SANS_LIGHT_ITALIC;
                break;
            case 5:
                typefaceTemp =BUZZ_FONT_OPEN_SANS_REGULAR;
                break;
            case 6:
                typefaceTemp =BUZZ_FONT_OPEN_SANS_SEMI_BOLD;
                break;

            default:
                typefaceTemp =BUZZ_FONT_OPEN_SANS_LIGHT;
        }
        return typefaceTemp;
    }// End of getTypefaceName method
}// End of TypefaceCache Class
