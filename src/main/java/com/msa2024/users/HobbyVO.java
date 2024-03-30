package com.msa2024.users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HobbyVO {
	
	 private int hobbyid;
	 private String hobbyname;
	 private String checked;
}
