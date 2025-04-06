/**
 * Classe que representa uma Árvore AVL,uma árvore binária de busca
 * autobalanceada que garante operações eficientes.
 *
 * @author Guilherme Rosmaninho
 * @version 1.0
 */
public class AVLTree {

    /**
     * Classe interna que representa um nó da Árvore AVL.
     */
    private class Node {
        int chave, altura;
        Node esquerda, direita;

        /**
         * Construtor do nó.
         *
         * @param chave A chave inteira a ser armazenada no nó.
         */
        Node(int chave) {
            this.chave = chave;
            this.altura = 1;
        }
    }

    private Node root;

    /**
     * Número total de rotações realizadas durante as inserções.
     */
    public int rotacoes = 0;

    /**
     * Insere uma nova chave inteira na Árvore AVL.
     *
     * @param chave A chave a ser inserida.
     */
    public void insert(int chave) {
        root = insertRec(root, chave);
    }

    /**
     * Método recursivo auxiliar para inserção de uma chave.
     *
     * @param node Subárvore atual.
     * @param chave Chave a ser inserida.
     * @return A subárvore atualizada após a inserção e eventual balanceamento.
     */
    private Node insertRec(Node node, int chave) {
        if (node == null) return new Node(chave);

        if (chave < node.chave) {
            node.esquerda = insertRec(node.esquerda, chave);
        } else if (chave > node.chave) {
            node.direita = insertRec(node.direita, chave);
        } else {
            // Chave duplicada, não faz nada
            return node;
        }

        updateAltura(node);
        return balance(node);
    }

    /**
     * Atualiza a altura de um nó com base nas alturas dos seus filhos.
     *
     * @param node O nó cuja altura deve ser atualizada.
     */
    private void updateAltura(Node node) {
        node.altura = 1 + Math.max(altura(node.esquerda), altura(node.direita));
    }

    /**
     * Retorna a altura de um nó.
     *
     * @param node O nó a ser avaliado.
     * @return A altura do nó, ou 0 se for {@code null}.
     */
    private int altura(Node node) {
        return node == null ? 0 : node.altura;
    }

    /**
     * Calcula o fator de balanceamento de um nó.
     *
     * @param node O nó a ser avaliado.
     * @return A diferença entre as alturas dos filhos esquerdo e direito.
     */
    private int getBalance(Node node) {
        return altura(node.esquerda) - altura(node.direita);
    }

    /**
     * Realiza o balanceamento do nó fornecido, utilizando rotações se necessário.
     *
     * @param node O nó a ser balanceado.
     * @return O novo nó raiz da subárvore após o balanceamento.
     */
    private Node balance(Node node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.esquerda) < 0)
                node.esquerda = rotacaoEsquerda(node.esquerda); // rotação dupla
            return rotacaoDireita(node);
        }
        if (balance < -1) {
            if (getBalance(node.direita) > 0)
                node.direita = rotacaoDireita(node.direita); // rotação dupla
            return rotacaoEsquerda(node);
        }

        return node;
    }

    /**
     * Realiza uma rotação à direita para restaurar o balanceamento da árvore.
     *
     * @param y O nó desbalanceado.
     * @return Novo nó raiz da subárvore após a rotação.
     */
    private Node rotacaoDireita(Node y) {
        rotacoes++;
        Node x = y.esquerda;
        y.esquerda = x.direita;
        x.direita = y;
        updateAltura(y);
        updateAltura(x);
        return x;
    }

    /**
     * Realiza uma rotação à esquerda para restaurar o balanceamento da árvore.
     *
     * @param x O nó desbalanceado.
     * @return Novo nó raiz da subárvore após a rotação.
     */
    private Node rotacaoEsquerda(Node x) {
        rotacoes++;
        Node y = x.direita;
        x.direita = y.esquerda;
        y.esquerda = x;
        updateAltura(x);
        updateAltura(y);
        return y;
    }
}