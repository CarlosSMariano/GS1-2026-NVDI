
# GS1-2026-NVDI

## 📋 Sobre o Projeto

O **GS1-2026-NVDI** é um sistema de monitoramento agrícola que **simula** o cálculo do NVDI (Normalized Difference Vegetation Index - Índice de Vegetação por Diferença Normalizada) a partir de dados meteorológicos fornecidos pela API Open-Meteo.

### ⚠️ Importante

Este sistema é uma **simulação educacional**. O NVDI real é calculado a partir de imagens de satélite que medem a reflectância da vegetação nas bandas do vermelho e infravermelho próximo. Neste projeto, utilizamos dados climáticos (temperatura, umidade, chuva e vento) como proxy para estimar o risco e a saúde da vegetação.

## 🎯 Objetivo

Simular a análise de risco para fazendas de grãos e frutas com base em condições climáticas, demonstrando conceitos de:
- Programação orientada a objetos
- Integração com APIs REST
- Tratamento de dados JSON
- Cálculos de risco baseados em condições ambientais

## 🌦️ Fonte dos Dados

Os dados climáticos são obtidos da **Open-Meteo API** (https://open-meteo.com), uma API gratuita e sem necessidade de autenticação que fornece:
- Temperatura atual
- Umidade relativa do ar
- Velocidade do vento
- Precipitação (chuva)

## 🏗️ Arquitetura do Projeto

```
br.com.nvdi.gs/
├── application/
│   ├── APIApplication.java    # Integração com Open-Meteo API
│   └── DadosAtuais.java       # DTO para dados climáticos
├── domain/
│   ├── Fazenda.java           # Classe base abstrata
│   ├── FazendaGraos.java      # Especialização para grãos
│   └── FazendaFrutas.java     # Especialização para frutas
├── infrastructure/
│   └── Repository.java        # Armazenamento em memória
└── presentation/
└── Main.java              # Interface com o usuário
```

## 🚀 Funcionalidades

1. **Cadastrar fazendas**
   - Nome da fazenda
   - Nome do proprietário
   - Coordenadas geográficas (latitude/longitude)
   - Tipo de plantio (grãos ou frutas)

2. **Listar fazendas**
   - Exibição organizada em tabela
   - Separadas por tipo de plantio

3. **Excluir fazendas**
   - Busca por ID
   - Confirmação antes da exclusão

4. **Consultar previsão**
   - Busca dados climáticos em tempo real da Open-Meteo
   - Calcula risco baseado nas condições atuais
   - Exibe análise específica para cada tipo de cultura

## 🧮 Lógica de Simulação do NVDI

O sistema **simula** o conceito do NVDI através das seguintes regras:

### Para Fazendas de Grãos:
- Risco ALTO: Umidade > 80% OU chuva > 50mm (pode causar fungos)
- Risco BAIXO: Temperatura entre 20-30°C E umidade entre 40-70%
- Risco MEDIO: Demais condições

### Para Fazendas de Frutas:
- Risco ALTO: Vento > 50km/h (queda de frutas) OU chuva > 40mm
- Risco BAIXO: Temperatura entre 18-28°C E vento < 20km/h
- Risco MEDIO: Demais condições

*Nota: Estas regras são meramente ilustrativas para fins educacionais.*

## 📦 Pré-requisitos

- Java 17 ou superior
- Conexão com internet (para consumir a API Open-Meteo)
- Biblioteca Jackson para parsing JSON

## 🔧 Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/GS1-2026-NVDI.git
```

2. Compile o projeto:
```bash
javac -cp ".:jackson-databind-2.15.0.jar:jackson-core-2.15.0.jar:jackson-annotations-2.15.0.jar" br/com/nvdi/gs/presentation/Main.java
```

3. Execute o sistema:
```bash
java -cp ".:jackson-databind-2.15.0.jar:jackson-core-2.15.0.jar:jackson-annotations-2.15.0.jar" br.com.nvdi.gs.presentation.Main
```

## 📊 Exemplo de Uso

```
╔══════════════════════════════════════╗
║        SISTEMA DE MONITORAMENTO      ║
╠══════════════════════════════════════╣
║      1 - Cadastrar fazendas          ║
║      2 - Listar fazendas             ║
║      3 - Excluir fazendas            ║
║      4 - Consultar Previsao          ║
║      0 - Sair                        ║
╚══════════════════════════════════════╝
```

## 🔬 NVDI Real vs Simulação

| Aspecto | NVDI Real | Simulação do Projeto |
|---------|-----------|---------------------|
| Fonte de dados | Imagens de satélite (bandas espectrais) | API meteorológica |
| Cálculo | (NIR - RED) / (NIR + RED) | Regras baseadas em clima |
| Métrica | -1 a +1 (saúde da vegetação) | ALTO/MEDIO/BAIXO (risco) |
| Aplicação real | Agricultura de precisão, monitoramento de queimadas | Educacional/Demonstração |

## 📝 Licença

Este projeto é desenvolvido para fins educacionais como parte do curso de Graduação em Sistemas de Informação.

## 👥 Autores

- Carlos Eduardo Sanches Mariano - RM 561756
- Gabriel Henrique Borges Hombris - RM 566553
- Icaro Machado de Carvalho - RM 572804
- Nicolas Guinante - RM 570222

## 🙏 Agradecimentos

- [Open-Meteo](https://open-meteo.com) pela API meteorológica gratuita
- Jackson team pela biblioteca de processamento JSON

---

**Nota:** Este sistema NÃO implementa o cálculo real do NVDI. É uma simulação que utiliza dados climáticos para demonstrar conceitos de programação e integração com APIs.
