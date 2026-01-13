
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ContaBancaria {

    private String titular;
    private double saldo;
    private double credito;
    private String extrato;

    public ContaBancaria(String titular, double saldoInicial, double creditoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
        this.credito = creditoInicial;
        this.extrato = "--- EXTRATO INICIAL ---\n";
    }

    // Método para Transferir
    public boolean transferir(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            extrato += String.format("- Transferência: R$ %.2f%n", valor);
            return true;
        } else if (valor <= (saldo + credito)) {
            double falta = valor - saldo;
            credito -= falta;
            saldo = 0;
            extrato += String.format("- Transferência com Crédito: R$ %.2f%n", valor);
            return true;
        }
        return false;
    }

    public void receber(double valor) {
        if (valor > 0) {
            saldo += valor;
            extrato += String.format("+ Recebimento: R$ %.2f%n", valor);
        }
    }

    public String getExtrato() { return extrato; }
    public double getSaldo() { return saldo; }
    public double getCredito() { return credito; }
    public String getTitular() { return titular; }

    public void salvarExtratoNoArquivo() {
        String filename = "extrato_" + titular.replaceAll("\\s+","_") + ".txt";
        Path path = Paths.get(filename);
        try {
            Files.writeString(path, extrato, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Extrato salvo em: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao salvar extrato: " + e.getMessage());
        }
    }
}
// ...existing code...