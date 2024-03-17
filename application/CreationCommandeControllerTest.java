package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreationCommandeControllerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testIdClient() {
		CreationCommandeController controller=new CreationCommandeController();
		
		assertEquals(1, controller.idClient("Gojo", "Satoru"));
	}
	
	@Test
	void testIdProduit() {
		CreationCommandeController controller=new CreationCommandeController();
		
		assertEquals(1, controller.idProduit("alu"));
	}

}
