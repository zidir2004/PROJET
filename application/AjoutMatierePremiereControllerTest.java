package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AjoutMatierePremiereControllerTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        AjoutMatierePremiereController controller = new AjoutMatierePremiereController();
        
        // Appelle la méthode pour charger les noms de matières premières depuis la base de données
        List<String> actualList = controller.chargerNomsMatieresDepuisDB();

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
