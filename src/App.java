import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

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
            System.out.print("Digite uma String qualquer: ");
            String qualquer = input.nextLine().toLowerCase();
            print(st, qualquer);

            System.out.print("\nVocê deseja pesquisar o significado de algum nome novamente? [y/n] ");
            yn = input.nextLine().toLowerCase();

            while (!yn.equals("y") && !yn.equals("n")) {
                yn = tryAgain(yn);
            }
        }

        // Finalização
        System.out.println("Até mais! :D");
    }

    public static String tryAgain (String yn) {
        Scanner input = new Scanner(System.in);

        System.out.print("Desculpa, eu não entendi, pode responder com \"y\" (Sim) ou \"n\" (Não)? Obrigado :) ");
        yn = input.nextLine().toLowerCase();
        return yn;
    }

    public static void print(SearchTree st, String str) {
        try {
            List<List<String>> lista = st.searchName(str);
            for (List<String> i : lista) {
                System.out.printf("%s: %s\n", i.get(0), i.get(1));
            }
            st.cleanList();
        } catch (Exception e) {
            System.out.println("String inválida ou não existente na árvore! Tente novamente.");
        }

    }

}
