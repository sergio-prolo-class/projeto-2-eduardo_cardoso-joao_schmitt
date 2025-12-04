package ifsc.joe.domain;

import ifsc.joe.enums.Direcao;
import ifsc.joe.utils.Config; // Importando as configurações

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
    protected int alcance;
    protected double velocidade;

    public Personagem(int x, int y, String nomeImagemBase, int vida, int ataque, int alcance) {
        this.posX = x;
        this.posY = y;
        this.nomeImagemBase = nomeImagemBase;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.alcance = alcance;
        this.atacando = false;
        this.icone = carregarImagem(nomeImagemBase);
    }

    public void desenhar(Graphics g, JPanel painel) {
        if (this.vida <= 0) return;

        g.drawImage(this.icone, this.posX, this.posY, painel);
        desenharBarraVida(g);

        if (this.atacando) {
            desenharAlcance(g);
        }
    }

    private void desenharAlcance(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));

        int raio = this.alcance;
        int centroX = this.posX + (this.icone.getWidth(null) / 2);
        int centroY = this.posY + (this.icone.getHeight(null) / 2);

        g2d.drawOval(centroX - raio, centroY - raio, raio * 2, raio * 2);
    }

    private void desenharBarraVida(Graphics g) {
        int larguraBarra = this.icone.getWidth(null);
        // Uso das constantes visuais
        int alturaBarra = Config.BARRA_VIDA_ALTURA;
        int yBarra = this.posY - Config.BARRA_VIDA_OFFSET;

        g.setColor(Color.RED);
        g.fillRect(this.posX, yBarra, larguraBarra, alturaBarra);

        if (this.vida > 0) {
            int larguraVerde = (int) ((double) this.vida / this.vidaMaxima * larguraBarra);
            g.setColor(Color.GREEN);
            g.fillRect(this.posX, yBarra, larguraVerde, alturaBarra);
        }

        g.setColor(Color.BLACK);
        g.drawRect(this.posX, yBarra, larguraBarra, alturaBarra);
    }

    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        if (this.vida <= 0) return;

        // Uso da constante de velocidade
        int passo = Config.VELOCIDADE_PADRAO;

        switch (direcao) {
            case CIMA     -> this.posY -= passo;
            case BAIXO    -> this.posY += passo;
            case ESQUERDA -> this.posX -= passo;
            case DIREITA  -> this.posX += passo;
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
        int centroX = this.posX + (this.icone.getWidth(null) / 2);
        int centroY = this.posY + (this.icone.getHeight(null) / 2);
        int outroCentroX = outro.posX + (outro.icone.getWidth(null) / 2);
        int outroCentroY = outro.posY + (outro.icone.getHeight(null) / 2);

        return Math.sqrt(Math.pow(centroX - outroCentroX, 2) + Math.pow(centroY - outroCentroY, 2));
    }

    public int getAtaque() {
        return ataque;
    }

    public int getAlcance() {
        return alcance;
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