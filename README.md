# 🏁 NOME DO PROJETO

**Gerenciador de Imóveis — Aplicação Mobile de Apoio à Corretagem Autônoma**

Aplicativo nativo Android desenvolvido para catalogação, gestão diária de portfólio e georreferenciamento de imóveis, focado em otimizar a rotina de corretores autônomos e modernizar pequenos negócios imobiliários através da computação móvel.

---

## 🧑‍💻 Membros da equipe

* **Matrícula:** [608584] | **Nome:** João Guilherme Pinto Souza do Nascimento | **Curso:** Engenharia de Software (UFC Quixadá)

---

## 💡 Objetivo Geral

Desenvolver uma solução móvel nativa, robusta e de alta usabilidade aplicando a arquitetura MVVM, projetada para apoiar corretores de imóveis autônomos no cadastro, organização, consulta reativa e traçado de rotas geográficas para visitação de portfólios imobiliários, promovendo a modernização digital de suas atividades profissionais.

---

## 👀 Público-Alvo

Corretores de imóveis autônomos, profissionais independentes do setor imobiliário e pequenos gestores que carecem de ferramentas digitais acessíveis e padronizadas para a gestão de imóveis no dia a dia (utilizando profissionais locais da região como parceiros-piloto para validação de uso real e extensão universitária).

---

## 🌟 Impacto Esperado

* **Substituição de Registros Informais:** Fim da dependência de anotações em cadernos ou planilhas desestruturadas, centralizando os dados de forma segura no celular.
* **Ganho de Produtividade Diária:** Agilidade no cadastro e na consulta de informações chaves (preço, área, diferenciais) durante o atendimento em tempo real ao cliente.
* **Otimização de Deslocamento:** Redução do tempo de busca e navegação no trânsito através do georreferenciamento com integração direta a aplicativos de GPS.
* **Inclusão Digital:** Entrega de uma interface intuitiva com suporte a acessibilidade visual (alto contraste e Modo Escuro), facilitando a transição de profissionais tradicionais para o meio digital.

---

## 🚩 Principais funcionalidades da aplicação

* **Listagem Reativa de Imóveis (Read):** Exibição instantânea do portfólio na tela inicial via `Flow`, atualizando os cards automaticamente no momento em que o banco de dados sofre qualquer alteração, contando com tela informativa explicativa (*Empty State*) quando não há cadastros.
* **Cadastro Inteligente com API Externa (Create):** Formulário de inserção de imóveis com validação de dados e consulta automatizada de CEP (via integração REST com a API pública ViaCEP), preenchendo logradouro, bairro, cidade e estado em milissegundos.
* **Edição Ágil via Popup Modal (Update):** Sistema interativo de atualização rápida que permite modificar preços, títulos e descrições diretamente de um diálogo suspenso na tela principal, sem perda de contexto de navegação.
* **Exclusão Simplificada (Delete):** Remoção instantânea de imóveis obsoletos ou já negociados com apenas um clique no card de listagem.
* **Georreferenciamento e Rotas GPS (Integração Nativa):** Abertura automática de rotas no Google Maps ou Waze com o endereço exato do imóvel a partir do clique no ícone de localização do card (`Intent` Geográfica do Android).
* **Suporte Integral ao Modo Escuro (Material Design 3):** Interface com paleta de cores dinâmica que se adapta automaticamente ao tema claro ou escuro configurado no sistema operacional do aparelho.

---

> **Warning**  
> Daqui em diante o README.md só deve ser preenchido no momento da entrega final.

## Tecnologias:

O projeto foi construído utilizando as melhores práticas do desenvolvimento moderno para Android:

* **Linguagem:** [Kotlin](https://kotlinlang.org/)
* **Interface Gráfica:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (UI 100% Declarativa e Material Design 3)
* **Padrão de Arquitetura:** MVVM (Model - View - ViewModel)
* **Persistência Local:** [Room Database](https://developer.android.com/training/data-storage/room) (SQLite ORM) + Kotlin Coroutines & Flow
* **Consumo de API Externa:** [Retrofit 2](https://square.github.io/retrofit/) + Gson Converter (Consulta HTTP à API do [ViaCEP](https://viacep.com.br/))
* **Integração com o Sistema:** Android Intents para chamadas de URI Geográfica (`geo:0,0?q=...`)

---

## Instruções para Execução

Para compilar e testar o aplicativo localmente em um emulador ou dispositivo físico:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/Joao-GuilhermeS/imob-app-maneger-.git
   