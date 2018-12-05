import java.text.Normalizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {

        // Lê a file, recebe a informação em formato de ArrayList dentro de outra ArrayList
        ReadCSVFile read_it = new ReadCSVFile();
        List<List<String>> information = read_it.read();

        // Cria o objeto árvore e alimenta ela
        SearchTree st = new SearchTree();
        for (int i = 0; i < information.size(); i++) {
            st.add(information.get(i).get(0), information.get(i).get(1));
        }

        // Recebe input do usuário
        Scanner input = new Scanner(System.in);
        String yn = "y";
        System.out.print("Você deseja pesquisar o significado de algum nome? [y/n] ");
        yn = input.nextLine().toLowerCase();

        // Valida a entrada do usuário
        while (!yn.equals("y") && !yn.equals("n")) {
            yn = tryAgain(yn);
        }

        // Inicia o projeto
        while (yn.equals("y")) {
            System.out.print("Digite uma String qualquer entre 2 ou 3 caracteres: ");
            String qualquer = deAccent(input.nextLine().toLowerCase());
            while (qualquer.length() < 2 || qualquer.length() > 3) {
                System.out.print("Por favor, digite uma String qualquer entre 2 ou 3 caracteres: ");
                qualquer = deAccent(input.nextLine().toLowerCase());
            }
            print(st, qualquer);

            System.out.print("\nVocê deseja pesquisar o significado de algum nome novamente? [y/n] ");
            yn = input.nextLine().toLowerCase();

            while (!yn.equals("y") && !yn.equals("n")) {
                yn = tryAgain(yn).toLowerCase();
            }
        }

        // Finalização
        System.out.println("Até mais! :D");
    }

    // O(n)
    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    // O(n)
    public static String tryAgain (String yn) {
        Scanner input = new Scanner(System.in);

        System.out.print("Desculpa, eu não entendi, pode responder com \"y\" (Sim) ou \"n\" (Não)? Obrigado :) ");
        yn = deAccent(input.nextLine().toLowerCase());
        return yn;
    }

    // O(n)
    public static void print(SearchTree st, String str) {
        Scanner input = new Scanner(System.in);
        try {
            List<List<String>> lista = st.searchName(str);
            if (lista == null) {
                System.out.println("ERRO 01: String inválida ou não existente na árvore! Tente novamente.");
            } else {
                System.out.println("Selecione um nome:");
                for (List<String> i : lista) {
                    System.out.printf("%s\n", i.get(0));
                }
                String sss = input.nextLine();
                System.out.println();
                while (!isIt(sss, lista)) {
                    System.out.println("Por favor selecione um nome da lista!");
                    sss = input.nextLine();
                }
                for (List<String> i : lista) {
                    if (i.get(0).equals(sss)) {
                        System.out.printf("%s: %s\n", i.get(0), i.get(1));
                        break;
                    }
                }
            }
            st.cleanList();
        } catch (Exception e) {
            System.out.println("ERRO 02: String inválida ou não existente na árvore! Tente novamente.");
        }

    }

    public static boolean isIt (String str, List<List<String>> lista) {
        for (List<String> s : lista) {
            if (str.equals(s.get(0))) {
                return true;
            }
        }
        return false;
    }

}
