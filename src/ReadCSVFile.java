// package PACKAGE_NAME;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import java.util.List;
import java.util.ArrayList;

public class ReadCSVFile {

    private String path, line, split_on;
    private BufferedReader br;
    //private

    public ReadCSVFile () {
        // You will have to change the path name in your desktop
        this.path = "src\\nomes.csv";
        this.br = null;
        this.line = "";
        this.split_on = ";";
    }

    /**
     * Here the method reads the CSV file and return it as a 2 dimensions array.
     */
    public List<List<String>> read () {
        List<List<String>> information = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        int aux = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(split_on);
                List<String> helper = new ArrayList<>();
                helper.add(data[0]);
                helper.add(data[1]);
                information.add(helper);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return information;
    }
}
