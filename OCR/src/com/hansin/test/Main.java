package com.hansin.test;

import com.hansin.recoder.Recoder;

public class Main {

	public static void main(String[] args) throws Throwable {

		String appKey = "wo0YGS7fd8Ogx1Vt2SsUQcSd";				//�ٶ�API Key
		String secretKey = "N1lsxFG5PuGLGH1iUthPistToGPojTeo";	//SercetKey
		
		String path = "d:/files/idcard/";						//���֤��Ƭ�ļ���
		
		String csvFile = "d:/a.csv";							//����csv�ļ�����λ��
		
		
		Recoder recoder = new Recoder(appKey,secretKey );
		
		float persent = recoder.startRecode(path, csvFile);		//��ʼ����
		
		System.out.println("done. " +  persent * 100 + "%");
	}
}
