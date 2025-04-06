import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Classe principal responsável por realizar testes de desempenho
 * com os diferentes tipos de árvores (BST, AVL, VP, TREAP),
 * inserindo diferentes conjuntos de dados e registrando os tempos
 * e rotações num ficheiro CSV.
 *
 * @author Guilherme Rosmaninho
 * @version 1.0
 */
public class Main {

    /**
     * Método principal que executa os testes e gera o ficheiro CSV com os resultados.
     *
     * @param args argumentos da linha de comandos (não utilizados).
     */
    public static void main(String[] args) {
        int[] sizes = {10000, 25000, 50000, 75000, 100000, 250000, 500000, 750000, 1000000, 2000000, 3000000, 4000000, 5000000};
        String[] treeTypes = {"BST", "AVL", "VP", "TREAP"};
        String[] labels = {"A", "B", "C", "D"};

        // Obter data/hora atual e formatar para nome do ficheiro
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = now.format(formatter);
        String fileName = "resultados-" + formattedDateTime + ".csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("TreeType;Dataset;Size;Time(s);Rotations\n");

            for (String treeType : treeTypes) {
                System.out.printf("Tree Type: %s%n", treeType);

                for (int size : sizes) {
                    if (treeType.equals("BST") && size > 100000) {
                        continue;
                    }

                    if (!treeType.equals("BST") && size < 100000) {
                        continue;
                    }

                    int[][] datasets = {
                            GerarChaves.generateA(size),
                            GerarChaves.generateB(size),
                            GerarChaves.generateC(size),
                            GerarChaves.generateD(size)
                    };

                    for (int i = 0; i < datasets.length; i++) {
                        int[] dataset = datasets[i];
                        String label = labels[i];

                        Temporizador temporizador = new Temporizador();
                        double time = 0.0;
                        int rotations = 0;

                        switch (treeType) {
                            case "BST":
                                BinaryTree bst = new BinaryTree();
                                temporizador.inicio();
                                for (int key : dataset) bst.inserir(key);
                                time = temporizador.fim();
                                System.out.printf("  -> BST - Conjunto %s (Size: %d): Tempo = %.4f s%n", label, size, time);
                                writer.write(String.format(Locale.FRANCE, "%s;%s;%d;%.4f;%d\n", treeType, label, size, time, 0));
                                break;

                            case "AVL":
                                AVLTree avl = new AVLTree();
                                temporizador.inicio();
                                for (int key : dataset) avl.insert(key);
                                time = temporizador.fim();
                                rotations = avl.rotacoes;
                                System.out.printf("  -> AVL - Conjunto %s (Size: %d): Tempo = %.4f s, Rotações = %d%n", label, size, time, rotations);
                                writer.write(String.format(Locale.FRANCE, "%s;%s;%d;%.4f;%d\n", treeType, label, size, time, rotations));
                                break;

                            case "VP":
                                VPTree rb = new VPTree();
                                temporizador.inicio();
                                for (int key : dataset) rb.insert(key);
                                time = temporizador.fim();
                                rotations = rb.rotacoes;
                                System.out.printf("  -> VP - Conjunto %s (Size: %d): Tempo = %.4f s, Rotações = %d%n", label, size, time, rotations);
                                writer.write(String.format(Locale.FRANCE, "%s;%s;%d;%.4f;%d\n", treeType, label, size, time, rotations));
                                break;

                            case "TREAP":
                                TreapTree treap = new TreapTree();
                                temporizador.inicio();
                                for (int key : dataset) treap.insert(key);
                                time = temporizador.fim();
                                rotations = treap.rotacoes;
                                System.out.printf("  -> TREAP - Conjunto %s (Size: %d): Tempo = %.4f s, Rotações = %d%n", label, size, time, rotations);
                                writer.write(String.format(Locale.FRANCE, "%s;%s;%d;%.4f;%d\n", treeType, label, size, time, rotations));
                                break;
                        }
                    }
                }
            }

            System.out.println("\nProcesso terminado. Resultados foram guardados em: " + fileName);

        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }
}