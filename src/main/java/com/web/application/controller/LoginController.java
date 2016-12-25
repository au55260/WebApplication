package com.web.application.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

	/**
	 * Handles requests for the application login page.
	 */
	@Controller
	public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginRquest(Model model) {
		model.addAttribute("loginError", "Welcome to the login page!!");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String verifyLogin(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model) {
		System.out.println("Login method : " + username + "Password:   " + password);
		model.addAttribute("loginError", "login Succcessfull with " + username + "Password:   " + password);
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			String url = "http://localhost:8081/webapi/api/member/login/"+username+"/"+password ;
			HttpPost postRequest = new HttpPost(
					url);
			HttpResponse response = httpClient.execute(postRequest);
			
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			System.out.println(entity.getContent());
			
			byte[] buffer = new byte[1024];
		      if (entity != null) {
		        InputStream inputStream = entity.getContent();
		        try {
		          int bytesRead = 0;
		          BufferedInputStream bis = new BufferedInputStream(inputStream);
		          while ((bytesRead = bis.read(buffer)) != -1) {
		            String chunk = new String(buffer, 0, bytesRead);
		            System.out.println(chunk);
		          }
		        } catch (Exception e) {
		          e.printStackTrace();
		        } finally {
		          try { inputStream.close(); } catch (Exception ignore) {}
		        }
		      }
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		return "login";
	}

}
