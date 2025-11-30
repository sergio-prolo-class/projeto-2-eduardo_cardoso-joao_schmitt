package ifsc.joe.domain.impl;

import ifsc.joe.domain.Coletador;
import ifsc.joe.domain.Guerreiro;
import ifsc.joe.domain.Personagem;

import javax.swing.*;
import java.awt.*;

public class Aldeao extends Personagem implements Guerreiro, Coletador {

    public Aldeao(int x, int y) {
        super(x, y, "aldeao");
    }

    @Override
    public void atacar() {
        this.atacando = !this.atacando;
        // Lógica específica visual do aldeão que muda de imagem ao atacar
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