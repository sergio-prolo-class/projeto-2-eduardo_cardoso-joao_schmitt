package ifsc.joe.ui;

import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.PersonagemFactory;
import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.enums.Direcao;
import ifsc.joe.enums.TipoPersonagem;
import ifsc.joe.utils.Config;

import javax.swing.*;
import java.awt.event.KeyEvent;
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
    private JButton montariaButton;

    public PainelControles() {
        this.sorteio = new Random();
        configurarListeners();
        configurarAtalhosTeclado();
        desabilitarFocoElementos();
    }

    // Necessario porque sem isso ao apertar TAB, os elementos da interface vão sendo percorridos e atrapalham os outros atalhos
    private void desabilitarFocoElementos() {
        bCriaAldeao.setFocusable(false);
        bCriaArqueiro.setFocusable(false);
        bCriaCavaleiro.setFocusable(false);
        todosRadioButton.setFocusable(false);
        aldeaoRadioButton.setFocusable(false);
        arqueiroRadioButton.setFocusable(false);
        cavaleiroRadioButton.setFocusable(false);
        atacarButton.setFocusable(false);
        buttonCima.setFocusable(false);
        buttonEsquerda.setFocusable(false);
        buttonBaixo.setFocusable(false);
        buttonDireita.setFocusable(false);
    }

    private void configurarAtalhosTeclado() {
        getTela(); // Garante que tela nao seja null

        InputMap inputMap = painelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = painelPrincipal.getActionMap();

        // Movimentacao
        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        actionMap.put("moveUp", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                buttonCima.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        actionMap.put("moveLeft", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                buttonEsquerda.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        actionMap.put("moveDown", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                buttonBaixo.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        actionMap.put("moveRight", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                buttonDireita.doClick();
            }
        });

        // Criar personagens
        inputMap.put(KeyStroke.getKeyStroke("1"), "criaAldeao");
        actionMap.put("criaAldeao", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                bCriaAldeao.doClick();
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("2"), "criaArqueiro");
        actionMap.put("criaArqueiro", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                bCriaArqueiro.doClick();
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("3"), "criaCavaleiro");
        actionMap.put("criaCavaleiro", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                bCriaCavaleiro.doClick();
            }
        });

        // Atacar
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "atacar");
        actionMap.put("atacar", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                atacarButton.doClick();
            }
        });

        // Alternar filtro de tipo (Tab)
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "alternarFiltro");
        actionMap.put("alternarFiltro", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                alternarFiltros();
            }
        });

        // Montar/Desmontar (M)
        inputMap.put(KeyStroke.getKeyStroke("M"), "montarDesmontar");
        actionMap.put("montarDesmontar", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                montariaButton.doClick();
            }
        });
    }


    private void alternarFiltros() {
        if (todosRadioButton.isSelected()) {
            aldeaoRadioButton.setSelected(true);
            montariaButton.setEnabled(false);
        } else if (aldeaoRadioButton.isSelected()) {
            arqueiroRadioButton.setSelected(true);
            montariaButton.setEnabled(false);
        } else if (arqueiroRadioButton.isSelected()) {
            cavaleiroRadioButton.setSelected(true);
            montariaButton.setEnabled(true);
        } else {
            todosRadioButton.setSelected(true);
            montariaButton.setEnabled(true);
        }
    }

    private void configurarListeners() {
        configurarBotoesMovimento();
        configurarBotoesCriacao();
        configurarBotaoAtaque();
        configurarBotaoMontaria();
    }

    private Class<?> getTipoSelecionado() {
        montariaButton.setEnabled(false);

        if (aldeaoRadioButton.isSelected()) return Aldeao.class;
        if (arqueiroRadioButton.isSelected()) return Arqueiro.class;

        montariaButton.setEnabled(true);
        if (cavaleiroRadioButton.isSelected()) return Cavaleiro.class;

        return null; // "Todos"
    }

    private void configurarBotoesMovimento() {
        buttonCima.addActionListener(e -> getTela().movimentarPersonagens(Direcao.CIMA, getTipoSelecionado()));
        buttonBaixo.addActionListener(e -> getTela().movimentarPersonagens(Direcao.BAIXO, getTipoSelecionado()));
        buttonEsquerda.addActionListener(e -> getTela().movimentarPersonagens(Direcao.ESQUERDA, getTipoSelecionado()));
        buttonDireita.addActionListener(e -> getTela().movimentarPersonagens(Direcao.DIREITA, getTipoSelecionado()));
    }

    // Metodo auxiliar para usar a Factory e evitar repetição de código
    private void criarPersonagem(TipoPersonagem tipo) {
        int[] pos = sortearPosicao();
        Personagem p = PersonagemFactory.criar(tipo, pos[0], pos[1]);
        getTela().adicionarPersonagem(p);
    }

    private void configurarBotoesCriacao() {
        todosRadioButton.addActionListener(e -> getTipoSelecionado());
        aldeaoRadioButton.addActionListener(e -> getTipoSelecionado());
        arqueiroRadioButton.addActionListener(e -> getTipoSelecionado());
        cavaleiroRadioButton.addActionListener(e -> getTipoSelecionado());
        montariaButton.addActionListener(e -> getTipoSelecionado());

        bCriaAldeao.addActionListener(e -> criarPersonagem(TipoPersonagem.ALDEAO));
        bCriaArqueiro.addActionListener(e -> criarPersonagem(TipoPersonagem.ARQUEIRO));
        bCriaCavaleiro.addActionListener(e -> criarPersonagem(TipoPersonagem.CAVALEIRO));
    }

    private void configurarBotaoAtaque() {
        atacarButton.addActionListener(e -> getTela().atacarComGuerreiros(getTipoSelecionado()));
    }

    private void configurarBotaoMontaria() {
        montariaButton.addActionListener(e -> getTela().alternarMontaria());
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
            tela.setFocusable(true);
            tela.setFocusTraversalKeysEnabled(false);
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