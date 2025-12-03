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

        // 1. Remove personagens que já morreram E terminaram a animação
        boolean alguemRemovido = personagens.removeIf(p -> {
            if (p.estaMortoCompletamente()) {
                System.out.println("Baixa confirmada: " + p.getClass().getSimpleName() + " removido do jogo.");
                return true;
            }
            return false;
        });

        // 2. Desenha todos (vivos e os que estão morrendo/desaparecendo)
        this.personagens.forEach(p -> p.desenhar(g, this));

        // 3. Se houver alguém morrendo (vida 0, mas ainda visível), força repaint para animar o fade-out
        boolean haAnimacaoPendente = personagens.stream().anyMatch(p -> p.getVida() <= 0);
        
        if (haAnimacaoPendente) {
            try {
                Thread.sleep(50); // Pequeno delay para controlar a velocidade da animação
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint(); // Chama o paint novamente
        }

        g.dispose();
    }

    public void adicionarPersonagem(Personagem p) {
        p.desenhar(super.getGraphics(), this);
        this.personagens.add(p);
    }

    public void movimentarPersonagens(Direcao direcao, Class<?> tipoFiltro) {
        this.personagens.stream()
                .filter(p -> p.getVida() > 0) // Mortos não se movem
                .filter(p -> tipoFiltro == null || tipoFiltro.isInstance(p))
                .forEach(p -> p.mover(direcao, this.getWidth(), this.getHeight()));

        this.repaint();
    }

    public void atacarComGuerreiros(Class<?> tipoFiltro) {
        Set<Personagem> atacantes = this.personagens.stream()
                .filter(p -> p.getVida() > 0) // Mortos não atacam
                .filter(p -> p instanceof Guerreiro)
                .filter(p -> tipoFiltro == null || tipoFiltro.isInstance(p))
                .collect(Collectors.toSet());

        for (Personagem atacante : atacantes) {
            ((Guerreiro) atacante).atacar();

            for (Personagem alvo : this.personagens) {
                // Não ataca a si mesmo, nem mortos, e deve estar no alcance
                if (atacante != alvo && alvo.getVida() > 0 && atacante.distanciaPara(alvo) <= ALCANCE_ATAQUE) {
                    alvo.sofrerDano(atacante.getAtaque());
                }
            }
        }
        this.repaint();
    }
}