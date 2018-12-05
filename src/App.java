import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ReadCSVFile read_it = new ReadCSVFile();
        List<List<String>> information = read_it.read();

        SearchTree st = new SearchTree();
        for (int i = 0; i < information.size(); i++) {
            //System.out.printf("%S\t\t%S\n", information.get(i).get(0), information.get(i).get(1));
            st.add(information.get(i).get(0), information.get(i).get(1));
        }

        //System.out.println(st.getSize());
        //System.out.println(st.searchName("aga", st.root));
        //System.out.println(st.findSubtreeSize(st.root)-1);
        List<List<String>> lista = st.searchName("be");
        for (List<String> i : lista) {
            System.out.printf("%s: %s\n", i.get(0), i.get(1));
        }



    }
}
