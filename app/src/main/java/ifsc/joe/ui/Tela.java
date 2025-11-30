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

    public void adicionarPersonagem(Personagem p) {
        p.desenhar(super.getGraphics(), this);
        this.personagens.add(p);
    }

    /**
     * Movimenta os personagens, aplicando o filtro de tipo selecionado.
     * @param direcao Direção do movimento.
     * @param tipoFiltro Classe do tipo a ser movido (ex: Aldeao.class) ou null para todos.
     */
    public void movimentarPersonagens(Direcao direcao, Class<?> tipoFiltro) {
        this.personagens.stream()
                // Se tipoFiltro for null (Todos), passa tudo. Senão, verifica se o personagem é do tipo.
                .filter(p -> tipoFiltro == null || tipoFiltro.isInstance(p))
                .forEach(p -> p.mover(direcao, this.getWidth(), this.getHeight()));

        this.repaint();
    }

    /**
     * Ordena ataque para os Guerreiros, respeitando o filtro de tipo.
     * @param tipoFiltro Classe do tipo a atacar ou null para todos.
     */
    public void atacarComGuerreiros(Class<?> tipoFiltro) {
        this.personagens.stream()
                .filter(p -> p instanceof Guerreiro) // Apenas guerreiros podem atacar
                .filter(p -> tipoFiltro == null || tipoFiltro.isInstance(p)) // Aplica o filtro da interface
                .forEach(p -> ((Guerreiro) p).atacar());

        this.repaint();
    }
}