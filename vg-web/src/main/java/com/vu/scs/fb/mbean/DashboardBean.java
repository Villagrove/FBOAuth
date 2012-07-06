package com.vu.scs.fb.mbean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vu.scs.fb.util.FbrConstants;

@ManagedBean
@RequestScoped
public class DashboardBean implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(DashboardBean.class);

	private String accessToken;

	

	private static final long serialVersionUID = 1L;

	private String code;

	private String state;

	public String getCode() {
		return code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@PostConstruct
	public void init() {
		
		logger.debug("entering init..");

		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String error = req.getParameter("error_reason");
		if (error != null) {
			try {
				// you may want to pass this error to
				// the error redirect url
				String error_desc = req.getParameter("error_description");
				((HttpServletResponse) FacesContext.getCurrentInstance()
						.getExternalContext().getResponse())
						.sendRedirect("http://ERROR_URI.xhtml");
			} catch (Exception e) {
			}
		}

		String code = req.getParameter("code");
		
		logger.debug("code received: " + code);
		
		if (code != null) {
			int ret = retrieveToken(code);
			this.code = code;
			// process return value
		} else {
			// Redirect or tell the user about the error
		}
	}

	private int retrieveToken(String code) {
		logger.debug("trying to retrieve token with the code: " + code);
		
		String redirect_uri = "http://localhost:8080/vg-web/dashboard.jsf";
		
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(
				"https://graph.facebook.com/oauth/access_token");
		/*
		https://graph.facebook.com/oauth/access_token?
		    client_id=YOUR_APP_ID
		   &redirect_uri=YOUR_REDIRECT_URI
		   &client_secret=YOUR_APP_SECRET
		   &code=CODE_GENERATED_BY_FACEBOOK
*/
		try {

			String[][] parameters = {
					{ "client_id", FbrConstants.CLIENT_APP_ID },
					{ "client_secret", FbrConstants.APP_SECRET },
					{ "redirect_uri", redirect_uri }, 
					{ "code", code } };

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);

			for (int i = 0; i < parameters.length; i++) {
				nameValuePairs.add(new BasicNameValuePair(parameters[i][0],
						parameters[i][1]));
			}

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = client.execute(post);
			
			logger.debug("resp received: " + resp);
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(resp
					.getEntity().getContent()));

			String message = "";
			String lineData;
			while ((lineData = rd.readLine()) != null) {
				message += lineData;
			}

			
			logger.debug("message received: " + message);
			
			
			String token = null;

			// Add more safety traps
			String[] params = message.split("&");
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					if (params[i].contains("access_token")) {
						String[] B = params[i].split("=");
						if (B != null) {
							token = B[1];
						}
						break;
					}
				}
			} else {
				// Let the user know about the error.
				return 0;
			}

			logger.debug("token received: " + token);
			
			accessToken = token;

			return 1;

		} catch (Exception e) {
			return 0;
		}

	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}