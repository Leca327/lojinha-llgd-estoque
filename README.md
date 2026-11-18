# 🛒 Lojinha LLGD — Controle de Estoque

Sistema desktop em Java para controle de estoque de uma pequena lojinha de mercado. Permite cadastrar, buscar, atualizar e excluir **produtos**, **fornecedores** e **lotes**, tudo por uma interface gráfica simples.

Projeto desenvolvido como **Relatório de Extensão** na Universidade Veiga de Almeida (UVA) — Cursos da Área de Informática.

## 📖 Sobre o projeto

A lojinha LLGD precisa organizar os produtos que vende e os fornecedores que a abastecem. O sistema modela esse cenário (o "minimundo"):

- **Produto**: código único, nome, categoria e preço de venda.
- **Fornecedor**: CNPJ, código, nome, contato (telefone) e endereço. Um fornecedor pode fornecer vários produtos.
- **Lote**: representa uma entrada de mercadoria. Liga um produto a um fornecedor e guarda código do lote, validade e quantidade, que é como o estoque é registrado.

## ✨ Funcionalidades

- CRUD completo de **Produtos**, **Fornecedores** e **Lotes** (cadastrar, buscar, atualizar e excluir)
- Busca por código, com tabela de resultados e contador de registros encontrados
- Visualização de todos os lotes cadastrados
- Seleção de fornecedores e produtos existentes ao cadastrar ou atualizar um lote
- Validações nos formulários: campos em branco, CNPJ com 14 dígitos numéricos e checagem de dia e mês na data de validade
- Mensagens de confirmação e de erro por caixas de diálogo
- Persistência local em arquivos de texto, sem precisar de banco de dados

## 🧰 Tecnologias

- Java 22
- Java Swing (telas criadas com o editor visual do NetBeans, arquivos `.form`)
- Maven
- Armazenamento em arquivos `.txt`

## 🗂️ Estrutura do projeto

```
Lojinha_LLGD/
├── pom.xml
├── prod.txt            # produtos
├── forn.txt            # fornecedores
├── lote.txt            # lotes
└── src/main/java/
    ├── com/mycompany/lojinha_llgd/   # classe principal (Lojinha_LLGD)
    ├── GUI/                          # telas principais (TelaPrin, Produto, Fornecedor, Lote)
    ├── SubGui/                       # telas de cadastro e atualização (Cad_*, Att_*)
    ├── MOD/                          # modelos (Produto, Fornecedor, Lote)
    ├── SwingPerson/                  # componentes Swing personalizados
    └── IMG/                          # ícones e imagens da interface
```

## ▶️ Como executar

**Pré-requisitos:** JDK 22 ou superior e Maven (opcional se usar o NetBeans).

### Pelo NetBeans
1. Abra o projeto `Lojinha_LLGD` (File → Open Project).
2. Execute a classe `com.mycompany.lojinha_llgd.Lojinha_LLGD` (Run Project).

### Pelo terminal
Na pasta raiz do projeto, para que os arquivos `.txt` sejam encontrados:

```bash
mvn compile
java -cp target/classes com.mycompany.lojinha_llgd.Lojinha_LLGD
```

> ⚠️ Os arquivos `prod.txt`, `forn.txt` e `lote.txt` são lidos a partir do **diretório de execução**. Rode o programa a partir da pasta raiz do projeto, onde eles ficam.

## 💾 Armazenamento de dados

Cada entidade fica em um arquivo de texto, com um registro por linha e campos separados por vírgula:

| Arquivo | Conteúdo |
|---|---|
| `prod.txt` | Produtos |
| `forn.txt` | Fornecedores |
| `lote.txt` | Lotes |

## 👥 Autores

- Letícia dos Reis Prado
- Daniel Rios de Mello de Castro
- Giovanna Rios de Lima Dantas
- Luiza Mello de Sá

Universidade Veiga de Almeida — Rio de Janeiro/RJ
