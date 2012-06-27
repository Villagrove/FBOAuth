package com.vu.scs.fb.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicProfileService {
	
	private static Logger logger = LoggerFactory.getLogger(BasicProfileService.class);
	
	void e (){
		try {
			String url = "http://facebook.com/some/api";
			String charset = "UTF-8";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("param1", "value"));
			params.add(new BasicNameValuePair("param2", "value2"));
			UrlEncodedFormEntity query = new UrlEncodedFormEntity(params, charset);

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			post.setEntity(query);
			InputStream response = client.execute(post).getEntity().getContent();
			
			
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException received: ", e);
		} catch (IllegalStateException e) {
			logger.error("IllegalStateException received: ", e);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException received: ", e);
		} catch (IOException e) {
			logger.error("IOException received: ", e);
		}
		// Now do your thing with the facebook response.
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
