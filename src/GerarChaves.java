import java.util.*;

/**
 * Classe utilitária responsável por gerar diferentes conjuntos de chaves inteiras
 * com padrões variados para testes de desempenho em árvores de pesquisa.
 *
 * Os métodos fornecem sequências ordenadas, reversas, aleatórias e com repetições.
 *
 * @author Guilherme Rosmaninho
 * @version 1.0
 */
public class GerarChaves {

    /**
     * Gera um conjunto de chaves ordenadas de 1 até n.
     *
     * @param n tamanho do conjunto
     * @return array ordenado de inteiros de 1 até n
     */
    public static int[] generateA(int n) {
        return range(1, n);
    }

    /**
     * Gera um conjunto de chaves ordenadas de forma decrescente, de n até 1.
     *
     * @param n tamanho do conjunto
     * @return array com inteiros de n até 1
     */
    public static int[] generateB(int n) {
        int[] arr = range(1, n);
        inverter(arr);
        return arr;
    }

    /**
     * Gera um conjunto de chaves aleatoriamente embaralhadas no intervalo de 1 até n.
     *
     * @param n tamanho do conjunto
     * @return array embaralhado com inteiros de 1 até n
     */
    public static int[] generateC(int n) {
        int[] arr = range(1, n);
        shuffle(arr);
        return arr;
    }

    /**
     * Gera um conjunto de chaves com 90% dos elementos repetidos (valor 1)
     * e os restantes com números aleatórios entre 2 e n.
     *
     * @param n tamanho do conjunto
     * @return array com muitos valores repetidos e alguns distintos
     */
    public static int[] generateD(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        int numeroRepetidos = (int) (0.9 * n);
        for (int i = 0; i < n; i++) {
            if (i <= numeroRepetidos) {
                arr[i] = 1;
            } else {
                arr[i] = rand.nextInt(n - 1) + 2; // Número entre 2 e n
            }
        }
        shuffle(arr);
        return arr;
    }

    /**
     * Gera um array com valores consecutivos a partir de um valor inicial.
     *
     * @param start valor inicial
     * @param count quantidade de elementos
     * @return array de inteiros consecutivos
     */
    private static int[] range(int start, int count) {
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) arr[i] = start + i;
        return arr;
    }

    /**
     * Embaralha aleatoriamente os elementos de um array.
     *
     * @param arr array de inteiros a ser embaralhado
     */
    private static void shuffle(int[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    /**
     * Inverte a ordem dos elementos de um array.
     *
     * @param arr array de inteiros a ser invertido
     */
    private static void inverter(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}