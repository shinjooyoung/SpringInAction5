package tacos;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;  // MockMvc 주입
	
	@Test
	public void testHomePage() throws Exception{
		mockMvc.perform(get("/"))	// GET /를 수행
		.andExpect(status().isOk()) // httpp 200이 되어야 함
		.andExpect(view().name("home"))	// home 뷰가 존재해야함
		.andExpect(content().string(	// 콘텐츠에 실제로 Welcom to..가 포함되어야 함
			containsString("Welcome to...")));
		
	}

}
