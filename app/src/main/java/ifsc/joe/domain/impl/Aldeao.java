package ifsc.joe.domain.impl;

import ifsc.joe.domain.Coletador;
import ifsc.joe.domain.Guerreiro;
import ifsc.joe.domain.Personagem;
import ifsc.joe.utils.Config;

import javax.sound.sampled.*;

public class Aldeao extends Personagem implements Coletador, Guerreiro {
    private static int contagem_baixas;
    private static Clip ataqueClip;

    static {
        ataqueClip = obterClipSom("lanca.wav");
    }

    public static int getContagemBaixas() {
        return contagem_baixas;
    }

    public Aldeao(int x, int y) {
        super(x, y, "aldeao", Config.ALDEAO_VIDA, Config.ALDEAO_ATAQUE, Config.ALDEAO_ALCANCE, "lanca.wav");
        this.chanceEsquiva = Config.ALDEAO_ESQUIVA;
    }

    @Override
    public void atacar() {
        tocarSomAtaque(ataqueClip);
        this.atacando = !this.atacando;
        this.icone = this.carregarImagem(this.nomeImagemBase + (atacando ? "2" : ""));
        System.out.println("Aldeão atacando!");
    }

    @Override
    public void coletarMadeira() {
        System.out.println("Aldeao coletando madeira...");
    }

    @Override
    public void coletarOuro() {
        System.out.println("Aldeao coletando ouro...");
    }

    @Override
    public void aumentar_baixas() {
        contagem_baixas++;
        System.out.printf("Número de baixas de aldeões: %d\n", contagem_baixas);
    }
}