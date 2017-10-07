package com.peony.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 判断上传图片 
 * @author 刘达康
 *
 */
public class ImageUtil {
	public static Boolean isPass(File img, Integer limitLength, String ContentType){
		List<String> list = new ArrayList<String>();
		list.add("image/gif");
		list.add("image/jpeg");
		list.add("image/png");
		list.add("image/bmp");
		if(img == null)return false;
		if(img.length() > limitLength)return false;
		if(!list.contains(ContentType))return false;
		return true;
	}
	
	public static Boolean isPass(File img[], Integer limitLength, String ContentType[]){
		List<String> list = new ArrayList<String>();
		list.add("image/gif");
		list.add("image/jpeg");
		list.add("image/png");
		list.add("image/bmp");
		if(img == null || img.length == 0)return false;
		for(int i=0;i<img.length;i++){
			if(img[i].length() > limitLength)return false;
			if(!list.contains(ContentType[i]))return false;
		}
		return true;
	}
	
	public static String reName(String userName, String imgName){
		return userName + "_" 
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())
				+ imgName.substring(imgName.indexOf("."), imgName.length());
	}
	
	public static String[] reName(String userName, String imgName[]){
		String[] names = new String[imgName.length];
		for(int i=0;i<imgName.length;i++){
			names[i] = userName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()) 
					+ "_" + i + imgName[i].substring(imgName[i].indexOf("."), imgName[i].length());
		}
		return names; 
	}
	
	/**
	 * 图片缩放
	 * @param inFileName 原图完整名
	 * @param outFileName 缩放图完整名
	 * @param reSize 缩小倍数
	 */
	public static void reImgSize(String inFileName, String outFileName, Float reSize){
		ByteArrayOutputStream ot = null; 
		FileOutputStream out = null;
		try {
			File img = new File(inFileName);
			BufferedImage im = ImageIO.read(img);
			
			int width = im.getWidth();//原始图像的宽度和高度
			int height = im.getHeight();
			
			int toWidth = (int) (Float.parseFloat(String.valueOf(width)) / reSize);//调整后的图片的宽度和高度
			int toHeight = (int) (Float.parseFloat(String.valueOf(height)) / reSize);
			
			BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);//新生成结果图片
			result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
			
			ot = new ByteArrayOutputStream();
			ImageIO.write(result, "gif", ot);
			byte[] imgs = ot.toByteArray();
			
			out = new FileOutputStream(new File(outFileName)); 
			out.write(imgs);
			
			ot.close();
			out.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("数值类型异常", e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("文件类型异常", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("输入输出异常", e);
		} finally{
			try {
				ot.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("流关闭异常", e);
			}
		}
	}
	
	/**
	 * 图片缩放
	 * @param inFileName 原图完整名
	 * @param outFileName 缩放图完整名
	 * @param reSize 缩小倍数
	 */
	public static void reImgSize(String url, String method, String outFileName, Float reSize){
		ByteArrayOutputStream ot = null; 
		FileOutputStream out = null;
		try {
			URL imgurl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) imgurl.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			
			BufferedImage im = ImageIO.read(conn.getInputStream());
			
			int width = im.getWidth();/*原始图像的宽度和高度*/
			int height = im.getHeight();
			
			int toWidth = (int) (Float.parseFloat(String.valueOf(width)) / reSize);/*调整后的图片的宽度和高度*/
			int toHeight = (int) (Float.parseFloat(String.valueOf(height)) / reSize);
			
			BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);/*新生成结果图片*/
			result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
			
			ot = new ByteArrayOutputStream();
			ImageIO.write(result, "gif", ot);
			byte[] imgs = ot.toByteArray();
			
			out = new FileOutputStream(new File(outFileName)); 
			out.write(imgs);
			
			ot.close();
			out.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("数值类型异常", e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("文件类型异常", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("输入输出异常", e);
		} finally{
			try {
				ot.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("流关闭异常", e);
			}
		}
	}
	
	public static void main(String[] args) {
		reImgSize("http://thumb.115.com/thumb/A/0B/FB/A0BFB358BD4AD2BB17714B8DF6F8A0A1560AC4DD_800_800?s=iYKm4kBVNa56CPcuFpN2-Q&t=1433491200&sync=1", "C:\\Users\\Administrator\\Desktop\\p3.png", 1f);
	}
}
