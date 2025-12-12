package ifsc.joe.domain;

import ifsc.joe.enums.Direcao;
import ifsc.joe.utils.Config;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class Personagem {
    protected int posX, posY;
    protected Image icone;
    protected boolean atacando;
    protected String nomeImagemBase;
    protected String somAtaque;

    protected int vida;
    protected int vidaMaxima;
    protected int ataque;
    protected int alcance;
    protected int velocidade;
    protected double chanceEsquiva;
    protected String textoFlutuante = "";
    protected int tempoTexto = 0;

    protected float opacidade = 1.0f;
    protected float taxaFade = Config.TAXA_FADE_OUT;

    abstract public void aumentar_baixas();

    public Personagem(int x, int y, String nomeImagemBase, int vida, int ataque, int alcance, String somAtaque) {
        this.posX = x;
        this.posY = y;
        this.nomeImagemBase = nomeImagemBase;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.alcance = alcance;
        this.atacando = false;
        this.icone = carregarImagem(nomeImagemBase);
        this.velocidade = Config.VELOCIDADE_PADRAO;
        this.somAtaque = somAtaque;
    }

    public void desenhar(Graphics g, JPanel painel) {
        if (opacidade <= 0) return;

        Graphics2D g2d = (Graphics2D) g;
        Composite compositeOriginal = g2d.getComposite();

        // Lógica de Morte
        if (this.vida <= 0) {
            opacidade -= taxaFade;
            if (opacidade < 0) opacidade = 0;

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidade));
        }

        g.drawImage(this.icone, this.posX, this.posY, painel);

        if (opacidade > 0.8f) {
            desenharBarraVida(g);
        }

        if (this.atacando && this.vida > 0) {
            desenharAlcance(g);
        }

        if (this.tempoTexto > 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 14));

            int deslocamentoY = (40 - this.tempoTexto) / 2;

            g.drawString(this.textoFlutuante, this.posX, this.posY - deslocamentoY);

            this.tempoTexto--;
        }

        g2d.setComposite(compositeOriginal);
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
        int alturaBarra = Config.BARRA_VIDA_ALTURA;
        int yBarra = this.posY - Config.BARRA_VIDA_OFFSET;

        g.setColor(Color.BLACK);
        g.fillRect(this.posX, yBarra, larguraBarra, alturaBarra);

        if (this.vida > 0) {
            double porcentagem = (double) this.vida / this.vidaMaxima;
            int larguraVida = (int) (porcentagem * larguraBarra);

            if (porcentagem > 0.74) {
                g.setColor(Color.GREEN);
            } else if (porcentagem > 0.24) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.RED);
            }

            g.fillRect(this.posX, yBarra, larguraVida, alturaBarra);
        }

        g.setColor(Color.BLACK);
        g.drawRect(this.posX, yBarra, larguraBarra, alturaBarra);
    }

    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        if (this.vida <= 0) return;

        int passo = this.velocidade;

        switch (direcao) {
            case CIMA -> this.posY -= passo;
            case BAIXO -> this.posY += passo;
            case ESQUERDA -> this.posX -= passo;
            case DIREITA -> this.posX += passo;
        }

        if (this.icone != null) {
            this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
            this.posY = Math.min(Math.max(0, this.posY), maxAltura - this.icone.getHeight(null));
        }
    }

    public void sofrerDano(int dano) {
        if (this.vida <= 0) return;

        if (Math.random() < this.chanceEsquiva) {
            this.textoFlutuante = "ESQUIVOU!";
            this.tempoTexto = 40;
            System.out.println("ESQUIVOU! " + this.getClass().getSimpleName());
            return;
        }

        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;

        System.out.println(this.getClass().getSimpleName() + " sofreu " + dano);
    }

    public boolean isRemovivel() {
        return this.vida <= 0 && this.opacidade <= 0;
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
        String caminho = imagem + ".png";
        try {
            return new ImageIcon(Objects.requireNonNull(
                    getClass().getClassLoader().getResource(caminho)
            )).getImage();
        } catch (Exception e) {
            System.err.println("ERRO: Imagem não encontrada: " + caminho);

            BufferedImage placeholder = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            Graphics g = placeholder.getGraphics();
            g.setColor(Color.MAGENTA); // Cor chamativa para saber que deu erro
            g.fillRect(0, 0, 32, 32);
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, 31, 31);
            g.dispose();
            return placeholder;
        }
    }

    protected void tocarSomAtaque() {
        try {
            URL url = getClass().getClassLoader().getResource(this.somAtaque);

            if (url == null) {
                System.err.println("Sound not found: " + this.somAtaque);
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

            // Get a Clip resource
            Clip clip = AudioSystem.getClip();

            // Open the audio stream and start playing
            clip.open(audioInputStream);
            clip.start(); // Plays the sound once
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}