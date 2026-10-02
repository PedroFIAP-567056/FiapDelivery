package br.com.fiapdelivery.main;

import br.com.fiapdelivery.model.Caminhao;
import br.com.fiapdelivery.model.Moto;
import br.com.fiapdelivery.model.Pacote;
import br.com.fiapdelivery.model.Rota;
import br.com.fiapdelivery.model.StatusPacote;
import br.com.fiapdelivery.model.Veiculo;

// Sistema Principal, reponsável pelo teste do funcionamento e validação do código desta atividade
public class SistemaPrincipal {

    public static void main(String[] args) {

        // Cenário 1
        System.out.println("=== Cenario 1: rota valida com Caminhao ===");

        Veiculo caminhao = new Caminhao("ABC1234", 5000, 3);
        Pacote pacoteA = new Pacote("PC-001", 120);
        Rota rota1 = new Rota(caminhao, pacoteA);

        System.out.println(rota1); // Rota [Caminhao [placa=ABC1234, capacidade=5000.0kg, eixos=3] -> Pacote [codigo=PC-001, peso=120.0kg, status=PENDENTE]]

        rota1.despachar(); // Caminhao ABC1234 saiu com o pacote PC-001.
        rota1.entregar(); // Pacote PC-001 entregue

        System.out.println(pacoteA); // Pacote [codigo=PC-001, peso=120.0kg, status=ENTREGUE]
        System.out.println();

        // Cenário 2
        System.out.println("=== Cenario 2: rota valida com Moto ===");

        Veiculo moto = new Moto("XYZ9K88", 30, true);
        Pacote pacoteB = new Pacote("PC-002", 8);
        Rota rota2 = new Rota(moto, pacoteB);

        System.out.println(rota2); // Rota [Moto [placa=XYZ9K88, capacidade=30.0kg, bau=sim] -> Pacote [codigo=PC-002, peso=8.0kg, status=PENDENTE]]
        
        rota2.despachar(); // Moto XYZ9K88 saiu com o pacote PC-002.
        rota2.entregar(); // Pacote PC-002 entregue. 

        System.out.println(pacoteB); // Pacote [codigo=PC-002, peso=8.0kg, status=ENTREGUE]
        System.out.println();
        
        // Cenário 3 (testes dos casos inválidos)
        System.out.println("=== Cenario 3: validacoes bloqueando dados invalidos ===");

        tentar("placa vazia", () -> new Caminhao("", 1000, 3));
        tentar("capacidade negativa", () -> new Caminhao("DEF5678", -500, 3));
        tentar("caminhao com 1 eixo", () -> new Caminhao("DEF5678", 1000, 1));
        tentar("moto com capacidade zero", () -> new Moto("MOT0001", 0, false));
        tentar("codigo de pacote nulo", () -> new Pacote(null, 10));
        tentar("peso de pacote negativo", () -> new Pacote("PC-003", -5));
        tentar("status nulo", () -> pacoteB.atualizarStatus(null));
        tentar("mudar status de pacote ja entregue", () -> {
            Pacote entregue = new Pacote("PC-004", 2);
            entregue.atualizarStatus(StatusPacote.EM_TRANSITO);
            entregue.atualizarStatus(StatusPacote.ENTREGUE);
            entregue.atualizarStatus(StatusPacote.PENDENTE);
        });
        tentar("rota com veiculo nulo", () -> new Rota(null, pacoteA));
        tentar("rota com pacote nulo", () -> new Rota(caminhao, null));
        tentar("pacote pesado demais para a moto", () -> {
            Pacote pesado = new Pacote("PC-005", 500);
            new Rota(moto, pesado);
        });
        tentar("despachar pacote que nao esta pendente", () -> rota1.despachar());
        tentar("entregar pacote antes de despachar", () -> {
            Caminhao van = new Caminhao("VAN9999", 2000, 2);
            Pacote fresco = new Pacote("PC-006", 10);
            new Rota(van, fresco).entregar();
        });
    }

    // Imprime a descrição da ação a ser executada e envolve a ação em um try catch para facilitar o teste dos limites da aplicação
    private static void tentar(String descricao, Runnable acao) {
        try {
            acao.run();
            System.out.println("[FALHA] " + descricao + ": deveria ter lancado excecao.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("[OK] " + descricao + " -> " + e.getMessage());
        }
    }
}
