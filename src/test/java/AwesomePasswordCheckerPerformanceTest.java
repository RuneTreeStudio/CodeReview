import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class AwesomePasswordCheckerPerformanceTest {

    @Test
    public void testGetDistancePerformance() throws IOException {
        AwesomePasswordChecker checker = AwesomePasswordChecker.getInstance();

        long startTime = System.currentTimeMillis();

        // Test 1000 appels
        for (int i = 0; i < 1000; i++) {
            checker.getDistance("TestPassword" + i);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Doit prendre moins de 5 secondes pour 1000 appels
        assertTrue("Performance test failed: took " + duration + "ms", duration < 5000);
    }

    @Test
    public void testMd5ComputePerformance() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            AwesomePasswordChecker.computeMd5("password" + i);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Doit prendre moins de 3 secondes pour 10000 appels
        assertTrue("Performance test failed: took " + duration + "ms", duration < 3000);
    }
}
