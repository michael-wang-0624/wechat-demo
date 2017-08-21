package com.github.binarywang.demo.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * 图灵机器人http
 * @author USER
 *
 */
public class HttpCilentUtil {
	public final static String key = "6e4d7641de5c4cea9841d1768a9250ac";

	public static String postUrl(String words,String userid) throws ClientProtocolException, IOException {
		//创建实例
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://www.tuling123.com/openapi/api");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("info",words));
		params.add(new BasicNameValuePair("key",key));
		params.add(new BasicNameValuePair("userid",userid));
		@SuppressWarnings("deprecation")
		HttpEntity httpEntity =  new UrlEncodedFormEntity(params,HTTP.UTF_8);
		httpPost.setEntity(httpEntity);
		//httpPost.addHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8"); 
		 httpPost.addHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded");  
		CloseableHttpResponse response = httpClient.execute(httpPost);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
		String inputLine;
		StringBuffer buffer = new  StringBuffer();
		while((inputLine=reader.readLine())!=null) {
			
			buffer.append(inputLine);
		}
		reader.close();
		httpClient.close();
		
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		 try {
			String postUrl = postUrl("明天见","123456");
			System.out.println(postUrl);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
