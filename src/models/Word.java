package models;

import java.util.ArrayList;

public class Word {

    private String word;
    private ArrayList<String> synonimousList;

    public Word(String word, ArrayList<String> synonymousList) {
        this.word = word;
        this.synonimousList = synonymousList;
    }

    public void addSynonym(String synonym) {
        synonimousList.add(synonym);
    }

    public int getSynonymCount() {
        return synonimousList.size();
    }

    public String getWord() {
        return word;
    }

    public ArrayList<String> getSynonymsList() {
        return synonimousList;
    }

    public String getSynonym(int i) {
        return synonimousList.get(i);
    }
}