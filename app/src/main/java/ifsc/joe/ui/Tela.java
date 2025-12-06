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
    private PainelControles painelControles;

    private final Set<Personagem> personagens;
    private final Timer gameLoop;

    public Tela() {
        this.setBackground(Color.white);
        this.personagens = new HashSet<>();

        this.gameLoop = new Timer(16, e -> {
            repaint();
        });
        this.gameLoop.start();
    }

    public void setPainelControles(PainelControles painelControles) {
        this.painelControles = painelControles;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Remove da lista apenas se estiver morto E totalmente transparente
        boolean removido = personagens.removeIf(p -> {
            if (p.isRemovivel()) {
                System.out.println("Baixa confirmada: " + p.getClass().getSimpleName() + " eliminado.");
                p.aumentar_baixas();
                return true;
            }
            return false;
        });
        if (removido && painelControles != null) {
            painelControles.atualizarContadoresBaixas();
        }

        // Desenha todos os personagens (os mortos desenham-se com transparência até sumirem)
        this.personagens.forEach(p -> p.desenhar(g, this));

        g.dispose();
    }

    public void adicionarPersonagem(Personagem p) {
        this.personagens.add(p);
    }

    public void movimentarPersonagens(Direcao direcao, Class<?> tipoFiltro) {
        this.personagens.stream()
                .filter(p -> tipoFiltro == null || tipoFiltro.isInstance(p))
                .forEach(p -> p.mover(direcao, this.getWidth(), this.getHeight()));
    }

    public void atacarComGuerreiros(Class<?> tipoFiltro) {
        Set<Personagem> atacantes = this.personagens.stream()
                .filter(p -> p instanceof Guerreiro)
                .filter(p -> tipoFiltro == null || tipoFiltro.isInstance(p))
                .collect(Collectors.toSet());

        for (Personagem atacante : atacantes) {
            ((Guerreiro) atacante).atacar();

            for (Personagem alvo : this.personagens) {
                // Não ataca a si mesmo e respeita o alcance
                if (atacante != alvo && atacante.distanciaPara(alvo) <= atacante.getAlcance()) {
                    alvo.sofrerDano(atacante.getAtaque());
                }
            }
        }
    }
}