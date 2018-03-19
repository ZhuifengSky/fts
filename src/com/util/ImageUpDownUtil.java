package com.util;

import java.io.InputStream;
import java.util.Date;

public class ImageUpDownUtil {

	//图片上传目录
    public static String imagePath = EnvironmentUtil.getInstance().getPropertyValue("image.savePath");
	
    /**
     * 图片上传
     * @param useOriginalFileName
     * @param filName
     * @param suffix
     * @param originFilePath 绝对路径
     * @return
     */
	public static String upImage(Boolean useOriginalFileName,String fileName, String suffix,String originFilePath){
		boolean flag = false;
		String folderName = DateUtil.format(new Date(), DateUtil.DATE_NO);
		String path = imagePath + "/" + folderName;
		if (useOriginalFileName) {
			flag = FtpUtils.uploadFile(path, fileName, originFilePath);			
		}else{
			fileName = DateUtil.format(new Date(), DateUtil.DATE_NO)
					+ RandomStrUtil.getCharAndNumr(5)+"."+suffix;
			flag = FtpUtils.uploadFile(path, fileName, originFilePath);
		}
		if (flag) {
			return path+fileName;
		}
		return null;		
	}
	
	
	/**
	 * 图片上传
	 * @param useOriginalFileName
	 * @param fileName
	 * @param suffix
	 * @param inputStream
	 * @return
	 */
	public static String upImage(Boolean useOriginalFileName,String fileName, String suffix,InputStream inputStream){
		boolean flag = false;
		String folderName = DateUtil.format(new Date(), DateUtil.DATE_NO);
		String path = imagePath + "/" + folderName;
		if (useOriginalFileName) {
			flag = FtpUtils.uploadFile(path, fileName, inputStream);			
		}else{
			fileName = DateUtil.format(new Date(), DateUtil.DATE_NO)
					+ RandomStrUtil.getCharAndNumr(5)+"."+suffix;
			flag = FtpUtils.uploadFile(path, fileName, inputStream);
		}
		if (flag) {
			return path+fileName;
		}
		return null;		
	}
	
	
	public static void main(String[] args) {
		ImageUpDownUtil.upImage(false,"ss.png", "png", "D://3.0//ss.png");
	}
}
