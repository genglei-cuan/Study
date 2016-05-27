package cn.steve.deeplink;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by yantinggeng on 2016/5/20.
 */
public class ReadCommentFromAPK {

    public static String getCommentFromApk(Context context) {
        String packagePath = getPackagePath(context.getApplicationContext());
        File file = new File(packagePath);
        return readApk(file);
    }


    //首先获取apk的路径，通过context中的getPackageCodePath()方法就可以获取
    private static String getPackagePath(Context context) {
        if (context != null) {
            return context.getPackageCodePath();
        }
        return null;
    }

    private static String readApk(File file) {
        byte[] bytes;
        try {
            RandomAccessFile accessFile = new RandomAccessFile(file, "r");
            long index = accessFile.length();

            bytes = new byte[2];
            index = index - bytes.length;
            accessFile.seek(index);
            accessFile.readFully(bytes);

            int contentLength = stream2Short(bytes, 0);

            bytes = new byte[contentLength];
            index = index - bytes.length;
            accessFile.seek(index);
            accessFile.readFully(bytes);

            return new String(bytes, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * short转换成字节数组（小端序）
     */
    private static short stream2Short(byte[] stream, int offset) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(stream[offset]);
        buffer.put(stream[offset + 1]);
        return buffer.getShort(0);
    }
}
