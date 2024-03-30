package com.msa2024.users;

import java.io.IOException;
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
public class UserService  {
	private static final long serialVersionUID = 1L;
       
	UserDAO usersDAO = new UserDAO();
	
    public UserService() {
        super();
        // TODO Auto-generated constructor stub
    }

    public List<UserVO> list(UserVO user) throws ServletException, IOException {
		return usersDAO.list(user);
	}

    public UserVO read(String userVO) throws ServletException, IOException {
		return usersDAO.read(userVO);
	}
    public int insertUserWithHobbies(UserVO user) throws Exception {
		return usersDAO.insertUserWithHobbies(user);
	}
    public int delete(UserVO user) throws ServletException, IOException {
		return usersDAO.delete(user);
	}
    public int update(UserVO user) throws ServletException, IOException {
		return usersDAO.update(user);
	}
    
    public Map<Integer, String> getHobbies() {
        // UserDAO를 통해 취미 목록을 가져오는 로직을 구현
        return usersDAO.getHobbies(); // 취미 목록을 가져오는 DAO 메서드 호출
    }

	public UserVO loginRead(String loginVO) {
		return usersDAO.read(loginVO);
	}

	
}
