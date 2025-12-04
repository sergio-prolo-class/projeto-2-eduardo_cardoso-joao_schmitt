package ifsc.joe.domain.impl;

import ifsc.joe.domain.ComMontaria;
import ifsc.joe.domain.Guerreiro;
import ifsc.joe.domain.Personagem;

public class Cavaleiro extends Personagem implements Guerreiro, ComMontaria {

    public Cavaleiro(int x, int y) {
        super(x, y, "cavaleiro", 50, 3, 75);
    }

    @Override
    public void atacar() {
        this.atacando = !this.atacando;
        this.icone = this.carregarImagem(this.nomeImagemBase + (atacando ? "2" : ""));
        System.out.println("Cavaleiro atacando!");
    }

    @Override
    public void montar() {
        System.out.println("Cavaleiro montou no cavalo.");
    }
}