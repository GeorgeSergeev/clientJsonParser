import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;


public class HundredCorrectClientTest extends BaseTest{
    private static final String JSON = "10002.json";

    /**
     * Проверка обратоки фала с 100 клиенами
     */
    @Test(timeout = 5000)
    public void oneCorrectClientTest() throws Exception {
        moveFile(JSON);
        JSONAssert.assertEquals(getExpectedResult(JSON), getActualResult(JSON), false);
    }
}

