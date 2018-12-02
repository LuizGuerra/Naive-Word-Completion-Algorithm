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
    private Integer file_size;
    //private

    public ReadCSVFile () {
        // You will have to change the path name in your desktop
        this.path = "src\\nomes.csv";
        this.br = null;
        this.line = "";
        this.split_on = ";";
        this.file_size = file_size();
    }

    public Integer getFile_size(){
        return this.file_size;
    }

    /**
     * Here, the method evaluates the amount of lines that the file have.
     */
    private Integer file_size() {
        Integer fs = 0;
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(path));
            lnr.skip(Long.MAX_VALUE);
            fs = lnr.getLineNumber();
        } catch (Exception e) {
            System.out.println(e);
        }
        return fs;
    }

    /**
     * Here the method reads the CSV file and return it as a 2 dimensions array.
     */
    public String [][] read () {
        if (this.file_size == 0) {
            return null;
        }
        String [][] information = new String[this.file_size][2];
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        int aux = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(split_on);
                information[aux][0] = data[0];
                information[aux][1] = data[1];
                aux++;
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
