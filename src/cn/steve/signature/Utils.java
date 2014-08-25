package cn.steve.signature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.os.Environment;

public class Utils {
	/**
	 * 功能：将签好名的bitmap保存到sd卡
	 * 
	 * @param bitmap，待保存的图片
	 */
	public static void storeInSD(Bitmap bitmap) {
		
		String SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String currentSystemTime = formatter.format(curDate);
		
		File file = new File(SDCardRoot+"/签名");// 要保存的文件的文件夹地址
		
		if (!file.exists()) {
			file.mkdir();
		}
		
		//文件的最终路径
		File imageFile = new File(file, currentSystemTime + ".png");
		
		//文件读写，将文件以二进制流的形式保存到指定的路径之中
		try {
			imageFile.createNewFile();
			
			System.out.println(imageFile);
			
			FileOutputStream fos = new FileOutputStream(imageFile);
			
			if (fos==null) {
				System.out.println("wwwwww");
			}
			if (bitmap==null) {
				System.out.println("kkkkkkk");
			}
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
