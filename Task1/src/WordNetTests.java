import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WordNetTests {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private WordNet wn;
    File file;
    @Before
    public void setUp() {
     wn = new WordNet("synset.txt",null);
    }

    @Test
    public void readsFromCSVDataAndInsertIdAndWordIntoMap() throws IOException {
        prepareSyntexData();
        wn.processSynsets(file.getAbsolutePath());
        Map<String, Integer> words  = wn.getSynset();
        assertThat(words.get("Java")).isEqualTo(72);
    }

    private void prepareSyntexData() throws IOException {
        file = tempFolder.newFile("synset.txt");
        BufferedWriter  bw = new BufferedWriter(new FileWriter(file));
        bw.write("36,AND_circuit AND_gate,a circuit in a computer that fires only when all of its inputs fire \r ");
        bw.write("72,Java         JMX,a circuit in a computer that fires only when all of its inputs fire");
        bw.flush();
        bw.close();
    }


}
