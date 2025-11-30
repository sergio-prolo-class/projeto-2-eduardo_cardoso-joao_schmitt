package ifsc.joe.domain.impl;

import ifsc.joe.domain.Guerreiro;
import ifsc.joe.domain.Personagem;

public class Arqueiro extends Personagem implements Guerreiro {

    public Arqueiro(int x, int y) {
        super(x, y, "arqueiro");
    }

    @Override
    public void atacar() {
        this.atacando = !this.atacando;
        System.out.println("Arqueiro disparando flechas!");
    }
}