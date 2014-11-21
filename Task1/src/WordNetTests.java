import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WordNetTests {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private WordNet wn;
    File synsetFile, hypernumFile;

    @Before
    public void setUp() throws IOException {
//     wn = new WordNet("synset.txt", "hypernum.txt");
        prepareSyntexData();
        prepareHypernumData();
        wn = new WordNet(synsetFile.getAbsolutePath(), hypernumFile.getAbsolutePath());
    }

    private void prepareSyntexData() throws IOException {
        synsetFile = tempFolder.newFile("synset.txt");
        BufferedWriter  bw = new BufferedWriter(new FileWriter(synsetFile));
        bw.write("36,AND_circuit AND_gate,a circuit in a computer that fires only when all of its inputs fire \r ");
        bw.write("72,Java         JMX,a circuit in a computer that fires only when all of its inputs fire");
        bw.flush();
        bw.close();
    }

    private void prepareHypernumData() throws IOException {
        hypernumFile = tempFolder.newFile("hypernum.txt");
        BufferedWriter  bw = new BufferedWriter(new FileWriter(hypernumFile));
        bw.write("36, 12\n");
        bw.write("12, 10\n");
        bw.write("72, 34\n");
        bw.write("34, 10\n");
        bw.flush();
        bw.close();
    }

    @Test
    public void readsFromCSVDataAndInsertIdAndWordIntoMap() throws IOException {
        Map<String, Integer> words  = wn.getSynset();
        assertThat(words.get("Java")).isEqualTo(72);
        assertThat(words.get("JMX")).isEqualTo(72);
    }

    @Test
    public void readsFromCSVHypernumIdsAndInsertThemInTheMap() throws IOException {
        Map<Integer, ArrayList<Integer>> ids = wn.getHypernyms();
        assertThat(ids.get(36).get(0)).isEqualTo(12);
    }

    @Test(expected = NullPointerException.class)
    public void throwNullPointerExceptionWhenConstructorInvokedIfOneArgumentIsNull() {
        new WordNet(null, "some.txt");
        new WordNet("", null);
    }

    @Test
    public void returnWhetherGivenWordInWordNetGraph() throws IOException {
        assertThat(wn.isNoun("Java")).isTrue();
    }

    @Test
    public void returnsSetOfWordsInGraph() throws IOException {
        Iterator<String> iterator = wn.nouns().iterator();
        assertThat(iterator.next()).isIn("AND_circuit", "AND_gate", "Java", "JMX");
    }

    private void prepareSpecialSyntexData() throws IOException {
        synsetFile = tempFolder.newFile("synset.txt");
        BufferedWriter  bw = new BufferedWriter(new FileWriter(synsetFile));
        bw.write("36, one, 12\n");
        bw.write("12, two, 12\n");
        bw.write("10, three, 10\n");
        bw.write("72, four, 72\n");
        bw.write("34, five, 12\n");
        bw.flush();
        bw.close();
    }

}
