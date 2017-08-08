import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;


public class HundredAndOneCorrectClientTest extends BaseTest{
    private static final String JSON = "10102.json";

    /**
     * Проверка обработки файла со 101 клиентами
     */
    @Test(timeout = 5000)
    public void oneCorrectClientTest() throws Exception {
        moveFile(JSON);
        JSONAssert.assertEquals(getExpectedResult(JSON), getActualResult(JSON), false);
    }
}

