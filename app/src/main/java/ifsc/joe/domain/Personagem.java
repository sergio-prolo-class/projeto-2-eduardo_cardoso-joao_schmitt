package ifsc.joe.domain;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class Personagem {
    protected int posX, posY;
    protected Image icone;
    protected boolean atacando;
    protected String nomeImagemBase;

    public Personagem(int x, int y, String nomeImagemBase) {
        this.posX = x;
        this.posY = y;
        this.nomeImagemBase = nomeImagemBase;
        this.atacando = false;
        this.icone = carregarImagem(nomeImagemBase);
    }

    public void desenhar(Graphics g, JPanel painel) {
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }

    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        switch (direcao) {
            case CIMA     -> this.posY -= 10;
            case BAIXO    -> this.posY += 10;
            case ESQUERDA -> this.posX -= 10;
            case DIREITA  -> this.posX += 10;
        }

        // Garante que não saia da tela
        if (this.icone != null) {
            this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
            this.posY = Math.min(Math.max(0, this.posY), maxAltura - this.icone.getHeight(null));
        }
    }

    protected Image carregarImagem(String imagem) {
        return new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("./" + imagem + ".png")
        )).getImage();
    }
}