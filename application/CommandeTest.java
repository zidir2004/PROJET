package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandeTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetType() {
		Commande controller=new Commande();
		controller.setIdProduit(5);
		//On s'attends à avoir "pvc pvdc" comme résultat
		assertEquals("PVC PVDC", controller.getType());
	}

	@Test
	void testGetPrixProduit() {
		Commande controller=new Commande();
		controller.setIdProduit(5);
		//On s'attends à avoir 60 comme résultat
		assertEquals(60, controller.getPrixProduit());
	}

}
