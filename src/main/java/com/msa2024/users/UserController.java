package com.msa2024.users;

import javax.servlet.ServletException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
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
        return "hobbies"; // 취미 목록을 보여줄 JSP 페이지 이름
    }
    public Object signupForm(HttpServletRequest request, UserVO user) {
        Map<Integer, String> hobbiesList = userService.getHobbies();
        request.setAttribute("hobbiesList", hobbiesList);
        return "signupForm"; // 취미 목록을 보여줄 JSP 페이지 이름
    }
    public Object signup(HttpServletRequest request, UserVO user) throws ServletException, IOException {
        // 취미 목록 가져오기
        Map<Integer, String> hobbiesList = userService.getHobbies();
        request.setAttribute("hobbiesList", hobbiesList);

        String requestData = null;

        // Async를 지원하지 않거나 이미 시작된 경우에만 요청 본문을 읽음
        if (!request.isAsyncStarted() && request.isAsyncSupported()) {
            requestData = request.getReader().lines().collect(Collectors.joining());
        }

        if (requestData != null) {
            ObjectMapper mapper = new ObjectMapper();

            // JSON 문자열에서 UserVO 객체와 취미 ID 리스트 추출
            JsonNode rootNode = mapper.readTree(requestData);
            JsonNode hobbiesNode = rootNode.path("hobbies");
            List<Integer> hobbyIds = null;
            if (!hobbiesNode.isMissingNode()) { // 취미 데이터가 있다면 파싱
                hobbyIds = mapper.convertValue(hobbiesNode, new TypeReference<List<Integer>>(){});
            }

            if (hobbyIds != null) {
                user.setHobbies(hobbyIds);
            }
        }

        // UserService를 통해 사용자를 등록
        UserVO userVO = userService.signup(user);
        request.setAttribute("user", userVO);
        request.setAttribute("hobbies", hobbiesList);

        return "signup"; // 반환할 뷰 이름
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
    public Object update(HttpServletRequest request,UserVO updateUserVO) throws ServletException, IOException {
        System.out.println("수정: " + updateUserVO.getName()); // 1. 처리 로깅

        // 사용자 ID로 원본 데이터 조회
//        String userid = user.getUserid(); 
//        UserVO updateUserVO = userService.read(userid);
//        
//        if (updateUserVO == null) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("status", -99);
//            map.put("statusMessage", "회원 정보를 찾을 수 없습니다.");
//            return map;
//        }
//
//        // 변경된 정보로 업데이트
//        updateUserVO.setName(user.getName());
//        updateUserVO.setEmail(user.getEmail());
//        updateUserVO.setBirth(user.getBirth());
//        updateUserVO.setGender(user.getGender());
//        updateUserVO.setRegister(user.getRegister());
//        updateUserVO.setHobbyid(user.getHobbyid());
        int updated = userService.update(updateUserVO);
        // userService를 통해 데이터베이스 업데이트 시도
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
