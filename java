import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

class ContaEnergia {
    private LocalDate dataReferencia;
    private double consumoKwh;
    private double tarifaKwh;
    private double adicionalBandeira;

    public ContaEnergia(LocalDate dataReferencia, double consumoKwh, double tarifaKwh, double adicionalBandeira) {
        this.dataReferencia = dataReferencia;
        this.consumoKwh = consumoKwh;
        this.tarifaKwh = tarifaKwh;
        this.adicionalBandeira = adicionalBandeira;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    // a) Calcular o valor a ser pago de uma conta de energia
    public double calcularValor() {
        return (consumoKwh * tarifaKwh) + adicionalBandeira;
    }
}

class Imovel {
    private String endereco;
    private List<ContaEnergia> contas = new ArrayList<>();

    public Imovel(String endereco) {
        this.endereco = endereco;
    }

    public void adicionarConta(ContaEnergia conta) {
        contas.add(conta);
    }

    // b) Relatório de contas acima da média no período
    public String gerarRelatorioAcimaDaMedia() {
        if (contas.isEmpty()) {
            return "Nenhuma conta cadastrada para o imóvel: " + endereco;
        }

        double soma = 0;
        for (ContaEnergia c : contas) {
            soma += c.calcularValor();
        }
        double media = soma / contas.size();

        StringBuilder sb = new StringBuilder();
        sb.append("=== CONTAS ACIMA DA MÉDIA (Média: R$ ").append(String.format("%.2f", media)).append(") ===\n");
        for (ContaEnergia c : contas) {
            double valor = c.calcularValor();
            if (valor > media) {
                sb.append("Ref: ").append(c.getDataReferencia())
                  .append(" | Valor: R$ ").append(String.format("%.2f", valor)).append("\n");
            }
        }
        return sb.toString();
    }

    // c) Relatório de contas nos últimos 12 meses
    public String gerarRelatorioUltimos12Meses(LocalDate dataBase) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CONTAS NOS ÚLTIMOS 12 MESES (Imóvel: ").append(endereco).append(") ===\n");

        for (ContaEnergia c : contas) {
            if (!c.getDataReferencia().isAfter(dataBase)) {
                Period diff = Period.between(c.getDataReferencia().withDayOfMonth(1), dataBase.withDayOfMonth(1));
                int mesesDiferenca = diff.getYears() * 12 + diff.getMonths();
                if (mesesDiferenca >= 0 && mesesDiferenca < 12) {
                    sb.append("Ref: ").append(c.getDataReferencia())
                      .append(" | Valor: R$ ").append(String.format("%.2f", c.calcularValor())).append("\n");
                }
            }
        }
        return sb.toString();
    }
}

public class App {
    public static void main(String[] args) {
        Imovel imovel = new Imovel("Av. Filadélfia, 100");
        LocalDate hoje = LocalDate.now();

        imovel.adicionarConta(new ContaEnergia(hoje.minusMonths(1), 220, 0.85, 10.0));
        imovel.adicionarConta(new ContaEnergia(hoje.minusMonths(2), 150, 0.85, 0.0));
        imovel.adicionarConta(new ContaEnergia(hoje.minusMonths(5), 310, 0.85, 15.0));
        imovel.adicionarConta(new ContaEnergia(hoje.minusMonths(14), 400, 0.85, 15.0));

        System.out.println(imovel.gerarRelatorioAcimaDaMedia());
        System.out.println(imovel.gerarRelatorioUltimos12Meses(hoje));
    }
}


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

class ContaEnergia {

    private LocalDate dataReferencia;
    private double consumoKwh;
    private double tarifaKwh;
    private double adicionalBandeira;

    public ContaEnergia(LocalDate dataReferencia,
                        double consumoKwh,
                        double tarifaKwh,
                        double adicionalBandeira) {

        this.dataReferencia = dataReferencia;
        this.consumoKwh = consumoKwh;
        this.tarifaKwh = tarifaKwh;
        this.adicionalBandeira = adicionalBandeira;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    // a) Calcular o valor da conta de energia
    public double calcularValor() {
        return (consumoKwh * tarifaKwh) + adicionalBandeira;
    }
}

class Imovel {

    private String endereco;
    private List<ContaEnergia> contas = new ArrayList<>();

    public Imovel(String endereco) {
        this.endereco = endereco;
    }

    public void adicionarConta(ContaEnergia conta) {
        contas.add(conta);
    }

    // b) Relatório de contas acima da média
    public String gerarRelatorioAcimaDaMedia() {

        if (contas.isEmpty()) {
            return "Nenhuma conta cadastrada para o imóvel: " + endereco;
        }

        double soma = 0;

        for (ContaEnergia c : contas) {
            soma += c.calcularValor();
        }

        double media = soma / contas.size();

        StringBuilder sb = new StringBuilder();

        sb.append("=== CONTAS ACIMA DA MEDIA ===\n");
        sb.append("Media: R$ ")
          .append(String.format("%.2f", media))
          .append("\n");

        for (ContaEnergia c : contas) {

            double valor = c.calcularValor();

            if (valor > media) {
                sb.append("Ref: ")
                  .append(c.getDataReferencia())
                  .append(" | Valor: R$ ")
                  .append(String.format("%.2f", valor))
                  .append("\n");
            }
        }

        return sb.toString();
    }

    // c) Relatório de contas nos últimos 12 meses
    public String gerarRelatorioUltimos12Meses(LocalDate dataBase) {

        StringBuilder sb = new StringBuilder();

        sb.append("=== CONTAS NOS ULTIMOS 12 MESES ===\n");
        sb.append("Imovel: ").append(endereco).append("\n");

        for (ContaEnergia c : contas) {

            LocalDate dataConta = c.getDataReferencia();

            if (!dataConta.isAfter(dataBase)) {

                Period diff = Period.between(
                    dataConta.withDayOfMonth(1),
                    dataBase.withDayOfMonth(1)
                );

                int mesesDiferenca =
                    diff.getYears() * 12 + diff.getMonths();

                if (mesesDiferenca >= 0 && mesesDiferenca < 12) {

                    sb.append("Ref: ")
                      .append(dataConta)
                      .append(" | Valor: R$ ")
                      .append(String.format("%.2f", c.calcularValor()))
                      .append("\n");
                }
            }
        }

        return sb.toString();
    }
}

// Classe principal para o Online Java Compiler
public class Main {

    public static void main(String[] args) {

        Imovel imovel = new Imovel("Av. Filadelfia, 100");

        LocalDate hoje = LocalDate.now();

        imovel.adicionarConta(
            new ContaEnergia(hoje.minusMonths(1), 220, 0.85, 10.0)
        );

        imovel.adicionarConta(
            new ContaEnergia(hoje.minusMonths(2), 150, 0.85, 0.0)
        );

        imovel.adicionarConta(
            new ContaEnergia(hoje.minusMonths(5), 310, 0.85, 15.0)
        );

        imovel.adicionarConta(
            new ContaEnergia(hoje.minusMonths(14), 400, 0.85, 15.0)
        );

        System.out.println(imovel.gerarRelatorioAcimaDaMedia());

        System.out.println(
            imovel.gerarRelatorioUltimos12Meses(hoje)
        );
    }
}

Esse é versão corrigida 


Tem o código conluido 

=== CONTAS ACIMA DA MEDIA ===
Media: R$ 239.50
Ref: 2026-06-24 | Valor: R$ 278.50
Ref: 2025-09-24 | Valor: R$ 355.00

=== CONTAS NOS ULTIMOS 12 MESES ===
Imovel: Rua das laranjeiras, 130
Ref: 2026-10-24 | Valor: R$ 197.00
Ref: 2026-09-24 | Valor: R$ 127.50
Ref: 2026-06-24 | Valor: R$ 278.50



...Program finished with exit code 0
Press ENTER to exit console.
Parte alterado: 
  Imovel imovel = new Imovel("Rua das laranjeiras, 130");

=== CONTAS ACIMA DA MEDIA ===
Media: R$ 239.50
Ref: 2026-06-24 | Valor: R$ 278.50
Ref: 2025-09-24 | Valor: R$ 355.00

=== CONTAS NOS ULTIMOS 12 MESES ===
Imovel: Av. Filadelfia, 100
Ref: 2026-10-24 | Valor: R$ 197.00
Ref: 2026-09-24 | Valor: R$ 127.50
Ref: 2026-06-24 | Valor: R$ 278.50



...Program finished with exit code 0
Press ENTER to exit console.
Versão original 
sobre as difrenças de feito a mão e feito a ia 
