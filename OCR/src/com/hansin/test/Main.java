package com.hansin.test;

import com.hansin.recoder.Recoder;

public class Main {

	public static void main(String[] args) throws Throwable {

		String appKey = "wo0YGS7fd8Ogx1Vt2SsUQcSd";				//百度API Key
		String secretKey = "N1lsxFG5PuGLGH1iUthPistToGPojTeo";	//SercetKey
		
		String path = "d:/files/idcard/";						//身份证照片文件夹
		
		String csvFile = "d:/a.csv";							//导出csv文件保存位置
		
		
		Recoder recoder = new Recoder(appKey,secretKey );
		
		float persent = recoder.startRecode(path, csvFile);		//开始分析
		
		System.out.println("done. " +  persent * 100 + "%");
	}
}
