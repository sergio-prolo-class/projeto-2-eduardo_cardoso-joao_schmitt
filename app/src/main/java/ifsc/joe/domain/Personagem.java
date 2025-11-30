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

    // atributos de combate
    protected int vida;
    protected int ataque;
    protected double velocidade;

    public Personagem(int x, int y, String nomeImagemBase, int vida, int ataque) {
        this.posX = x;
        this.posY = y;
        this.nomeImagemBase = nomeImagemBase;
        this.vida = vida;
        this.ataque = ataque;
        this.atacando = false;
        this.icone = carregarImagem(nomeImagemBase);
    }

    public void desenhar(Graphics g, JPanel painel) {
        if (this.vida > 0) { // Só desenha se estiver vivo
            g.drawImage(this.icone, this.posX, this.posY, painel);
        }
    }

    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        if (this.vida <= 0) return; // Morto não anda

        switch (direcao) {
            case CIMA     -> this.posY -= 10;
            case BAIXO    -> this.posY += 10;
            case ESQUERDA -> this.posX -= 10;
            case DIREITA  -> this.posX += 10;
        }

        if (this.icone != null) {
            this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
            this.posY = Math.min(Math.max(0, this.posY), maxAltura - this.icone.getHeight(null));
        }
    }

    // Metodo para receber dano
    public void sofrerDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;
        System.out.println(this.getClass().getSimpleName() + " sofreu " + dano + " de dano. Vida restante: " + this.vida);
    }

    // Metodo auxiliar para calcular distância
    public double distanciaPara(Personagem outro) {
        return Math.sqrt(Math.pow(this.posX - outro.posX, 2) + Math.pow(this.posY - outro.posY, 2));
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVida() {
        return vida;
    }

    protected Image carregarImagem(String imagem) {
        return new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("./" + imagem + ".png")
        )).getImage();
    }
}