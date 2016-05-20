package cn.steve.deeplink;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.ZipFile;

/**
 * source from http://pingguohe.net/2016/03/21/Dynimac-write-infomation-into-apk.html
 *
 * 这个部分是在服务器端执行的
 *
 * Created by yantinggeng on 2016/5/20.
 */
public class WriteComment2APK {

    public static void writeApk(File file, String comment) {
        ZipFile zipFile = null;
        ByteArrayOutputStream outputStream = null;
        RandomAccessFile accessFile = null;
        try {
            zipFile = new ZipFile(file);
            String zipComment = zipFile.getComment();
            if (zipComment != null) {
                return;
            }

            byte[] byteComment = comment.getBytes();
            outputStream = new ByteArrayOutputStream();

            outputStream.write(byteComment);
            outputStream.write(short2Stream((short) byteComment.length));

            byte[] data = outputStream.toByteArray();

            accessFile = new RandomAccessFile(file, "rw");
            accessFile.seek(file.length() - 2);
            accessFile.write(short2Stream((short) data.length));
            accessFile.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zipFile != null) {
                    zipFile.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (accessFile != null) {
                    accessFile.close();
                }
            } catch (Exception e) {

            }

        }
    }


    /**
     * 字节数组转换成short（小端序）
     */
    private static byte[] short2Stream(short data) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putShort(data);
        buffer.flip();
        return buffer.array();
    }
}
