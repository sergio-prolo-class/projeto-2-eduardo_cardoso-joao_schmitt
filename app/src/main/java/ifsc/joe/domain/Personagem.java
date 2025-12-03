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
    
    // Controle de animação de morte
    protected float opacidade = 1.0f;

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
        if (this.vida <= 0) {
            // Lógica de "Fade Out" (Desaparecimento)
            opacidade -= 0.05f; // Velocidade do desaparecimento
            if (opacidade < 0) opacidade = 0;

            Graphics2D g2d = (Graphics2D) g;
            // Configura transparência
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidade));
            g.drawImage(this.icone, this.posX, this.posY, painel);
            // Restaura transparência para os próximos desenhos
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        } else {
            // Desenho normal
            g.drawImage(this.icone, this.posX, this.posY, painel);
        }
    }

    public boolean estaMortoCompletamente() {
        return this.vida <= 0 && this.opacidade <= 0;
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

    public void sofrerDano(int dano) {
        if (this.vida <= 0) return; // Já está morto

        this.vida -= dano;
        if (this.vida <= 0) {
            this.vida = 0;
            System.out.println(this.getClass().getSimpleName() + " morreu! Iniciando efeito visual.");
        } else {
            System.out.println(this.getClass().getSimpleName() + " sofreu " + dano + " de dano. Vida restante: " + this.vida);
        }
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