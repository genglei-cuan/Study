package steve.cn.mylib.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 用于图片的二次采样
 * <p/>
 * Created by Steve on 2015/8/16.
 */
public class BitmapCompressTools {

    /**
     * 根据目标图片大小来计算Sample图片大小
     * <p/>
     * note:设置inSampleSize为2的幂是因为解码器最终还是会对非2的幂的数进行向下处理，获取到最靠近2的幂的数
     */
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    /**
     * 为了使用该方法，首先需要设置 inJustDecodeBounds 为 true, 把options的值传递过来，
     * 然后设置 inSampleSize 的值并设置 inJustDecodeBounds 为 false，之后重新调用相关的解码方法。
     *
     * @param res       resource对象
     * @param resId     图片资源ID
     * @param reqWidth  目标的宽度
     * @param reqHeight 目标的高度
     * @return 返回对象的bitmap对象
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设置为true，则不会读取全部的图片信息，只用来读取图片的轮廓信息
        BitmapFactory.decodeResource(res, resId, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;//重置，以得到图片对象
        return BitmapFactory.decodeResource(res, resId, options);
    }

}
