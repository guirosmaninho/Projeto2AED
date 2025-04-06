/**
 * Implementação de uma Árvore Binária (BST) com inserção iterativa.
 * Esta classe permite a inserção de chaves inteiras numa árvore binária,
 * mantendo a propriedade de ordenação: para cada nó, as chaves na subárvore
 * esquerda são menores, e as da subárvore direita são maiores.
 *
 * @author Guilherme Rosmaninho
 * @version 1.0
 */
public class BinaryTree {
    /** Raiz da árvore binária */
    private Node root;

    /**
     * Insere uma nova chave na árvore binária.
     * A inserção é feita de forma iterativa.
     *
     * @param chave valor inteiro a ser inserido
     */
    public void inserir(int chave) {
        root = inserirIter(root, chave);
    }

    /**
     * Método auxiliar que realiza a inserção iterativa de um nó na árvore.
     *
     * @param root raiz atual da subárvore
     * @param chave valor a ser inserido
     * @return raiz atualizada da subárvore
     */
    private Node inserirIter(Node root, int chave) {
        Node newNode = new Node(chave);
        if (root == null) {
            return newNode;
        }

        Node atual = root;
        Node parente = null;

        while (true) {
            parente = atual;
            if (chave < atual.chave) {
                atual = atual.esquerda;
                if (atual == null) {
                    parente.esquerda = newNode;
                    return root;
                }
            } else if (chave > atual.chave) {
                atual = atual.direita;
                if (atual == null) {
                    parente.direita = newNode;
                    return root;
                }
            } else {
                return root;
            }
        }
    }

    /**
     * Classe interna que representa um nó da árvore binária.
     */
    private static class Node {
        int chave;
        Node esquerda, direita;

        /**
         * Construtor do nó.
         *
         * @param item valor a ser armazenado no nó
         */
        public Node(int item) {
            chave = item;
            esquerda = direita = null;
        }
    }
}