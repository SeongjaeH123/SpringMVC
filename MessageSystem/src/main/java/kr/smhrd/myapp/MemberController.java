package kr.smhrd.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.smhrd.entity.Member;
import kr.smhrd.mapper.MemberMapper;

// 사용자(Member)와 관련된 요청을 처리할 수 있는 Controller
// Spring Conatainer에 Controller객체를 생성할 수 있도록 어노테이션을 사용해서 알려줘야 한다.
@Controller
public class MemberController {
	
	// MemberMapper 인터페이스를 만들었다면 객체를 생성해야 한다.
	@Autowired
	MemberMapper mapper;

	//유저와 관련된 요청을 처리할 수 있는 메서드들 생성
	
	//루트경로로 들어왔을 때 처리하는 메서드
	@RequestMapping("/")
	public String Main() {
		//Main 페이지를 보여줄 수 있도록 view의 이름을 반환하는 메서드
		return "Main";
	}
	
	//POJO에서 request, response객체는 사용 불가능
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String Join(Member member, Model model) {
		System.out.println(member.toString());
		
		//DB에 접근할 수 있도록 mybatis에게 명령하기 - Mapper 인터페이스 활용
		//위에서 생성한 mapper객체를 이용해서 DB와 연동하는 memberJoin메서드 실행
		int result = mapper.memberJoin(member);
		
		if(result>0) {
			//scope 영역을 이용해서 데이터 전달 가능 but, jsp나 servlet이 아닌데 내장 Scope어케쓰나?
			//매개변수 형태로 Dispatcher Servlet에게 전달 받아서 사용할 수 있다.
			//		Request scope 안에 있는 Model이라는 객체를 이용할 수도 있다. (session scope가 아니라 request scope!)
			model.addAttribute("user_email", member.getEmail());
			
			// Q. 요청이 일어나면 Request객체는 Controller안에서만 유효하다.
			//    그런데 model객체를 컨트롤러를 벗어났는데 햇는데 어케 쓰는걸까??
			// A. 포워드 방식을 이용해서
			
			return "JoinSuccess";
		} else {
			return "Main";
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String Login(Member member, Model model) {
		System.out.println("login기능 | "+member.toString());
		Member loginMember = mapper.memberLogin(member);
		if(loginMember!=null) {
			System.out.println("login한 Member | "+loginMember);
			model.addAttribute("user_email", member.getEmail());
		} 
		return "Main";
	}
}
