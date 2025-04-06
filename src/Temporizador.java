/**
 * Classe utilitária para medir o tempo de execução de operações.
 *
 * @author Guilherme Rosmaninho
 * @version 1.0
 */
public class Temporizador {
    private long tempoInicial;

    /**
     * Inicia a contagem do tempo, armazenando o instante atual em nanossegundos.
     */
    public void inicio() {
        tempoInicial = System.nanoTime();
    }

    /**
     * Finaliza a contagem do tempo e devolve o tempo decorrido em segundos.
     *
     * @return tempo decorrido desde o início, em segundos (com casas decimais)
     */
    public double fim() {
        return (System.nanoTime() - tempoInicial) / 1_000_000_000.0;
    }
}