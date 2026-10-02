package br.com.fiapdelivery.model;

public class Rota {

    private final Veiculo veiculo;
    private final Pacote pacote;

    public Rota(Veiculo veiculo, Pacote pacote) {
        if (veiculo == null) {
            throw new IllegalArgumentException("A rota precisa de um veiculo.");
        }
        if (pacote == null) {
            throw new IllegalArgumentException("A rota precisa de um pacote.");
        }
        if (!veiculo.podeTransportar(pacote)) {
            throw new IllegalArgumentException(
                "Veiculo " + veiculo.getPlaca() + " nao pode transportar o pacote "
                + pacote.getCodigo() + " (peso " + pacote.getPesoKg()
                + "kg > capacidade " + veiculo.getCapacidadeKg() + "kg).");
        }
        this.veiculo = veiculo;
        this.pacote = pacote;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void despachar() {
        if (pacote.getStatus() != StatusPacote.PENDENTE) {
            throw new IllegalStateException(
                "Somente pacotes PENDENTE podem ser despachados (atual: " + pacote.getStatus() + ").");
        }
        pacote.atualizarStatus(StatusPacote.EM_TRANSITO);
        System.out.println(veiculo.getTipo() + " " + veiculo.getPlaca()
            + " saiu com o pacote " + pacote.getCodigo() + ".");
    }

    public void entregar() {
        if (pacote.getStatus() != StatusPacote.EM_TRANSITO) {
            throw new IllegalStateException(
                "Somente pacotes EM_TRANSITO podem ser entregues (atual: " + pacote.getStatus() + ").");
        }
        pacote.atualizarStatus(StatusPacote.ENTREGUE);
        System.out.println("Pacote " + pacote.getCodigo() + " entregue.");
    }

    @Override
    public String toString() {
        return "Rota [" + veiculo + " -> " + pacote + "]";
    }
}
