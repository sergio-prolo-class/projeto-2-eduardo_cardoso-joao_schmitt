package ifsc.joe.utils;

/**
 * Classe responsável por centralizar todas as constantes e configurações do jogo.
 */
public class Config {
    // --- Atributos dos Personagens ---

    // Aldeão
    public static final int ALDEAO_VIDA = 25;
    public static final int ALDEAO_ATAQUE = 1;
    public static final int ALDEAO_ALCANCE = 50;

    // Arqueiro
    public static final int ARQUEIRO_VIDA = 35;
    public static final int ARQUEIRO_ATAQUE = 2;
    public static final int ARQUEIRO_ALCANCE = 150;

    // Cavaleiro
    public static final int CAVALEIRO_VIDA = 50;
    public static final int CAVALEIRO_ATAQUE = 3;
    public static final int CAVALEIRO_ALCANCE = 75;

    // --- Mecânicas de Jogo ---

    public static final int VELOCIDADE_PADRAO = 10; // Pixels por movimento

    // --- Interface Visual ---

    public static final int PADDING_BORDAS = 50; // Margem de segurança para criar personagens

    // Barra de Vida
    public static final int BARRA_VIDA_ALTURA = 5;
    public static final int BARRA_VIDA_OFFSET = 10; // Distância acima da cabeça
}