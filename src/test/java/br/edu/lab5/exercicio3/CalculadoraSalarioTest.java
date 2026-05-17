package br.edu.lab5.exercicio3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraSalarioTest {

    private final CalculadoraSalario calc = new CalculadoraSalario();

    private Funcionario funcionario(Cargo cargo, String salario) {
        return new Funcionario("Fulano", "fulano@x.com", new BigDecimal(salario), cargo);
    }

    // ---------- DESENVOLVEDOR ----------

    @Test
    @DisplayName("DESENVOLVEDOR com salário >= 3000 => desconto 20%")
    void desenvolvedorAcimaLimite() {
        assertEquals(new BigDecimal("2400.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.DESENVOLVEDOR, "3000.00")));
        assertEquals(new BigDecimal("4000.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.DESENVOLVEDOR, "5000.00")));
    }

    @Test
    @DisplayName("DESENVOLVEDOR com salário < 3000 => desconto 10%")
    void desenvolvedorAbaixoLimite() {
        assertEquals(new BigDecimal("2250.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.DESENVOLVEDOR, "2500.00")));
    }

    @Test
    @DisplayName("DESENVOLVEDOR com 2999.99 => 10%")
    void desenvolvedorJustoAbaixo() {
        assertEquals(new BigDecimal("2699.99"),
                calc.calcularSalarioLiquido(funcionario(Cargo.DESENVOLVEDOR, "2999.99")));
    }

    // ---------- DBA ----------

    @Test
    @DisplayName("DBA com salário >= 2000 => desconto 25%")
    void dbaAcimaLimite() {
        assertEquals(new BigDecimal("1500.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.DBA, "2000.00")));
        assertEquals(new BigDecimal("3000.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.DBA, "4000.00")));
    }

    @Test
    @DisplayName("DBA com salário < 2000 => desconto 15%")
    void dbaAbaixoLimite() {
        assertEquals(new BigDecimal("1530.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.DBA, "1800.00")));
    }

    // ---------- TESTADOR ----------

    @Test
    @DisplayName("TESTADOR com salário >= 2000 => desconto 25%")
    void testadorAcimaLimite() {
        assertEquals(new BigDecimal("1875.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.TESTADOR, "2500.00")));
    }

    @Test
    @DisplayName("TESTADOR com salário < 2000 => desconto 15%")
    void testadorAbaixoLimite() {
        assertEquals(new BigDecimal("1530.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.TESTADOR, "1800.00")));
    }

    // ---------- GERENTE ----------

    @Test
    @DisplayName("GERENTE com salário >= 5000 => desconto 30%")
    void gerenteAcimaLimite() {
        assertEquals(new BigDecimal("3500.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.GERENTE, "5000.00")));
        assertEquals(new BigDecimal("7000.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.GERENTE, "10000.00")));
    }

    @Test
    @DisplayName("GERENTE com salário < 5000 => desconto 20%")
    void gerenteAbaixoLimite() {
        assertEquals(new BigDecimal("3200.00"),
                calc.calcularSalarioLiquido(funcionario(Cargo.GERENTE, "4000.00")));
    }

    // ---------- Validações ----------

    @Test
    @DisplayName("Funcionário nulo lança IllegalArgumentException")
    void funcionarioNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> calc.calcularSalarioLiquido(null));
    }

    @Test
    @DisplayName("Cargo nulo lança IllegalArgumentException")
    void cargoNulo() {
        Funcionario f = new Funcionario("X", "x@y.com", new BigDecimal("1000.00"), null);
        assertThrows(IllegalArgumentException.class,
                () -> calc.calcularSalarioLiquido(f));
    }

    @Test
    @DisplayName("Salário nulo lança IllegalArgumentException")
    void salarioNulo() {
        Funcionario f = new Funcionario("X", "x@y.com", null, Cargo.DBA);
        assertThrows(IllegalArgumentException.class,
                () -> calc.calcularSalarioLiquido(f));
    }
}
