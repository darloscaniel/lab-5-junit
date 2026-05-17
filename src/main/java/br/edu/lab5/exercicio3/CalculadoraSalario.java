package br.edu.lab5.exercicio3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CalculadoraSalario {

    private record RegraDesconto(BigDecimal limite, BigDecimal descontoAlto, BigDecimal descontoBaixo) {}

    private static final Map<Cargo, RegraDesconto> REGRAS = Map.of(
            Cargo.DESENVOLVEDOR, new RegraDesconto(new BigDecimal("3000.00"), new BigDecimal("0.20"), new BigDecimal("0.10")),
            Cargo.DBA,           new RegraDesconto(new BigDecimal("2000.00"), new BigDecimal("0.25"), new BigDecimal("0.15")),
            Cargo.TESTADOR,      new RegraDesconto(new BigDecimal("2000.00"), new BigDecimal("0.25"), new BigDecimal("0.15")),
            Cargo.GERENTE,       new RegraDesconto(new BigDecimal("5000.00"), new BigDecimal("0.30"), new BigDecimal("0.20"))
    );

    public BigDecimal calcularSalarioLiquido(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário é obrigatório");
        }
        if (funcionario.getCargo() == null) {
            throw new IllegalArgumentException("Cargo é obrigatório");
        }
        if (funcionario.getSalarioBase() == null) {
            throw new IllegalArgumentException("Salário base é obrigatório");
        }

        BigDecimal salario = funcionario.getSalarioBase();
        RegraDesconto regra = REGRAS.get(funcionario.getCargo());
        BigDecimal desconto = salario.compareTo(regra.limite()) >= 0
                ? regra.descontoAlto()
                : regra.descontoBaixo();

        BigDecimal liquido = salario.subtract(salario.multiply(desconto));
        return liquido.setScale(2, RoundingMode.HALF_UP);
    }
}
