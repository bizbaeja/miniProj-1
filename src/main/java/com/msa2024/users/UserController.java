package com.msa2024.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;/**
 * Servlet implementation class UserController
 */
public class UserController  {

	UserService userService = new UserService();

    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Object getHobbies(HttpServletRequest request, UserVO user) {
        Map<Integer, String> hobbiesList = userService.getHobbies();
        request.setAttribute("hobbiesList", hobbiesList);
        return "hobby"; // 취미 목록을 보여줄 JSP 페이지 이름
    }

    public Object list(HttpServletRequest request, UserVO user) throws ServletException, IOException {
        System.out.println("목록");
 
        // 사용자 목록 조회
        List<UserVO> usersList = userService.list(user);
        
        // 취미 목록 조회
        

        // JSP 페이지에 사용자 목록과 취미 목록 전달
        request.setAttribute("usersList", usersList);
//        request.setAttribute("hobbiesList", hobbiesList);

        return "list"; // JSP 페이지 이름
    }
    public Object delete(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("삭제");
		//1. 처리
		int updated = userService.delete(user);
		
//		//2. jsp출력할 값 설정
//		request.setAttribute("updated", updated);
//		
//		return "delete";
		Map<String, Object> map = new HashMap<>();
		if (updated == 1) { //성공
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "회원 정보 삭제 실패하였습니다");
		}
		
		return map;
	}
    public Object read(HttpServletRequest request) throws ServletException, IOException {
		String useridParam = request.getParameter("userid");
		String userid = useridParam;	

		//1. 처리
		UserVO userVO = userService.read(userid);
		Map<Integer, String> hobbyVO = userService.getHobbies();

		// 조회된 사용자 정보를 request 객체에 추가
		request.setAttribute("user", userVO);
		request.setAttribute("hobbies", hobbyVO);
		return "read";
	}
    public Object update(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("수정");
		System.out.println(user.getName());	//1. 처리
		
		//바꾸려는 원본의 데이터 조회
//		int userid = Integer.parseInt(user.getUserid());	
	    String userid = user.getUserid();	
		UserVO updateUserVO = userService.read(userid);
	
		updateUserVO.setName(user.getName());
		updateUserVO.setEmail(user.getEmail());
		updateUserVO.setBirth(user.getBirth());
		updateUserVO.setGender(user.getGender());
		updateUserVO.setRegister(user.getRegister());
		updateUserVO.setName(user.getName());
		updateUserVO.setHobbyid(user.getHobbyid());
		
		int updated = userService.update(user);
		

//		//2. jsp출력할 값 설정
//		request.setAttribute("updated", updated);
//		
//		return "update";
		Map<String, Object> map = new HashMap<>();
		if (updated == 1) { //성공
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "회원 정보 수정 실패하였습니다");
		}
		
		return map;
	}

}
