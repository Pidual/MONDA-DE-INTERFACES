package presenter;

import models.Dictionary;
import persitence.FileOperationXML;
import views.DiccionaryView;
import persitence.FileOperation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Presenter implements ActionListener {

    private DiccionaryView view;
    private Dictionary wikipedia;
    private FileOperationXML fileOperationXML;
    private int i;
    private int counter;

    public void run() {
        wikipedia = new Dictionary();

        view = new DiccionaryView(this);

        fileOperationXML = new FileOperationXML("src/resources/Diccionario.XML");
        
        loadXML();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        String text = view.getText();
        switch (command) {
            case "findTheSynonym":
                if (wikipedia.searchWord(text) == null) { //Busca la palabra
                    view.wordNotFound();

                } else if (wikipedia.searchWord(text).getSynonymCount() == 0) {
                    counter = wikipedia.searchWord(text).getSynonymCount();
                    view.actualizeWordShowCaser("Word has not synonyms", counter);

                } else {
                    counter = wikipedia.searchWord(text).getSynonymCount();
                    view.actualizeWordShowCaser(wikipedia.searchWord(text).getSynonymsList().get(i), counter);
                }
                break;
            case "leftButtonPressed":
                i--;
                if (i < 0) {
                    i = counter - 1;
                }
                view.actualizeWordShowCaserSynonyms(wikipedia.searchWord(text).getSynonymsList().get(i));
                break;
            case "rigthButtonPressed":
                i++;
                if (i > counter - 1) {
                    i = 0;
                }
                view.actualizeWordShowCaserSynonyms(wikipedia.searchWord(text).getSynonymsList().get(i));
                break;
            case "ShowAddWord":
                view.setJDialogWordsVisibility(true);
                break;

            case "ShowAddSynonym":
                view.setJDialogSynonymsVisibility(true);
                break;
            case "addWord":
                wikipedia.addWord(view.getJDialogForWords());
                view.setJDialogWordsVisibility(false);
                save();
                break;
            case "addSynonym":
                wikipedia.addSynonimous(wikipedia.searchWord(text), view.getJDialogForSynonyms());
                view.setJDialogSynonymsVisibility(false);
                save();
                break;
            default:
                break;
        }
    }

    /**
     * Este metodo carga los archivos de texto
     */
    public  void loadXML(){
        wikipedia.setWordsList(fileOperationXML.getArrayListXML());
    }


    public void save(){
        fileOperationXML.saveTextFile(wikipedia.getWordsList());
    }

}