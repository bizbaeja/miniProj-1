package com.msa2024.users;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO implements Serializable {
	private String action;
    private String userid;
    private String name;
    private String email;
    private String password;
    private String birth;
    private String gender;
    private String register;
    private List<Integer> hobbies; // 사용자의 취미 ID 목록

    // 모든 필드를 포함하는 생성자
    public UserVO(String userid, String name, String email, String password, String birth, String gender, String register, List<Integer> hobbies) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.register = register;
        this.hobbies = hobbies;
    }

    // 취미 정보를 제외한 기본 정보를 포함하는 생성자
    public UserVO(String userid, String name, String email, String password, String birth, String gender, String register) {
        this(userid, name, email, password, birth, gender, register, null);
    }
}