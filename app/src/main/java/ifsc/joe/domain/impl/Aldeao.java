package ifsc.joe.domain.impl;

import ifsc.joe.domain.Coletador;
import ifsc.joe.domain.Personagem;

public class Aldeao extends Personagem implements Coletador {

    public Aldeao(int x, int y) {
        super(x, y, "aldeao", 25, 1);
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