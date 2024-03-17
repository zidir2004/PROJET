package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AjoutProduitFormControllerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		AjoutProduitFormController controller=new AjoutProduitFormController();
		
		// Appelle la méthode pour charger les noms de matières premières depuis la base de données
        List<String> actualList = controller.chargerTypesProduitsDepuisDB();

        // Créer une liste attendue
        List<String> expectedList = new ArrayList<>();
        expectedList.add("alu");
        expectedList.add("pvc");
        expectedList.add("pvdc");
        expectedList.add("PVC/PE");
        expectedList.add("PVC PVDC");
        expectedList.add("OPA");

        // Vérifiez si les listes sont égales
        assertEquals(expectedList, actualList);
	}


}
