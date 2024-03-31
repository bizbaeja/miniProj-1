package com.msa2024.boards;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * MVC 
 * Model : B/L 로직을 구현하는 부분(service + dao)  
 * View  : 출력(jsp) 
 * Controller : model와 view에 대한 제어를 담당 
 */
public class BoardService  {
	private static final long serialVersionUID = 1L;
      
	static BoardDAO boardDAO = new BoardDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardService() {
        super();
        // TODO Auto-generated constructor stub
    }

    public List<BoardVO> list(BoardVO board) throws ServletException, IOException {
		return boardDAO.list(board);
	}
	
	public static BoardVO view(BoardVO board) throws ServletException, IOException {
		return boardDAO.read(board);
	}
	
	public int delete(BoardVO board) throws ServletException, IOException {
		return boardDAO.delete(board);
	}
	
	public BoardVO updateForm(BoardVO board) throws ServletException, IOException {
		return boardDAO.read(board);
	}
	
	public int update(BoardVO board) throws ServletException, IOException {
		return boardDAO.update(board);
	}
	
	public int board(BoardVO board) throws ServletException, IOException {
		return boardDAO.insert(board);
	}
	
}










