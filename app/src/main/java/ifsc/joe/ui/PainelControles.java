package ifsc.joe.ui;

import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.enums.Direcao;
import ifsc.joe.utils.Config;

import javax.swing.*;
import java.util.Random;

public class PainelControles {
    private final Random sorteio;
    private Tela tela;

    private JPanel painelPrincipal;
    private JPanel painelTela;
    private JPanel painelLateral;
    private JButton bCriaAldeao;
    private JButton bCriaArqueiro;
    private JButton bCriaCavaleiro;
    private JRadioButton todosRadioButton;
    private JRadioButton aldeaoRadioButton;
    private JRadioButton arqueiroRadioButton;
    private JRadioButton cavaleiroRadioButton;
    private JButton atacarButton;
    private JButton buttonCima;
    private JButton buttonEsquerda;
    private JButton buttonBaixo;
    private JButton buttonDireita;
    private JLabel logo;
    private JPanel Contador;
    private JLabel contagem_aldeao;
    private JLabel contagem_arqueiro;
    private JLabel contagem_cavaleiro;

    public PainelControles() {
        this.sorteio = new Random();
        configurarListeners();
    }

    private void configurarListeners() {
        configurarBotoesMovimento();
        configurarBotoesCriacao();
        configurarBotaoAtaque();
    }

    private Class<?> getTipoSelecionado() {
        if (aldeaoRadioButton.isSelected()) return Aldeao.class;
        if (arqueiroRadioButton.isSelected()) return Arqueiro.class;
        if (cavaleiroRadioButton.isSelected()) return Cavaleiro.class;
        return null; // "Todos"
    }

    private void configurarBotoesMovimento() {
        buttonCima.addActionListener(e -> getTela().movimentarPersonagens(Direcao.CIMA, getTipoSelecionado()));
        buttonBaixo.addActionListener(e -> getTela().movimentarPersonagens(Direcao.BAIXO, getTipoSelecionado()));
        buttonEsquerda.addActionListener(e -> getTela().movimentarPersonagens(Direcao.ESQUERDA, getTipoSelecionado()));
        buttonDireita.addActionListener(e -> getTela().movimentarPersonagens(Direcao.DIREITA, getTipoSelecionado()));
    }

    private void configurarBotoesCriacao() {
        bCriaAldeao.addActionListener(e -> {
            int[] pos = sortearPosicao();
            getTela().adicionarPersonagem(new Aldeao(pos[0], pos[1]));
        });

        bCriaArqueiro.addActionListener(e -> {
            int[] pos = sortearPosicao();
            getTela().adicionarPersonagem(new Arqueiro(pos[0], pos[1]));
        });

        bCriaCavaleiro.addActionListener(e -> {
            int[] pos = sortearPosicao();
            getTela().adicionarPersonagem(new Cavaleiro(pos[0], pos[1]));
        });
    }

    private void configurarBotaoAtaque() {
        atacarButton.addActionListener(e -> getTela().atacarComGuerreiros(getTipoSelecionado()));
    }

    private int[] sortearPosicao() {
        int posX = sorteio.nextInt(Math.max(1, painelTela.getWidth() - Config.PADDING_BORDAS));
        int posY = sorteio.nextInt(Math.max(1, painelTela.getHeight() - Config.PADDING_BORDAS));
        return new int[]{posX, posY};
    }

    private Tela getTela() {
        if (tela == null) {
            tela = (Tela) painelTela;
            tela.setPainelControles(this);
        }
        return tela;
    }

    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }

    private void createUIComponents() {
        this.painelTela = new Tela();
    }

    public void atualizarContadoresBaixas() {
        contagem_aldeao.setText(String.valueOf(Aldeao.getContagemBaixas()));
        contagem_arqueiro.setText(String.valueOf(Arqueiro.getContagemBaixas()));
        contagem_cavaleiro.setText(String.valueOf(Cavaleiro.getContagemBaixas()));
    }
}