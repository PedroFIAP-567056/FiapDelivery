package br.com.fiapdelivery.model;

public class Caminhao extends Veiculo {

    private final int eixos;

    public Caminhao(String placa, double capacidadeKg, int eixos) {
        super(placa, capacidadeKg);
        if (eixos < 2) {
            throw new IllegalArgumentException("Um caminhao precisa ter ao menos 2 eixos.");
        }
        this.eixos = eixos;
    }

    public int getEixos() {
        return eixos;
    }

    @Override
    public String getTipo() {
        return "Caminhao";
    }

    @Override
    public String toString() {
        return "Caminhao [placa=" + getPlaca() + ", capacidade=" + getCapacidadeKg()
                + "kg, eixos=" + eixos + "]";
    }
}
