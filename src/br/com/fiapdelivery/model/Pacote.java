package br.com.fiapdelivery.model;

public class Pacote {

    private final String codigo;
    private final double pesoKg;
    private StatusPacote status;

    public Pacote(String codigo, double pesoKg) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O codigo do pacote e obrigatorio.");
        }
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("O peso do pacote deve ser positivo.");
        }
        this.codigo = codigo;
        this.pesoKg = pesoKg;
        this.status = StatusPacote.PENDENTE;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public StatusPacote getStatus() {
        return status;
    }

    public void atualizarStatus(StatusPacote novoStatus) {
        if (novoStatus == null) {
            throw new IllegalArgumentException("O status nao pode ser nulo.");
        }
        if (this.status == StatusPacote.ENTREGUE) {
            throw new IllegalStateException("Pacote ja entregue nao pode mudar de status.");
        }
        this.status = novoStatus;
    }

    @Override
    public String toString() {
        return "Pacote [codigo=" + codigo + ", peso=" + pesoKg + "kg, status=" + status + "]";
    }
}
