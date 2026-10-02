package br.com.fiapdelivery.model;

public abstract class Veiculo {

    private final String placa;
    private final double capacidadeKg;

    protected Veiculo(String placa, double capacidadeKg) {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("A placa e obrigatoria.");
        }
        if (capacidadeKg <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser positiva.");
        }
        this.placa = placa;
        this.capacidadeKg = capacidadeKg;
    }

    public String getPlaca() {
        return placa;
    }

    public double getCapacidadeKg() {
        return capacidadeKg;
    }

    public boolean podeTransportar(Pacote pacote) {
        if (pacote == null) {
            throw new IllegalArgumentException("Pacote nao pode ser nulo.");
        }
        return pacote.getPesoKg() <= capacidadeKg;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() + " [placa=" + placa + ", capacidade=" + capacidadeKg + "kg]";
    }
}
