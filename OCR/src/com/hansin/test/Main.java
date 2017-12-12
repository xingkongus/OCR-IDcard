package com.hansin.test;

import com.hansin.recoder.Recoder;

public class Main {

	public static void main(String[] args) throws Throwable {

		String appKey = "wo0YGS7fd8Ogx1Vt2SsUQcSd";//°Ù¶ÈAPI Key
		String secretKey = "N1lsxFG5PuGLGH1iUthPistToGPojTeo";//SercetKey
		
		Recoder recoder = new Recoder(appKey,secretKey );
		
		float persent = recoder.startRecode("d:/files/idcard/", "d:/a.csv");
		System.out.println("done. " +  persent * 100 + "%");
	}
}
