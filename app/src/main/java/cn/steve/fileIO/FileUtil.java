package cn.steve.fileIO;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    Context context;

    public FileUtil(Context context) {
        this.context = context;
    }

    // read
    public String readFile(String fileName, boolean isSDcard) throws Exception {
        FileInputStream mfileFileInputStream = null;
        if (isSDcard) {
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            mfileFileInputStream = new FileInputStream(file);
        } else {
            mfileFileInputStream = context.openFileInput(fileName);
        }
        ByteArrayOutputStream bouts = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = mfileFileInputStream.read(buf)) != -1) {
            bouts.write(buf, 0, len);
        }
        byte[] data = bouts.toByteArray();
        mfileFileInputStream.close();
        bouts.close();
        return new String(data);
    }

    // output
    public boolean outputFile(String fileName, String content, boolean isSDcard) {
        boolean is_success = true;
        FileOutputStream mFileOutFileStream = null;
        try {
            if (isSDcard) {
                File file = null;
                // 文件保存到SDcard卡上
                // Environment.getExternalStorageDirectory()表示取得SDcard的路径，相当于"/sdcard/"
                if (ExistSDCard()) {
                    file = new File(Environment.getExternalStorageDirectory(), fileName);
                } else {
                    file = new File(fileName);
                }
                mFileOutFileStream = new FileOutputStream(file);
            } else {
                mFileOutFileStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            }
        } catch (FileNotFoundException e) {
            is_success = false;
        }
        try {
            mFileOutFileStream.write(content.getBytes());
            mFileOutFileStream.close();
        } catch (IOException e) {
            is_success = false;
            e.printStackTrace();
        }
        return is_success;
    }

    private boolean ExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
            android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
