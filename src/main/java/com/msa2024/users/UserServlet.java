package com.msa2024.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class UserServlet
 */

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserController userController = new UserController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doService(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request, response);
	}

	private Map<String, Object> convertMap(Map<String, String[]> map) {
		Map<String, Object> result = new HashMap();

		for (var entry : map.entrySet()) {
			if (entry.getValue().length == 1) {
				// 문자열 1건
				result.put(entry.getKey(), entry.getValue()[0]);
			} else {
				// 문자열 배열을 추가한다
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;

	}

	private void doService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String contentType = request.getContentType();

		ObjectMapper objectMapper = new ObjectMapper();

		UserVO userVO = null;
		
		// request로 받은 데이터를 UserVO의 타입으로 변환하기 위함 
		if (contentType == null || contentType.startsWith("application/x-www-form-urlencoded")) {
			userVO = objectMapper.convertValue(convertMap(request.getParameterMap()), UserVO.class);
		} else if (contentType.startsWith("application/json")) {
			userVO = objectMapper.readValue(request.getInputStream(), UserVO.class);
				
		} 
//		String loginData = null;
//		// request로 받은 데이터를 문자열로 변환하기 위함
//		if (contentType == null || contentType.startsWith("application/x-www-form-urlencoded")) {
//		    loginData = objectMapper.writeValueAsString(convertMap(request.getParameterMap()));
//		} else if (contentType.startsWith("application/json")) {
//		    StringBuilder stringBuilder = new StringBuilder();
//		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//		    String line;
//		    while ((line = bufferedReader.readLine()) != null) {
//		        stringBuilder.append(line);
//		    }
//		    loginData = stringBuilder.toString();
//		}

		
//		System.out.println("action =:" + userVO.getAction());

		String action = userVO.getAction();
		System.out.println("action= " + action);
		Object result = switch (action) {
		case "list" -> userController.list(request, userVO);
		case "read" -> userController.read(request);
		case "delete" -> userController.delete(request, userVO);
		case "update" -> userController.update(request, userVO);
		case "getHobbies" -> userController.getHobbies(request, userVO);
		case "loginForm" -> userController.loginForm(request, userVO);
		case "login" -> userController.login(request, userVO);
		case "signupForm" -> userController.signupForm(request, userVO);
		case "hobbies" -> userController.getHobbies(request, userVO);
		case "signup" -> userController.signup(request, userVO);
		case "about" -> userController.about(request);
		default -> "";
		};

		if (result instanceof Map map) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(objectMapper.writeValueAsString(map));
		} else if (result instanceof String url) {
			if (url.startsWith("redirect:")) {
				response.sendRedirect(url.substring("redirect:".length()));
			} else {
				// 3. jsp 포워딩
				// 포워딩
				
//				System.out.println("jsp forwading to url: " + url);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/users/" + url + ".jsp");
				rd.forward(request, response);
			}
		}

	}
}
