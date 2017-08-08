import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;


public class OneClientWithErrorTest extends BaseTest{
    private static final String ONE_CLIENT_WITH_ERROR = "11.json";

    /**
     * Проверка обратоки файла с клиентом содержащим ошибки
     */
    @Test(timeout = 5000)
    public void oneCorrectClientTest() throws IOException, JSONException {
        moveFile(ONE_CLIENT_WITH_ERROR);
        assertFalse("Ошибочный файл не должен быть обработан.", new File(OUT_DIRRECTORY + ONE_CLIENT_WITH_ERROR).exists());
    }
}

