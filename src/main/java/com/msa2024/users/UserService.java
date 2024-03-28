package com.msa2024.users;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
public class UserService  {
       
	UserDAO usersDAO = new UserDAO();
	
    public UserService() {
        super();
        // TODO Auto-generated constructor stub
    }

    public List<UserVO> list(UserVO user) throws ServletException, IOException {
		return usersDAO.list(user);
	}

    public UserVO read(String userId) throws ServletException, IOException {
		return usersDAO.read(userId);
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

}
