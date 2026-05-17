package br.edu.lab5.exercicio1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrianguloTest {

    private final Triangulo triangulo = new Triangulo();

    // ---------- Triângulos válidos ----------

    @Test
    @DisplayName("Triângulo escaleno válido (3, 4, 5)")
    void escalenoValido() {
        assertEquals(TipoTriangulo.ESCALENO, triangulo.classificar(3, 4, 5));
    }

    @Test
    @DisplayName("Triângulo isósceles válido (5, 5, 3)")
    void isoscelesValido() {
        assertEquals(TipoTriangulo.ISOSCELES, triangulo.classificar(5, 5, 3));
    }

    @Test
    @DisplayName("Triângulo equilátero válido (4, 4, 4)")
    void equilateroValido() {
        assertEquals(TipoTriangulo.EQUILATERO, triangulo.classificar(4, 4, 4));
    }

    // ---------- 3 CTs para isósceles com permutação dos mesmos valores ----------

    @Test
    @DisplayName("Isósceles permutação 1: (5, 5, 3)")
    void isoscelesPermutacao1() {
        assertEquals(TipoTriangulo.ISOSCELES, triangulo.classificar(5, 5, 3));
    }

    @Test
    @DisplayName("Isósceles permutação 2: (5, 3, 5)")
    void isoscelesPermutacao2() {
        assertEquals(TipoTriangulo.ISOSCELES, triangulo.classificar(5, 3, 5));
    }

    @Test
    @DisplayName("Isósceles permutação 3: (3, 5, 5)")
    void isoscelesPermutacao3() {
        assertEquals(TipoTriangulo.ISOSCELES, triangulo.classificar(3, 5, 5));
    }

    // ---------- Valores inválidos individuais ----------

    @Test
    @DisplayName("Um valor zero -> inválido")
    void umValorZero() {
        assertEquals(TipoTriangulo.INVALIDO, triangulo.classificar(0, 4, 5));
    }

    @Test
    @DisplayName("Um valor negativo -> inválido")
    void umValorNegativo() {
        assertEquals(TipoTriangulo.INVALIDO, triangulo.classificar(-1, 4, 5));
    }

    // ---------- Soma de 2 lados igual ao terceiro (permutações) ----------

    @Test
    @DisplayName("Soma de dois lados == terceiro, permutação 1: (2, 3, 5)")
    void somaIgualTerceiroPermutacao1() {
        assertEquals(TipoTriangulo.INVALIDO, triangulo.classificar(2, 3, 5));
    }

    @Test
    @DisplayName("Soma de dois lados == terceiro, permutação 2: (2, 5, 3)")
    void somaIgualTerceiroPermutacao2() {
        assertEquals(TipoTriangulo.INVALIDO, triangulo.classificar(2, 5, 3));
    }

    @Test
    @DisplayName("Soma de dois lados == terceiro, permutação 3: (5, 2, 3)")
    void somaIgualTerceiroPermutacao3() {
        assertEquals(TipoTriangulo.INVALIDO, triangulo.classificar(5, 2, 3));
    }

    // ---------- Soma de 2 lados menor que o terceiro (permutações) ----------

    @Test
    @DisplayName("Soma de dois lados < terceiro, permutação 1: (1, 2, 10)")
    void somaMenorTerceiroPermutacao1() {
        assertEquals(TipoTriangulo.INVALIDO, triangulo.classificar(1, 2, 10));
    }

    @Test
    @DisplayName("Soma de dois lados < terceiro, permutação 2: (1, 10, 2)")
    void somaMenorTerceiroPermutacao2() {
        assertEquals(TipoTriangulo.INVALIDO, triangulo.classificar(1, 10, 2));
    }

    @Test
    @DisplayName("Soma de dois lados < terceiro, permutação 3: (10, 1, 2)")
    void somaMenorTerceiroPermutacao3() {
        assertEquals(TipoTriangulo.INVALIDO, triangulo.classificar(10, 1, 2));
    }

    // ---------- Três valores iguais a zero ----------

    @Test
    @DisplayName("Três valores zero -> inválido")
    void tresValoresZero() {
        assertEquals(TipoTriangulo.INVALIDO, triangulo.classificar(0, 0, 0));
    }
}
