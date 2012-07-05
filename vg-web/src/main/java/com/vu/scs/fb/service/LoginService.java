package com.vu.scs.fb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vu.scs.fb.util.FbrConstants;

@Service
public class LoginService {

	private static Logger logger = LoggerFactory.getLogger(LoginService.class);

	public void loginToFacebook() {

		HttpClient httpClient = new DefaultHttpClient();
		getAccessCode(httpClient);

	}

	public void getAccessCode(HttpClient httpClient) {
		logger.debug("trying to get access code from facebook");

		String accessCodeFbUrl = "https://www.facebook.com/dialog/oauth?client_id="
				+ FbrConstants.CLIENT_APP_ID
				+ "&redirect_uri="
				+ "http://localhost:8080/vg-web/facebookCode.jsf&state="
				+ FbrConstants.STATE;

		HttpPost httpPost = new HttpPost(accessCodeFbUrl);

		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		try {

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			HttpParams fbCodeParams = response.getParams();

			String code = "";
			if (fbCodeParams.getParameter("Code") != null) {
				code = fbCodeParams.getParameter("Code").toString();
			}
			logger.debug("code received from facebook: " + code);

			logger.debug("response : " + response);

			logger.debug("Content: " + entity.getContent());

		} catch (IOException e) {
			logger.error("IOException received: " + e);
		} catch (Exception e) {
			logger.error("Exception received: " + e);
		} finally {
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error(
							"IOException received while closing bufferedReader: ",
							e);
				}

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
