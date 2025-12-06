package ifsc.joe.domain.impl;

import ifsc.joe.domain.Coletador;
import ifsc.joe.domain.Personagem;
import ifsc.joe.utils.Config;

public class Aldeao extends Personagem implements Coletador {
    private static int contagem_baixas;

    public static int getContagemBaixas() {
        return contagem_baixas;
    }

    public Aldeao(int x, int y) {
        super(x, y, "aldeao", Config.ALDEAO_VIDA, Config.ALDEAO_ATAQUE, Config.ALDEAO_ALCANCE);
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