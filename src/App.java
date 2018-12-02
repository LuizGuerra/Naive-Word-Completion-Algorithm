public class App {
    public static void main(String[] args) {
        ReadCSVFile read_it = new ReadCSVFile();
        String [][] information = read_it.read();
        Integer size = read_it.getFile_size();

        for (int i = 0; i < size; i++) {
            System.out.println(information[i][0] + "\t\t" + information[i][1]);
        }

    }
}
