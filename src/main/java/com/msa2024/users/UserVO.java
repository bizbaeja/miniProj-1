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
    private String passwordConfirm;
    private String birth;
    private String gender;
    private String register;
    private List<String> hobbies; // 사용자의 취미 ID 목록
    private List<HobbyVO> hobbyList; // 사용자의 취미 ID 목록
    private String hobbyid; // 또는 적절한 데이터 타입

    // getter와 setter도 추가
    public String getHobbyid() {
        return hobbyid;
    }

    public void setHobbyid(String hobbyid) {
        this.hobbyid = hobbyid;
    }
    // 모든 필드를 포함하는 생성자
    public UserVO(String userid, String name, String email, String password, String birth, String gender, String register, List<String> hobbies) {
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