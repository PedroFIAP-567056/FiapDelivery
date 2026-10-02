package br.com.fiapdelivery.model;

public class Moto extends Veiculo {

    private final boolean bau;

    public Moto(String placa, double capacidadeKg, boolean bau) {
        super(placa, capacidadeKg);
        this.bau = bau;
    }

    public boolean temBau() {
        return bau;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }

    @Override
    public String toString() {
        return "Moto [placa=" + getPlaca() + ", capacidade=" + getCapacidadeKg()
                + "kg, bau=" + (bau ? "sim" : "nao") + "]";
    }
}
