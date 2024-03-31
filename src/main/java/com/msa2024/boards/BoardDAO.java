package com.msa2024.boards;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    // Database connection and prepared statements declarations...
    private static Connection conn = null;
    private static PreparedStatement pstmtList = null;
    private static PreparedStatement pstmtInsert = null;
    private static PreparedStatement pstmtRead = null;
    private static PreparedStatement pstmtUpdate = null;
    private static PreparedStatement pstmtDelete = null;

    static {
        try {
            // Initialize your database connection and prepare statements...
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/miniProj-db", "bituser", "1004");
            conn.setAutoCommit(false);

            // Initialize your prepared statements based on your SQL schema
            pstmtList = conn.prepareStatement("SELECT * FROM tb_board");
            pstmtInsert = conn.prepareStatement("INSERT INTO tb_board (boardid, title, content, userid, postdate) VALUES (?, ?, ?, ?, ?)");
            pstmtRead = conn.prepareStatement("SELECT * FROM tb_board WHERE boardid = ?");
            pstmtUpdate = conn.prepareStatement("UPDATE tb_board SET title = ?, content = ?, userid = ?, postdate = ? WHERE boardid = ?");
            pstmtDelete = conn.prepareStatement("DELETE FROM tb_board WHERE boardid = ?");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BoardVO> list(BoardVO board2) {
        List<BoardVO> list = new ArrayList<>();
        try {
            ResultSet rs = pstmtList.executeQuery();
            while (rs.next()) {
                BoardVO board = BoardVO.builder()
                        .boardid(rs.getString("boardid"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .userid(rs.getString("userid"))
                        .postdate(rs.getString("postdate"))
                        .build();
                list.add(board);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insert(BoardVO board) {
        int updated = 0;
        try {
            pstmtInsert.setString(1, board.getBoardid());
            pstmtInsert.setString(2, board.getTitle());
            pstmtInsert.setString(3, board.getContent());
            pstmtInsert.setString(4, board.getUserid());
            pstmtInsert.setString(5, board.getPostdate());
            updated = pstmtInsert.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public BoardVO read(BoardVO board2) {
        BoardVO board = null;
        try {
            pstmtRead.setString(1, board2.getBoardid());
            ResultSet rs = pstmtRead.executeQuery();
            if (rs.next()) {
                board = BoardVO.builder()
                        .boardid(rs.getString("boardid"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .userid(rs.getString("userid"))
                        .postdate(rs.getString("postdate"))
                        .build();
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return board;
    }

    public int update(BoardVO board) {
        int updated = 0;
        try {
            pstmtUpdate.setString(1, board.getTitle());
            pstmtUpdate.setString(2, board.getContent());
            pstmtUpdate.setString(3, board.getUserid());
            
            // 현재 날짜와 시간을 postdate로 설정
            java.sql.Timestamp currentTime = new java.sql.Timestamp(System.currentTimeMillis());
            pstmtUpdate.setTimestamp(4, currentTime);

            pstmtUpdate.setString(5, board.getBoardid());
            updated = pstmtUpdate.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return updated;
    }


    public int delete(BoardVO boardVO) {
        int updated = 0;
        try {
            pstmtDelete.setString(1, boardVO.getBoardid());
            updated = pstmtDelete.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    // Additional methods for the DAO can be added here if necessary.
}
