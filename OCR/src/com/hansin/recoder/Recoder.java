package com.hansin.recoder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.hansin.utils.Base64Util;
import com.hansin.utils.FileUtil;
import com.hansin.utils.HttpUtil;

public class Recoder {

	private static final String API = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
	private static final String API_TOKEN = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials";

	private Gson gson;
	private String appKey;
	private String secretKey;

	public Recoder(String appKey, String secretKey) {
		this.appKey = appKey;
		this.secretKey = secretKey;
		this.gson = new GsonBuilder().create();
	}

	public float startRecode(String path, String outputFilename) throws Throwable {
		float count = 0;
		String tk_json = HttpUtil.GetGeneralUrl(API_TOKEN + "&client_id=" + appKey + "&client_secret=" + secretKey);
		JsonObject json = gson.fromJson(tk_json, JsonObject.class);
		final String accessToken = (json.get("access_token").getAsString());

		File d = new File(path);
		if (!d.isDirectory())
			throw new Exception("Path: \"" + path + "\" is not a directory!");
		File files[] = d.listFiles();
		File outputfile = new File(outputFilename);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfile)));
		String params, result, imgStr;
		byte[] imgData;
		Info info;
		for (File f : files) {
			imgData = FileUtil.readFileByBytes(f.getAbsolutePath());
			imgStr = Base64Util.encode(imgData);
			// 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
			params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
					+ URLEncoder.encode(imgStr, "UTF-8");
			result = HttpUtil.post(API, accessToken, params);
			try {
				info = gson.fromJson(result, Info.class);
				PersonInfo person = info.words_result;
				writer.write(person.姓名.words + "," + person.性别.words + "," + person.民族.words + "," + person.出生.words
						+ "," + person.公民身份号码.words + "," + person.住址.words + "\n");
				count++;
				System.out.println((int) count + "/" + files.length + "\tSuccess");
			} catch (Exception e) {
				System.err.println((int) count + "/" + files.length + "\tFail");
				writer.write("error\n");

			}

		}

		writer.flush();
		writer.close();
		return count / files.length;

	}
}
