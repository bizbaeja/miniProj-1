package com.msa2024.users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {	
   private String userid;
   private String name;
   private String email;
   private String password;
   private String birth;
   private String gender;
   private String register;
   private String action;
   private String hobbies;
   private int hobbyid;
   private String hobbyname;

public UserVO(String userid, String name, String email, String password, String birth, String gender, String register, int hobbyid) {
	super();
	this.userid = userid;
	this.name = name;
	this.email = email;
	this.password = password;
	this.birth = birth;
	this.gender = gender;
	this.register = register;
	this.hobbyid = hobbyid;
//	this.hobbyname = hobbyname;

}
public UserVO(String userid, String name, String email, String password, String birth, String gender, String register	) {
	super();
	this.userid = userid;
	this.name = name;
	this.email = email;
	this.password = password;
	this.birth = birth;
	this.gender = gender;
	this.register = register;

}
   
   

public UserVO(int hobbyid, String hobbyname) {
	super();
	this.hobbyid = hobbyid;
	this.hobbyname = hobbyname;
}
}
