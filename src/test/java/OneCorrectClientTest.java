import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;


public class OneCorrectClientTest extends BaseTest{
    private static final String ONE_CLIENT = "1.json";

    /**
     * Проверка обратоки файла с одним клиентом
     */
    @Test(timeout = 5000)
    public void oneCorrectClientTest() throws Exception {
        moveFile(ONE_CLIENT);
        JSONAssert.assertEquals(getExpectedResult(ONE_CLIENT), getActualResult(ONE_CLIENT), false);
    }
}

