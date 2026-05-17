package br.edu.lab5.exercicio1;

public class Triangulo {

    public TipoTriangulo classificar(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return TipoTriangulo.INVALIDO;
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            return TipoTriangulo.INVALIDO;
        }
        if (a == b && b == c) {
            return TipoTriangulo.EQUILATERO;
        }
        if (a == b || a == c || b == c) {
            return TipoTriangulo.ISOSCELES;
        }
        return TipoTriangulo.ESCALENO;
    }
}
