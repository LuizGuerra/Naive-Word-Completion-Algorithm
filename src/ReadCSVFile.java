// package PACKAGE_NAME;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVFile {

    private String path, line, split_on;
    private BufferedReader br;

    public ReadCSVFile () {
        // You will have to change the path name in your desktop
        this.path = "C:\\Users\\Celito\\Desktop\\T3_Algoritmo_I\\src\\nomes.csv";
        this.br = null;
        this.line = "";
        this.split_on = ";";
    }

    public void read () {
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String [] name = line.split(split_on);
                System.out.println("Name [" + name[0] + " , meaning = " + name[1] + "]");
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
    }

}
