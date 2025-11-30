package ifsc.joe.domain.impl;

import ifsc.joe.domain.Coletador;
import ifsc.joe.domain.Guerreiro;
import ifsc.joe.domain.Personagem;

public class Aldeao extends Personagem implements Guerreiro, Coletador {

    public Aldeao(int x, int y) {
        super(x, y, "aldeao", 25, 1);
    }

    @Override
    public void atacar() {
        this.atacando = !this.atacando;
        this.icone = this.carregarImagem(this.nomeImagemBase + (atacando ? "2" : ""));
    }

    @Override
    public void coletarMadeira() {
        System.out.println("Aldeao coletando madeira...");
    }

    @Override
    public void coletarOuro() {
        System.out.println("Aldeao coletando ouro...");
    }
}