package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SampleControllerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCheckUserRole() {
		SampleController controller=new SampleController();
		
		assertEquals("admin", controller.checkUserRole("eren@gmail.com", "eren"));
		assertEquals("comptable", controller.checkUserRole("jules@gmail.com", "jules"));
	}

}
