# Projeto 02 - Java of Empires 🏰

**Disciplina:** Programação Orientada a Objetos - Análise e Desenvolvimento de Sistemas

**Professor:** Sergio Maurício Prolo Santos Junior

**Alunos:** Eduardo Cardoso Oliveira e Joao Vitor Schmitt

## 📜 Sobre o Projeto

O **Java of Empires** é um projeto prático inspirado em jogos de estratégia em tempo real (RTS). O objetivo é aplicar
conceitos fundamentais de Orientação a Objetos, como herança e polimorfismo, no desenvolvimento de um jogo interativo
com interface gráfica em Java (Swing).

O desenvolvimento segue uma **Metodologia Gamificada**, onde funcionalidades são desbloqueadas através de uma "Árvore de
Requisitos" disponibilizada pelo professor, permitindo escolhas de design e implementação de funcionalidades.

## 🎯 Objetivos

* **Aplicar herança e polimorfismo:** Hierarquia de classes de personagens.
* **Utilizar interfaces:** Implementação de comportamentos específicos (Guerreiro, Coletador, etc.).
* **Integrar interface gráfica:** Uso de Swing para interação visual.
* **Gerenciar escolhas de design:** Seleção de funcionalidades da árvore de requisitos.

---

## ✅ Funcionalidades Implementadas (Pontuação atingida: 48 pontos)

Abaixo estão listados os requisitos da Árvore de Habilidades que já foram concluídos no projeto:

### 🏗️ Base do Projeto (Requisito Obrigatório)

- [x] **Hierarquia de Classes:** Criação da classe abstrata `Personagem` e suas subclasses (`Aldeao`, `Arqueiro`,
  `Cavaleiro`).
- [x] **Interfaces:** Implementação de `Guerreiro`, `Coletador` e `ComMontaria`.
- [x] **Controles Básicos:** Botões para criar personagens de cada tipo.

### ⚔️ Sistema de Combate

- [x] **Ataque Básico (3 pontos):**
  - Personagens guerreiros podem atacar.
  - Animação de troca de sprites (modo ataque).
  - Dano calculado e subtraído da vida do alvo.

- [x] **Sistema de Morte (3 pontos):**
  - Remoção lógica e visual de personagens com vida <= 0.
  - Efeito de *fade-out* (transparência gradual) antes de sumir.
  - Contador de baixas por tipo de unidade.

- [x] **Alcance Variável (4 pontos):**
  - Definição de raios de ataque distintos por classe (Aldeão: 30px, Cavaleiro: 50px, Arqueiro: 150px).
  - Cálculo de distância preciso entre personagens.
  - Validação de alcance antes de aplicar dano.
  - **Indicador Visual:** Círculo exibido ao atacar.

- [x] **Esquiva (5 pontos) - NOVO:**
  - Chance de ignorar completamente o dano recebido.
  - Probabilidades: Aldeão (10%), Cavaleiro (15%), Arqueiro (25%).
  - **Feedback Visual:** Texto flutuante "ESQUIVOU!" aparece sobre o personagem.

### 🎮 Interface e Controles

- [x] **Filtro por Tipo (4 pontos):**
  - Seleção de unidades via Radio Buttons ou tecla `TAB`.
  - Comandos afetam apenas o grupo selecionado ("Todos", "Aldeão", etc.).

- [x] **Controle de Montaria (5 pontos):**
  - Cavaleiros podem alternar entre montado e desmontado (Tecla `M`).
  - Mudança de velocidade (4x mais rápido montado) e sprite.

- [x] **Atalhos de Teclado (6 pontos):**
  - `WASD` / Setas: Movimentação.
  - `1`, `2`, `3`: Criar unidades.
  - `Espaço`: Atacar.
  - `TAB`: Alterar filtros de tipo.

- [x] **Barra de Vida (4 pontos):**
  - Barras dinâmicas sobre cada personagem (Verde > Amarelo > Vermelho).

### 🏛️ Arquitetura de Software

- [x] **Arquivo de Configurações (3 pontos):**
  - Centralização de constantes (vida, ataque, chances de esquiva) na classe `Config`.

- [x] **Fábrica de Personagens (6 pontos):**
  - Implementação do padrão de projeto **Factory** para centralizar a criação de unidades.
  - **Enumeração Segura**: Uso de `TipoPersonagem` para evitar strings mágicas.
  - **Switch Expression**: Instanciação moderna e concisa usando a nova sintaxe do Java.
  - **Refatoração da UI**: Código limpo no painel de controles, eliminando duplicação lógica.

- [x] **Tratamento de Erros:** Sistema robusto de carregamento de imagens (previne fechamento do jogo se faltar sprite).

---

## 🚀 Como Executar o programa

### Pré-requisitos
- Java JDK 17 ou superior.
- Gradle.

### Passos
1. Clone o repositório.
2. Abra o terminal na pasta do projeto.
3. Execute:
   ```bash
   ./gradlew run