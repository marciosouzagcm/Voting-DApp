# Voting DApp (Decentralized Application)

Este projeto é uma aplicação descentralizada (DApp) desenvolvida em Java, utilizando JavaFX para a interface gráfica e Web3j para interagir com a Binance Smart Chain (BSC). A aplicação permite o registro de candidatos, a votação em candidatos e a visualização dos resultados das eleições, tudo em um ambiente seguro e transparente proporcionado pela blockchain.

## Funcionalidades

- **Cadastro de Candidatos:** Permite que novos candidatos sejam adicionados à eleição. Os nomes dos candidatos são registrados no contrato inteligente, e eles recebem um identificador único.

- **Votação:** Usuários podem votar em candidatos utilizando seus IDs. Cada voto é registrado de forma imutável no contrato inteligente, garantindo transparência e segurança.

- **Visualização dos Resultados:** Permite que os usuários visualizem o total de votos recebidos por cada candidato, com os dados sendo recuperados diretamente do contrato inteligente.

## Tecnologias Utilizadas

- **Java 17:** Linguagem principal utilizada no desenvolvimento da aplicação.
- **JavaFX 20.0.1:** Utilizado para criar uma interface gráfica rica e interativa.
- **Web3j 5.0.0:** Biblioteca que facilita a interação entre Java e a blockchain, permitindo a execução de contratos inteligentes na BSC.
- **Binance Smart Chain (BSC):** Blockchain utilizada para o registro das eleições, oferecendo baixos custos de transação e alta velocidade.

## Estrutura do Projeto

### 1. **MainApp**

- A classe principal que inicializa a aplicação JavaFX e coordena o fluxo entre as diferentes telas (cadastro de candidatos, votação, resultados).

### 2. **VotingService**

- Responsável por interagir com o contrato inteligente na blockchain. Esta classe encapsula as operações principais, como adicionar candidatos, votar e obter o total de votos.

### 3. **Candidate**

- Uma classe modelo que representa um candidato na eleição, com atributos como `id`, `name`, e `voteCount`.

### 4. **Voting**

- Um wrapper gerado pelo Web3j que facilita a interação com o contrato inteligente. Contém métodos como `addCandidate`, `vote`, e `getTotalVotes`.

### 5. **CandidateRegistrationApp, VotingApp, ElectionResultsApp**

- Classes que implementam as interfaces gráficas para as funcionalidades de cadastro, votação, e visualização de resultados, respectivamente.

## Como Executar

1. Clone este repositório para a sua máquina local:

   ```bash
   git clone https://github.com/seu-usuario/voting-dapp.git
   ```

2. Navegue até o diretório do projeto e execute o seguinte comando para compilar e iniciar a aplicação:

   ```bash
   mvn javafx:run
   ```

3. A aplicação irá abrir uma janela gráfica onde você poderá realizar o cadastro de candidatos, votar e visualizar os resultados.

## Futuras Melhorias

- **Autenticação de Usuários:** Adicionar uma camada de autenticação para garantir que apenas usuários autorizados possam votar.
- **Distribuição de Tokens:** Implementar um sistema de tokens para recompensar os usuários que participam da votação.
- **Versão Web:** Expandir a aplicação para uma versão web utilizando frameworks modernos como React ou Angular.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests. Vamos construir um sistema de votação mais transparente e acessível juntos!

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
