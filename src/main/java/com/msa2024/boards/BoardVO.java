package com.msa2024.boards;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
	private String boardid;
	private String title;
	private String content;
	private String userid;
	private String postdate;
	
	private String action;

	//검색키
	private String searchKey;
	public BoardVO(String boardid, String title, String content) {
		this(boardid, title, content, "", "");
	}

	
	public BoardVO(String boardid, String title, String content, String userid, String postdate) {
		this(boardid, title, content, userid, postdate, "", "");
	}
	
	
}
