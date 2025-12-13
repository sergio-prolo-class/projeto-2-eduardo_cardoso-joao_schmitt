package ifsc.joe.domain.impl;

import ifsc.joe.domain.ComMontaria;
import ifsc.joe.domain.Guerreiro;
import ifsc.joe.domain.Personagem;
import ifsc.joe.utils.Config;


public class Cavaleiro extends Personagem implements Guerreiro, ComMontaria {
    private static int contagem_baixas;
    private boolean estaMontado;

    public static int getContagemBaixas() {
        return contagem_baixas;
    }

    public Cavaleiro(int x, int y) {
        super(x, y, "cavaleiro", Config.CAVALEIRO_VIDA, Config.CAVALEIRO_ATAQUE, Config.CAVALEIRO_ALCANCE, "espada.wav");
        this.estaMontado = true;
        this.velocidade = this.velocidade * 4;
        this.chanceEsquiva = Config.CAVALEIRO_ESQUIVA;
    }

    public boolean isEstaMontado() {
        return estaMontado;
    }

    @Override
    public void atacar() {
        this.atacando = !this.atacando;
        this.icone = this.carregarImagem(this.nomeImagemBase + (atacando ? "2" : ""));
        System.out.println("Cavaleiro atacando!");
    }

    @Override
    public void alternarMontaria() {
        this.estaMontado = !this.estaMontado;
        this.nomeImagemBase = (estaMontado ? "cavaleiro" : "guerreiro");
        this.velocidade = estaMontado ? this.velocidade * 4 : Config.VELOCIDADE_PADRAO;
        this.icone = this.carregarImagem(this.nomeImagemBase + (atacando ? "2" : ""));
    }

    @Override
    public void aumentar_baixas() {
        contagem_baixas++;
        System.out.printf("Número de baixas de cavaleiros: %d\n", contagem_baixas);
    }
}