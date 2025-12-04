# Projeto 02 - Java of Empires 🏰

**Disciplina:** Programação Orientada a Objetos - Análise e Desenvolvimento de Sistemas

**Professor:** Sergio Maurício Prolo Santos Junior

**Alunos:** Eduardo Cardoso Oliveira e Joao Vitor Schmitt

## 📜 Sobre o Projeto
O **Java of Empires** é um projeto prático inspirado em jogos de estratégia em tempo real (RTS). O objetivo é aplicar conceitos fundamentais de Orientação a Objetos, como herança e polimorfismo, no desenvolvimento de um jogo interativo com interface gráfica em Java (Swing).

O desenvolvimento segue uma **Metodologia Gamificada**, onde funcionalidades são desbloqueadas através de uma "Árvore de Requisitos" disponibilizada pelo professor, permitindo escolhas de design e implementação de funcionalidades.

## 🎯 Objetivos
* **Aplicar herança e polimorfismo:** Hierarquia de classes de personagens.
* **Utilizar interfaces:** Implementação de comportamentos específicos (Guerreiro, Coletador, etc.).
* **Integrar interface gráfica:** Uso de Swing para interação visual.
* **Gerenciar escolhas de design:** Seleção de funcionalidades da árvore de requisitos.

---

## ✅ Funcionalidades Implementadas (Pontuação atingida até o momento: 14/32)

Abaixo estão listados os requisitos da Árvore de Habilidades que já foram concluídos no projeto:

### 🏗️ Base do Projeto (Requisito Obrigatório)
- [x] **Hierarquia de Classes:** Criação da classe abstrata `Personagem` e subclasses `Aldeao`, `Arqueiro` e `Cavaleiro`.
- [x] **Interfaces:** Definição e uso de `Guerreiro`, `Coletador` e `ComMontaria`.
- [x] **Polimorfismo:** A `Tela` gerencia uma coleção genérica de `Personagem`, permitindo desenhar e mover qualquer unidade.
- [x] **Criação:** Botões de criação funcionando para todos os tipos de personagens.

### 🎮 Controles Avançados
- [x] **Filtro por Tipo (4 pontos):** - Implementação de *Radio Buttons* (Todos, Aldeão, Arqueiro, Cavaleiro).
  - Comandos de movimento e ataque afetam apenas as unidades do tipo selecionado.
  - Uso de filtragem dinâmica com `instanceof` e Streams.

### ⚔️ Sistema de Combate
- [x] **Ataque Básico (3 pontos):**
  - Botão "Atacar" funcional para unidades do tipo `Guerreiro`.
  - Sistema de dano implementado (atributo `ataque` vs `vida`).
  - **Feedback Visual:** Troca de *sprites* (imagens) durante a ação de ataque para todas as classes.
  - **Dano em Área:** Unidades atacam inimigos dentro de um raio de proximidade.

- [x] **Sistema de Morte (3 pontos):**
    - Monitoramento de vida: Personagens com vida igual ou menor que zero são detectados automaticamente.
    - Gestão de Memória: Remoção segura do objeto da coleção de personagens e da tela.
    - **Feedback:** Mensagem de "Baixa confirmada" exibida no terminal.
    - Integração visual com a Barra de Vida (vida zerada resulta em eliminação imediata).

- [x] **Alcance Variável (4 pontos):**
    - Definição de raios de ataque distintos por classe (Aldeão: 50px, Cavaleiro: 75px, Arqueiro: 150px).
    - Cálculo de distância preciso entre personagens.
    - Validação de alcance antes de aplicar dano.
    - **Indicador Visual:** Círculo pontilhado exibido ao ativar o modo de ataque.

---

## 🚀 Como Executar o programa

### Pré-requisitos
* Java JDK 21 ou superior.
* Gradle.

### Passos
1.  Clone o repositório utilizando o comando: ``` git clone https://github.com/sergio-prolo-class/projeto-2-eduardo_cardoso-joao_schmitt.git ```
2.  Navegue até a pasta do projeto.
3.  Execute via terminal:

**Linux/Mac:**
```bash
./gradlew run
