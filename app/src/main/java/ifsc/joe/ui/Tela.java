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

    public Tela() {
        this.setBackground(Color.white);
        this.personagens = new HashSet<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        personagens.removeIf(p -> {
            if (p.getVida() <= 0) {
                System.out.println("☠️ Baixa confirmada: " + p.getClass().getSimpleName() + " eliminado.");
                return true;
            }
            return false;
        });

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
        Set<Personagem> atacantes = this.personagens.stream()
                .filter(p -> p instanceof Guerreiro)
                .filter(p -> tipoFiltro == null || tipoFiltro.isInstance(p))
                .collect(Collectors.toSet());

        for (Personagem atacante : atacantes) {
            ((Guerreiro) atacante).atacar();

            for (Personagem alvo : this.personagens) {
                // Usa o alcance do atacante
                if (atacante != alvo && atacante.distanciaPara(alvo) <= atacante.getAlcance()) {
                    alvo.sofrerDano(atacante.getAtaque());
                }
            }
        }
        this.repaint();
    }
}