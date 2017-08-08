import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@RunWith(JUnit4.class)
public class BaseTest {
    protected final static String IN_DIRRECTORY = "src/main/resources/inbox/";
    protected final static String OUT_DIRRECTORY = "src/main/resources/outbox/";
    protected final static String FROM_DIRRECTORY = "src/test/resources/testfiles/";
    protected final static String EXPECTED_DIRRECTORY = "src/test/resources/testfiles/expected/";

    /**
     * Получить JSON из файла
     * @param filename
     * @return
     * @throws JSONException
     * @throws IOException
     */
    private static JSONObject parseJSON(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

    /**
     * Переместить файл в папку IN
     * @param fileName
     */
    public void moveFile(String fileName) {
        File sourceFile = new File(FROM_DIRRECTORY + fileName);
        File destinationFile = new File(IN_DIRRECTORY + fileName);
        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получить ожидаемы результат в формате JSON
     * @param fileName
     * @return
     */
    public JSONObject getExpectedResult(String fileName) {
        try {
            return parseJSON(EXPECTED_DIRRECTORY + fileName);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    /**
     * Получить фактический результат в формате JSON
     * @param fileName
     * @return
     */
    public JSONObject getActualResult(String fileName) throws Exception {
        try {
            String path = OUT_DIRRECTORY + fileName;
            synchronized (this) {
                while (!Files.exists(Paths.get(path))) {this.wait(10);}
            }
            return parseJSON(path);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }
}
