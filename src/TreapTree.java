import java.util.Random;

/**
 * Classe que representa uma Treap, uma árvore binária de busca que mantém as
 * propriedades de um heap de prioridades aleatórias.
 *
 * @author Guilherme Rosmaninho
 * @version 1.0
 */
public class TreapTree {

    /**
     * Classe interna que representa um nó da Treap.
     */
    private class Node {
        int chave, prioridade;
        Node esquerda, direita;

        /**
         * Construtor do nó.
         *
         * @param chave A chave inteira a ser armazenada no nó.
         */
        Node(int chave) {
            this.chave = chave;
            this.prioridade = rand.nextInt();
        }
    }

    private Node root;
    private Random rand = new Random();

    /**
     * Número total de rotações realizadas durante inserções.
     */
    public int rotacoes = 0;

    /**
     * Insere uma nova chave inteira na Treap.
     *
     * @param chave A chave a ser inserida.
     */
    public void insert(int chave) {
        root = insertRec(root, chave);
    }

    /**
     * Método recursivo auxiliar para inserção de uma chave.
     *
     * Garante as propriedades da árvore binária de busca e do heap de prioridades por meio de rotações quando necessário.
     *
     * @param node Subárvore atual.
     * @param chave Chave a ser inserida.
     * @return A subárvore atualizada após a inserção e possíveis rotações.
     */
    private Node insertRec(Node node, int chave) {
        if (node == null) return new Node(chave);

        if (chave < node.chave) {
            node.esquerda = insertRec(node.esquerda, chave);
            if (node.esquerda.prioridade > node.prioridade)
                node = rotateRight(node);
        } else if (chave > node.chave) {
            node.direita = insertRec(node.direita, chave);
            if (node.direita.prioridade > node.prioridade)
                node = rotateLeft(node);
        } else {
            // Chave duplicada, não faz nada
            return node;
        }

        return node;
    }

    /**
     * Realiza uma rotação à esquerda para manter as propriedades do heap.
     *
     * @param x O nó desbalanceado.
     * @return Novo nó raiz da subárvore após rotação.
     */
    private Node rotateLeft(Node x) {
        rotacoes++;
        Node y = x.direita;
        x.direita = y.esquerda;
        y.esquerda = x;
        return y;
    }

    /**
     * Realiza uma rotação à direita para manter as propriedades do heap.
     *
     * @param y O nó desbalanceado.
     * @return Novo nó raiz da subárvore após rotação.
     */
    private Node rotateRight(Node y) {
        rotacoes++;
        Node x = y.esquerda;
        y.esquerda = x.direita;
        x.direita = y;
        return x;
    }
}