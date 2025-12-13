# Projeto 02 - Java of Empires 🏰

**Disciplina:** Programação Orientada a Objetos - Análise e Desenvolvimento de Sistemas

**Professor:** Sergio Maurício Prolo Santos Junior

**Alunos:** Eduardo Cardoso Oliveira e Joao Vitor Schmitt

## 📜 Sobre o Projeto

O **Java of Empires** é um projeto prático inspirado em jogos de estratégia em tempo real (RTS). O objetivo é aplicar
conceitos fundamentais de Orientação a Objetos, como herança e polimorfismo, no desenvolvimento de um jogo interativo
com interface gráfica em Java (Swing).

O desenvolvimento segue uma **Metodologia Gamificada**, onde funcionalidades são desbloqueadas através de uma "Árvore de
Requisitos", permitindo autonomia nas escolhas de design e implementação.

## 🎯 Objetivos

* **Aplicar herança e polimorfismo:** Hierarquia de classes de personagens.
* **Utilizar interfaces:** Implementação de comportamentos específicos (Guerreiro, Coletador, etc.).
* **Integrar interface gráfica:** Uso de Swing para interação visual.
* **Gerenciar escolhas de design:** Seleção de funcionalidades da árvore de requisitos.

---

## ✅ Funcionalidades Implementadas (Pontuação Total: 48 pontos)

O projeto atingiu a meta de aprovação (32 pontos) implementando as seguintes funcionalidades da Árvore de Requisitos:

### 🏗️ Base do Projeto (Requisito Obrigatório)

- [x] **Hierarquia de Classes:** Criação da classe abstrata `Personagem` para encapsular atributos comuns (vida, posição) e suas subclasses concretas (`Aldeao`, `Arqueiro`, `Cavaleiro`).
- [x] **Interfaces:** Uso de interfaces para definir capacidades: `Guerreiro` (atacar), `Coletador` (trabalhar) e `ComMontaria` (mobilidade).
- [x] **Controles Básicos:** Botões para criação dinâmica de personagens.

### ⚔️ Sistema de Combate

- [x] **Ataque Básico (3 pontos):**
  - Lógica de combate onde guerreiros causam dano ao alvo.
  - Troca de sprite para feedback visual da ação de ataque.

- [x] **Sistema de Morte (3 pontos):**
  - Remoção lógica do personagem da lista de renderização quando vida <= 0.
  - Feedback visual (efeito de *fade-out*) e sonoro ao morrer.
  - Contabilização de baixas no placar.

- [x] **Alcance Variável (4 pontos):**
  - Implementação de raios de ataque distintos: Aldeão (30px), Cavaleiro (50px) e Arqueiro (150px).
  - Validação matemática de distância antes de permitir o ataque.
  - Indicador visual do raio de alcance durante a ação.

- [x] **Esquiva (5 pontos):**
  - Mecânica probabilística para ignorar dano recebido.
  - Balanceamento: Aldeão (10%), Cavaleiro (15%), Arqueiro (25%).
  - Feedback visual flutuante ("ESQUIVOU!").

### 🎮 Interface e Controles

- [x] **Filtro por Tipo (4 pontos):**
  - Sistema de seleção de unidades (Radio Buttons/TAB) para comandos em grupo.
  - Permite controlar apenas arqueiros, apenas cavaleiros ou todos simultaneamente.

- [x] **Controle de Montaria (5 pontos):**
  - Mecânica exclusiva para Cavaleiros (Tecla `M`).
  - Alternância de estados (Montado/Desmontado) afetando velocidade (4x) e sprite.

- [x] **Atalhos de Teclado (6 pontos):**
  - Mapeamento completo de ações (`WASD` mover, `1-3` criar, `Espaço` atacar, `TAB` filtrar).
  - Implementação via `InputMap` e `ActionMap` para melhor responsividade.

- [x] **Barra de Vida (4 pontos):**
  - Renderização gráfica da saúde sobre cada unidade.
  - Mudança dinâmica de cor (Verde -> Amarelo -> Vermelho).

- [x] **Efeitos Sonoros (5 pontos):**
  - Feedback auditivo para ações de ataque (diferente por classe), dano e morte.
  - Gerenciamento de áudio para evitar sobreposição excessiva.

### 🏛️ Decisões de Design e Arquitetura

Esta seção detalha as escolhas técnicas feitas para garantir um código modular e manutenível:

- [x] **Arquivo de Configurações (3 pontos):**
  - **Decisão:** Centralizar todas as constantes de balanceamento (vida, dano, velocidade, chances) na classe estática `Config`.
  - **Benefício:** Facilita ajustes de gameplay sem necessidade de alterar a lógica das classes de domínio.

- [x] **Fábrica de Personagens - Factory Pattern (6 pontos):**
  - **Decisão:** Implementar o padrão de projeto *Factory Method* (`PersonagemFactory`) com o uso de *Java Switch Expressions*.
  - **Benefício:** Desacopla a interface gráfica (`PainelControles`) da lógica de instanciação das classes, eliminando código duplicado e facilitando a adição de novos tipos de unidades no futuro.

- [x] **Tratamento de Erros:**
  - Implementação robusta no carregamento de recursos (sprites e áudio), evitando falhas fatais (`crash`) caso um arquivo esteja ausente.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java JDK 17 ou superior.
- Gradle (incluso via wrapper).

### Passos
1. Clone o repositório.
2. Abra o terminal na pasta raiz do projeto.
3. Execute o comando para compilar e rodar:
  * **Windows:**
    ```cmd
    gradlew.bat run
    ```
  * **Linux/Mac:**
    ```bash
    ./gradlew run
    ```