import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordNet {

    private final static String separator = ",";
    private Map<String, Integer> synset = new HashMap<String, Integer>();
    private Map<Integer, ArrayList<Integer>> hypernyms = new HashMap<Integer, ArrayList<Integer>>();

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms){

    }

    protected void processSynsets(String synsetsFile) {
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(synsetsFile));
            String line = null;
            while((line = bf.readLine()) != null) {
                String csvLine[] = line.split(separator);
                String words[]  = getWordsFromSynset(csvLine[1]);
                Integer id = Integer.parseInt(csvLine[0].trim());
                putWordsToMap(words, id);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] getWordsFromSynset(String synSet) {
        return synSet.split("\\s+");
    }

    private void putWordsToMap(String[] words, Integer id) {
        for (String word : words) {
            synset.put(word, id);
        }
    }

    private void processHypernyms() {

    }

    // returns all WordNet nouns
    public Iterable<String> nouns(){
        return null;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return true;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB){
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB){
        return null;
    }

    public Map<String, Integer> getSynset() {
        return synset;
    }

    public void setSynset(Map<String, Integer> synset) {
        this.synset = synset;
    }

    public Map<Integer, ArrayList<Integer>> getHypernyms() {
        return hypernyms;
    }

    public void setHypernyms(Map<Integer, ArrayList<Integer>> hypernyms) {
        this.hypernyms = hypernyms;
    }
}
