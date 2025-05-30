package kr.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Member {
	/// 엔티티는 테이블의 컬럼 정보를 가지고 있는 클래스로 VO와 유사
	
	@NonNull
	String email;
	@NonNull
	String pw;
	String tel;
	String address;
}
