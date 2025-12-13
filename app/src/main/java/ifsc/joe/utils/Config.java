package ifsc.joe.utils;

import ifsc.joe.domain.Personagem;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Config {
    // Configurações Globais
    public static final int PADDING_BORDAS = 50;

    // Configurações de Personagens
    public static final int VELOCIDADE_PADRAO = 5;

    // Configurações Visuais
    public static final int BARRA_VIDA_ALTURA = 5;
    public static final int BARRA_VIDA_OFFSET = 10;

    // Efeitos Visuais
    // 0.016f = ~1 segundo
    public static final float TAXA_FADE_OUT = 0.016f;

    // Atributos Específicos
    public static final int ALDEAO_VIDA = 50;
    public static final int ALDEAO_ATAQUE = 5;
    public static final int ALDEAO_ALCANCE = 30;

    public static final int ARQUEIRO_VIDA = 40;
    public static final int ARQUEIRO_ATAQUE = 10;
    public static final int ARQUEIRO_ALCANCE = 150;

    public static final int CAVALEIRO_VIDA = 80;
    public static final int CAVALEIRO_ATAQUE = 15;
    public static final int CAVALEIRO_ALCANCE = 50;

    // Chances de Esquiva
    public static final double ALDEAO_ESQUIVA = 0.10;
    public static final double ARQUEIRO_ESQUIVA = 0.25;
    public static final double CAVALEIRO_ESQUIVA = 0.15;

    static public Clip obterClipSom(String nomeSom) {
        try {
            URL url = Personagem.class.getClassLoader().getResource(nomeSom);
            if (url != null) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                return clip;
            } else {
                System.err.printf("Sound not found: %s", nomeSom);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public void tocarSom(Clip clip) {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }
}