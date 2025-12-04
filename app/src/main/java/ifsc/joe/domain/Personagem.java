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

    protected int vida;
    protected int vidaMaxima;
    protected int ataque;
    protected double velocidade;

    public Personagem(int x, int y, String nomeImagemBase, int vida, int ataque) {
        this.posX = x;
        this.posY = y;
        this.nomeImagemBase = nomeImagemBase;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.atacando = false;
        this.icone = carregarImagem(nomeImagemBase);
    }

    public void desenhar(Graphics g, JPanel painel) {
        if (this.vida <= 0) return;

        g.drawImage(this.icone, this.posX, this.posY, painel);
        desenharBarraVida(g);
    }

    private void desenharBarraVida(Graphics g) {
        int larguraBarra = this.icone.getWidth(null);
        int alturaBarra = 5;
        int yBarra = this.posY - 10;

        // Fundo da barra (Vermelho - Vida perdida)
        g.setColor(Color.RED);
        g.fillRect(this.posX, yBarra, larguraBarra, alturaBarra);

        // Frente da barra (Verde - Vida atual)
        if (this.vida > 0) {
            int larguraVerde = (int) ((double) this.vida / this.vidaMaxima * larguraBarra);
            g.setColor(Color.GREEN);
            g.fillRect(this.posX, yBarra, larguraVerde, alturaBarra);
        }

        // Borda preta para melhor visualização
        g.setColor(Color.BLACK);
        g.drawRect(this.posX, yBarra, larguraBarra, alturaBarra);
    }

    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        if (this.vida <= 0) return;

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

    public void sofrerDano(int dano) {
        if (this.vida <= 0) return;

        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;

        System.out.println(this.getClass().getSimpleName() + " sofreu " + dano + " de dano. Vida: " + this.vida + "/" + this.vidaMaxima);
    }

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
        try {
            return new ImageIcon(Objects.requireNonNull(
                    getClass().getClassLoader().getResource("./" + imagem + ".png")
            )).getImage();
        } catch (NullPointerException e) {
            System.err.println("Imagem não encontrada: " + imagem);
            return null;
        }
    }
}