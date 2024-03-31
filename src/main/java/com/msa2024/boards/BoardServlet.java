package com.msa2024.boards;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa2024.boards.BoardController;
import com.msa2024.boards.BoardVO;

public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BoardController boardController = new BoardController();

    public BoardServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    private Map<String, Object> convertMap(Map<String, String[]> map) {
        Map<String, Object> result = new HashMap<>();
        for (var entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue().length == 1 ? entry.getValue()[0] : entry.getValue());
        }
        return result;
    }

    private void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String contentType = request.getContentType();

        ObjectMapper objectMapper = new ObjectMapper();
        BoardVO boardVO = null;

        if (contentType == null || contentType.startsWith("application/x-www-form-urlencoded")) {
            boardVO = objectMapper.convertValue(convertMap(request.getParameterMap()), BoardVO.class);
        } else if (contentType.startsWith("application/json")) {
            boardVO = objectMapper.readValue(request.getInputStream(), BoardVO.class);
        }

        String action = boardVO.getAction();
        Object result = switch (action) {
            case "list" -> boardController.list(request, boardVO);
            case "view" -> boardController.view(request, boardVO, response);
            case "delete" -> boardController.delete(request, boardVO);
            case "updateForm" -> boardController.updateForm(request, boardVO);
            case "update" -> boardController.update(request, boardVO);
            case "boardForm" -> boardController.boardForm(request);
            case "board" -> boardController.board(request, boardVO);
            default -> "";
        };

        if (result instanceof Map<?, ?> map) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().append(objectMapper.writeValueAsString(map));
        } else if (result instanceof String url) {
            if (url.startsWith("redirect:")) {
                response.sendRedirect(url.substring("redirect:".length()));
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/boards/" + url + ".jsp");
                rd.forward(request, response);
            }
        }
    }
}
