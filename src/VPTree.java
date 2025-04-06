/**
 * Classe que representa uma árvore vermelho-preto (VP), uma estrutura de dados
 * balanceada que garante inserções eficientes em tempo logarítmico.
 *
 * @version 1.0
 */
public class VPTree {

    private static final boolean VERMELHO = true;
    private static final boolean PRETO = false;

    /**
     * Classe que representa um nó da árvore.
     */
    private class Node {
        int key;
        Node esquerda, direita;
        boolean cor;

        /**
         * Construtor do nó.
         *
         * @param key A chave inteira a ser armazenada no nó.
         */
        Node(int key) {
            this.key = key;
            this.cor = VERMELHO;
        }
    }

    private Node root;

    /**
     * Número total de rotações realizadas durante inserções.
     */
    public int rotacoes = 0;

    /**
     * Insere uma nova chave inteira na árvore.
     *
     * @param key A chave a ser inserida.
     */
    public void insert(int key) {
        root = insertRec(root, key);
        root.cor = PRETO;
    }

    /**
     * Verifica se um nó é vermelho.
     *
     * @param node O nó a ser verificado.
     * @return true se o nó for vermelho, false caso contrário.
     */
    private boolean isRed(Node node) {
        return node != null && node.cor == VERMELHO;
    }

    /**
     * Método recursivo auxiliar para inserção de uma chave.
     *
     * @param node Subárvore atual.
     * @param key Chave a ser inserida.
     * @return A subárvore atualizada após a inserção e possíveis rotações.
     */
    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key);

        if (key < node.key) {
            node.esquerda = insertRec(node.esquerda, key);
        } else if (key > node.key) {
            node.direita = insertRec(node.direita, key);
        } else {
            // Chave duplicada, não faz nada
            return node;
        }

        if (isRed(node.direita) && !isRed(node.esquerda)) node = rotateLeft(node);
        if (isRed(node.esquerda) && isRed(node.esquerda.esquerda)) node = rotateRight(node);
        if (isRed(node.esquerda) && isRed(node.direita)) flipColors(node);

        return node;
    }

    /**
     * Realiza uma rotação à esquerda para balancear a árvore.
     *
     * @param h O nó desbalanceado.
     * @return Novo nó raiz da subárvore após rotação.
     */
    private Node rotateLeft(Node h) {
        rotacoes++;
        Node x = h.direita;
        h.direita = x.esquerda;
        x.esquerda = h;
        x.cor = h.cor;
        h.cor = VERMELHO;
        return x;
    }

    /**
     * Realiza uma rotação à direita para balancear a árvore.
     *
     * @param h O nó desbalanceado.
     * @return Novo nó raiz da subárvore após rotação.
     */
    private Node rotateRight(Node h) {
        rotacoes++;
        Node x = h.esquerda;
        h.esquerda = x.direita;
        x.direita = h;
        x.cor = h.cor;
        h.cor = VERMELHO;
        return x;
    }

    /**
     * Inverte as cores de um nó e seus filhos, mantendo as propriedades da árvore VP.
     *
     * @param h O nó cujo esquema de cores deve ser invertido.
     */
    private void flipColors(Node h) {
        h.cor = VERMELHO;
        if (h.esquerda != null) h.esquerda.cor = PRETO;
        if (h.direita != null) h.direita.cor = PRETO;
    }
}