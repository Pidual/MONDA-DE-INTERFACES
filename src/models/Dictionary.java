package models;

import java.util.ArrayList;

public class Dictionary {

    ArrayList<Word> wordsList;

    public Dictionary() {
        wordsList = new ArrayList<Word>();
    }

    public void setWordsList(ArrayList<String> list){
        boolean mainWordSpot = true;
        String mainWord = "";
        ArrayList<String> synonyms = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            if(list.get(i) != ("") && mainWordSpot == true){
                mainWord = list.get(i);
                mainWordSpot = false;

            }else if (list.get(i) != ("")){

                synonyms.add(list.get(i));

            }else if (list.get(i) == ("")){
                wordsList.add(new Word(mainWord,synonyms)); //Crea la palabra de de paso la agrega
                mainWordSpot = true;
                synonyms = new ArrayList<>();
            }

        }

    }

    private Word createWord(String addWord, ArrayList<String> synonims) {
        return new Word(addWord, synonims);
    }

    public void addSynonimous(Word word, String synonimous) {
         for (Word theword : wordsList) {
             if(theword == word) {
                theword.addSynonym(synonimous);
             }
         }
    }

    public void addWord(String word){ //Agrega la palabra sin sinonimos
        wordsList.add(createWord(word, new ArrayList<String>()));
    }

    public Word searchWord(String word) {
        Word searchedWord = null;
        for (Word words : wordsList) {
            if (words.getWord().equalsIgnoreCase(word)) {
                searchedWord = words;
            }
        }
        return searchedWord;
    }

    public ArrayList<String> getWordsListTextFormated() {
        String mainWord;
        ArrayList<String> wordsListTxt = new ArrayList<>();
        for (int i = 0; i < wordsList.size(); i++) {
            wordsListTxt.add(wordsList.get(i).getWord()); //palabra max
            for (int j = 0; j < wordsList.get(i).getSynonymCount(); j++) {
                wordsListTxt.add(wordsList.get(i).getSynonym(j)); //sinonimos
            }
            wordsListTxt.add("");
        }
        return wordsListTxt;
    }

    public ArrayList<Word> getWordsList() {
        return wordsList;
    }
}