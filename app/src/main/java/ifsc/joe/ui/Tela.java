package ifsc.joe.ui;

import ifsc.joe.domain.Guerreiro;
import ifsc.joe.domain.Personagem;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Tela extends JPanel {

    private final Set<Personagem> personagens;
    private static final double ALCANCE_ATAQUE = 50.0;

    public Tela() {
        this.setBackground(Color.white);
        this.personagens = new HashSet<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Remove mortos da visualização e memória
        personagens.removeIf(p -> p.getVida() <= 0);

        this.personagens.forEach(p -> p.desenhar(g, this));
        g.dispose();
    }

    public void adicionarPersonagem(Personagem p) {
        p.desenhar(super.getGraphics(), this);
        this.personagens.add(p);
    }

    public void movimentarPersonagens(Direcao direcao, Class<?> tipoFiltro) {
        this.personagens.stream()
                .filter(p -> tipoFiltro == null || tipoFiltro.isInstance(p))
                .forEach(p -> p.mover(direcao, this.getWidth(), this.getHeight()));

        this.repaint();
    }

    public void atacarComGuerreiros(Class<?> tipoFiltro) {
        // Filtra quem vai atacar
        Set<Personagem> atacantes = this.personagens.stream()
                .filter(p -> p instanceof Guerreiro)
                .filter(p -> tipoFiltro == null || tipoFiltro.isInstance(p))
                .collect(Collectors.toSet());

        // Para cada atacante, encontra alvos próximos e causa dano
        for (Personagem atacante : atacantes) {
            ((Guerreiro) atacante).atacar(); // Efeito visual/estado

            for (Personagem alvo : this.personagens) {
                if (atacante != alvo && atacante.distanciaPara(alvo) <= ALCANCE_ATAQUE) {
                    alvo.sofrerDano(atacante.getAtaque());
                }
            }
        }

        this.repaint();
    }
}