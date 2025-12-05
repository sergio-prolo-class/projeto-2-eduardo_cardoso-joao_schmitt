package ifsc.joe.domain.impl;

import ifsc.joe.domain.Guerreiro;
import ifsc.joe.domain.Personagem;
import ifsc.joe.utils.Config;

public class Arqueiro extends Personagem implements Guerreiro {
    private static int contagem_baixas;

    public Arqueiro(int x, int y) {
        super(x, y, "arqueiro", Config.ARQUEIRO_VIDA, Config.ARQUEIRO_ATAQUE, Config.ARQUEIRO_ALCANCE);
    }

    @Override
    public void atacar() {
        this.atacando = !this.atacando;
        this.icone = this.carregarImagem(this.nomeImagemBase + (atacando ? "2" : ""));
        System.out.println("Arqueiro disparando flechas!");
    }

    @Override
    public void aumentar_baixas() {
        contagem_baixas++;
        System.out.printf("Número de baixas de arqueiros: %d\n", contagem_baixas);
    }
}