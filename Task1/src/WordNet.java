import java.io.*;
import java.util.*;

public class WordNet {

    private final static String separator = ",";
    public static final String NEW_LINE_SYMBOL = "\n";

    private Map<String, Integer> synset = new HashMap<String, Integer>();
    private Map<Integer, ArrayList<Integer>> hypernyms = new HashMap<Integer, ArrayList<Integer>>();

    public WordNet(String synsets, String hypernyms) {
        throwExceptionIfNull(synsets, hypernyms);
        processSynsets(synsets);
        processHypernyms(hypernyms);
    }

    private void processSynsets(String synsetsFile) {
        BufferedReader bf = null;
        String[] lines = retrieveDataFromFile(synsetsFile).split(NEW_LINE_SYMBOL);
        for (String line : lines) {
            String csvLine[] = line.split(separator);
            String words[] = getWordsFromSynset(csvLine[1]);
            Integer id = Integer.parseInt(csvLine[0].trim());
            putWordsToMap(words, id, synset);
        }
    }

    private String[] getWordsFromSynset(String synSet) {
        return synSet.split("\\s+");
    }

    private void processHypernyms(String hypernymsFile) {
        String[] lines = retrieveDataFromFile(hypernymsFile).split(NEW_LINE_SYMBOL);
        for(String line : lines) {
            String csvLine[] = line.split(separator);
            putIdsToMap(csvLine, hypernyms);
        }
    }

    private void putIdsToMap(String ids[], Map map) {
        ArrayList<Integer> list = new ArrayList();
        Integer id = Integer.parseInt(ids[0].trim());
        for(int i = 1; i < ids.length; i++) {
            list.add(Integer.parseInt(ids[i].trim()));
        }
        map.put(id, list);
    }

    private String retrieveDataFromFile(String fileName) {
        BufferedReader bf = null;
        StringBuffer fileMirror = new StringBuffer();
        try {
            bf = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ((line = bf.readLine()) != null) {
                fileMirror.append(line);
                fileMirror.append(NEW_LINE_SYMBOL);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileMirror.toString();
    }

    private void putWordsToMap(String[] words, Integer id, Map map) {
        for (String word : words) {
            map.put(word.trim(), id);
        }
    }

    public Iterable<String> nouns() {
        return synset.keySet();
    }

    public boolean isNoun(String word) {
        throwExceptionIfNull(word);

        return synset.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        throwExceptionIfNull(nounA, nounB);
        return 0;
    }

    private void throwExceptionIfNull(Object...obj) {
        for(Object o:obj){
            if(o == null) {
                throw new NullPointerException();
            }
        }
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
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
