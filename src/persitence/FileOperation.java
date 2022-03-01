package persitence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperation {

    String path;

    public ArrayList<String> loadTextFile(String path) {
        this.path = path;
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader(path));
            String line;
            while ((line = buffer.readLine()) != null) {
                list.add(line);
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveTextFile(ArrayList<String> wordsAndSynonyms) {
        try {
            ArrayList<String> lines = wordsAndSynonyms;
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (int i = 0; i < lines.size(); i++) {
                writer.write(lines.get((i)));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}