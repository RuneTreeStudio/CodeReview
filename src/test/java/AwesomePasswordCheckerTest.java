import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AwesomePasswordCheckerTest {

    private AwesomePasswordChecker checker;

    @BeforeEach
    public void setUp() throws IOException {
        checker = AwesomePasswordChecker.getInstance();
    }

    @Test
    public void getInstance() throws IOException {
        AwesomePasswordChecker instance1 = AwesomePasswordChecker.getInstance();
        assertNotNull(instance1);
    }

    @Test
    public void testGetInstance() throws IOException {
        AwesomePasswordChecker instance1 = AwesomePasswordChecker.getInstance();
        AwesomePasswordChecker instance2 = AwesomePasswordChecker.getInstance();
        assertSame(instance1, instance2, "Les deux instances doivent être identiques (Singleton)");
    }

    @Test
    public void maskAff() {
        // Test avec des caractères fréquents (code 1)
        int[] mask = checker.maskAff("eat");
        assertEquals(1, mask[0]); // 'e'
        assertEquals(1, mask[1]); // 'a'
        assertEquals(1, mask[2]); // 't'

        // Test avec des majuscules fréquentes (code 3)
        mask = checker.maskAff("EAT");
        assertEquals(3, mask[0]); // 'E'
        assertEquals(3, mask[1]); // 'A'
        assertEquals(3, mask[2]); // 'T'

        // Test avec des symboles spéciaux (code 6)
        mask = checker.maskAff("!@?");
        assertEquals(6, mask[0]); // '!'
        assertEquals(6, mask[1]); // '@'
        assertEquals(6, mask[2]); // '?'

        // Test avec des chiffres (code 5)
        mask = checker.maskAff("123");
        assertEquals(5, mask[0]); // '1'
        assertEquals(5, mask[1]); // '2'
        assertEquals(5, mask[2]); // '3'

        // Test avec limite de 28 caractères
        String longPassword = "a".repeat(30);
        mask = checker.maskAff(longPassword);
        assertEquals(28, mask.length);
    }

    @Test
    public void getDistance() {
        // Test avec un mot de passe simple
        double distance1 = checker.getDistance("password");
        assertTrue(distance1 >= 0, "La distance doit être positive");

        // Test avec un mot de passe complexe
        double distance2 = checker.getDistance("P@ssw0rd!123");
        assertTrue(distance2 >= 0, "La distance doit être positive");

        // Test avec une chaîne vide
        double distance3 = checker.getDistance("");
        assertTrue(distance3 >= 0, "La distance doit être positive");
    }

    @Test
    public void computeMd5() {
        // Test avec une chaîne connue
        String md5 = AwesomePasswordChecker.computeMd5("hello");
        assertEquals("5d41402abc4b2a76b9719d911017c592", md5);

        // Test avec une chaîne vide
        md5 = AwesomePasswordChecker.computeMd5("");
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", md5);

        // Test avec un mot de passe
        md5 = AwesomePasswordChecker.computeMd5("password");
        assertEquals("5f4dcc3b5aa765d61d8327deb882cf99", md5);

        // Vérifier que le résultat est toujours en minuscules et 32 caractères
        md5 = AwesomePasswordChecker.computeMd5("Test123");
        assertEquals(32, md5.length());
        assertEquals(md5.toLowerCase(), md5);
    }
}
