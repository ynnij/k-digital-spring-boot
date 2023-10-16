package abc.def;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ABCController {
	public ABCController() {
		System.out.println("ABCController 생성자 호출"); // 패키지가 다른 외부 컴포넌트의 컨트롤러는 호출하지 않음
	}
}
