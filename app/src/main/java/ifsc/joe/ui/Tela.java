package ifsc.joe.ui;

import ifsc.joe.domain.Guerreiro;
import ifsc.joe.domain.Personagem;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Tela extends JPanel {

    private final Set<Personagem> personagens;

    public Tela() {
        this.setBackground(Color.white);
        this.personagens = new HashSet<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.personagens.forEach(p -> p.desenhar(g, this));
        g.dispose();
    }

    /**
     * Adiciona qualquer tipo de personagem à tela
     */
    public void adicionarPersonagem(Personagem p) {
        p.desenhar(super.getGraphics(), this);
        this.personagens.add(p);
    }

    public void movimentarPersonagens(Direcao direcao) {
        // Mover() funciona para todos
        this.personagens.forEach(p -> p.mover(direcao, this.getWidth(), this.getHeight()));
        this.repaint();
    }

    public void atacarComGuerreiros() {
        for (Personagem p : this.personagens) {
            // Verifica se o personagem implementa a interface Guerreiro (instanceof)
            if (p instanceof Guerreiro) {
                ((Guerreiro) p).atacar();
            }
        }
        this.repaint();
    }
}