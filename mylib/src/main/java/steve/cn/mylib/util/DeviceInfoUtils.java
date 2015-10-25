package steve.cn.mylib.util;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Device utility functions
 */
public class DeviceInfoUtils {

    private static UUID uuid = null;

    /**
     * Returns unique UUID of the device
     *
     * @param context Context
     * @return UUID
     */
    public static UUID getDeviceUUID(Context context) {
        if (context == null) {
            return null;
        }
        if (uuid == null) {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String id = tm.getDeviceId();
            if (id != null) {
                uuid = UUID.nameUUIDFromBytes(id.getBytes());
            }
        }
        return uuid;
    }

    /**
     * return CPU type
     */
    public static String getCpuType() {
        String arch = System.getProperty("os.arch");
        return arch;
    }

    public static int getAndroidSDKVersion() {
        int sdk = Build.VERSION.SDK_INT;
        return sdk;
    }

    /**
     * Returns the consumer friendly device name
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        if (manufacturer.equalsIgnoreCase("HTC")) {
            // make sure "HTC" is fully capitalized.
            return "HTC " + model;
        }
        return capitalize(manufacturer) + " " + model;
    }

    // Brand
    public static String getDeviceBrand() {
        String brand = Build.BRAND;
        return brand;
    }

    // BOOTLOADER
    public static String getDeviceBootloader() {
        String Bootloader = Build.BOOTLOADER;
        return Bootloader;

    }

    // Model
    public static String getDeviceModel() {
        String Model = Build.MODEL;
        return Model;

    }

    // MANUFACTURER
    public static String getDeviceManufacturer() {
        String Manufacturer = Build.MANUFACTURER;
        return Manufacturer;

    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }

}
