package com.msa2024.boards;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.msa2024.users.UserVO;

public class BoardController {
    private BoardDAO boardDAO = new BoardDAO();

	BoardService boardService = new BoardService();

    public Object list(HttpServletRequest request,BoardVO board) throws ServletException, IOException {
        List<BoardVO> list = boardDAO.list(board);
        request.setAttribute("list", list);
        return "list";
    }

    public Object view(HttpServletRequest request, BoardVO boardid, HttpServletResponse response) throws ServletException, IOException {
        BoardVO boardidParam = boardDAO.read(boardid);
        BoardVO boardId = boardidParam;	
        request.setAttribute("board", boardId);

        return "view";
    }

    public Object delete(HttpServletRequest request, BoardVO boardVO) throws ServletException, IOException {
        int updated = boardDAO.delete(boardVO);

        if (updated == 1) {
            // 삭제가 성공했다면, 게시물 목록으로 리다이렉트
            return "redirect:list";
        } else {
            // 삭제 실패의 경우, 오류 메시지와 함께 같은 페이지에 머무르도록 처리
            Map<String, Object> map = new HashMap<>();
            map.put("status", -99);
            map.put("statusMessage", "Deletion failed");
            return map;
        }
    }


    public Object updateForm(HttpServletRequest request, BoardVO boardid) throws ServletException, IOException {
        BoardVO board = boardDAO.read(boardid);
        request.setAttribute("board", boardService.updateForm(board));
		
        return "updateForm";
    }

    public Object update(HttpServletRequest request, BoardVO board) throws ServletException, IOException {
        int updated = boardDAO.update(board);
        Map<String, Object> map = new HashMap<>();
        map.put("status", updated == 1 ? 0 : -99);
        map.put("statusMessage", updated == 1 ? "Success" : "Update failed");
        System.out.println("update");
        return map;
    }

    public Object boardForm(HttpServletRequest request) throws ServletException, IOException {
        return "boardForm";
    }

    public Object board(HttpServletRequest request, BoardVO board) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserVO loginVO = (UserVO) session.getAttribute("loginVO");
        if (loginVO != null) {
            board.setUserid(loginVO.getUserid());
        }

        int updated = boardDAO.insert(board);
        Map<String, Object> map = new HashMap<>();
        map.put("status", updated == 1 ? 0 : -99);
        map.put("statusMessage", updated == 1 ? "Success" : "Insertion failed");
        return map;
    }


}
