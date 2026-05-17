package br.edu.lab5.exercicio2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonDAOTest {

    private final PersonDAO dao = new PersonDAO();

    private Person personValida() {
        Person p = new Person("Maria Silva", 30);
        p.addEmail(new Email("maria@exemplo.com"));
        return p;
    }

    @Test
    @DisplayName("Person totalmente válida não retorna erros")
    void pessoaValida() {
        List<String> erros = dao.isValidToInclude(personValida());
        assertTrue(erros.isEmpty(), () -> "Esperava sem erros, veio: " + erros);
    }

    // ---------- Nome ----------

    @Test
    @DisplayName("Nome com apenas 1 parte é inválido")
    void nomeComUmaParte() {
        Person p = new Person("Maria", 30);
        p.addEmail(new Email("maria@exemplo.com"));
        List<String> erros = dao.isValidToInclude(p);
        assertTrue(erros.contains("Nome deve ser composto por ao menos 2 partes"));
    }

    @Test
    @DisplayName("Nome com dígitos é inválido")
    void nomeComDigitos() {
        Person p = new Person("Maria S1lva", 30);
        p.addEmail(new Email("maria@exemplo.com"));
        List<String> erros = dao.isValidToInclude(p);
        assertTrue(erros.contains("Nome deve ser composto apenas por letras"));
    }

    @Test
    @DisplayName("Nome com caracteres especiais é inválido")
    void nomeComCaracteresEspeciais() {
        Person p = new Person("Maria S@lva", 30);
        p.addEmail(new Email("maria@exemplo.com"));
        List<String> erros = dao.isValidToInclude(p);
        assertTrue(erros.contains("Nome deve ser composto apenas por letras"));
    }

    @Test
    @DisplayName("Nome nulo gera erro de obrigatório")
    void nomeNulo() {
        Person p = new Person(null, 30);
        p.addEmail(new Email("maria@exemplo.com"));
        List<String> erros = dao.isValidToInclude(p);
        assertTrue(erros.contains("Nome é obrigatório"));
    }

    @Test
    @DisplayName("Nome com acentos é aceito")
    void nomeComAcentos() {
        Person p = new Person("João Antônio", 30);
        p.addEmail(new Email("joao@exemplo.com"));
        List<String> erros = dao.isValidToInclude(p);
        assertTrue(erros.isEmpty(), () -> "Esperava sem erros, veio: " + erros);
    }

    // ---------- Idade ----------

    @Test
    @DisplayName("Idade 0 é inválida")
    void idadeZero() {
        Person p = personValida();
        p.setAge(0);
        List<String> erros = dao.isValidToInclude(p);
        assertTrue(erros.contains("Idade deve estar no intervalo [1, 200]"));
    }

    @Test
    @DisplayName("Idade 201 é inválida")
    void idadeAcimaDoMaximo() {
        Person p = personValida();
        p.setAge(201);
        List<String> erros = dao.isValidToInclude(p);
        assertTrue(erros.contains("Idade deve estar no intervalo [1, 200]"));
    }

    @Test
    @DisplayName("Idade negativa é inválida")
    void idadeNegativa() {
        Person p = personValida();
        p.setAge(-5);
        List<String> erros = dao.isValidToInclude(p);
        assertTrue(erros.contains("Idade deve estar no intervalo [1, 200]"));
    }

    @Test
    @DisplayName("Idade nos limites (1 e 200) é válida")
    void idadesNosLimites() {
        Person p1 = personValida();
        p1.setAge(1);
        assertTrue(dao.isValidToInclude(p1).isEmpty());

        Person p2 = personValida();
        p2.setAge(200);
        assertTrue(dao.isValidToInclude(p2).isEmpty());
    }

    // ---------- Emails ----------

    @Test
    @DisplayName("Person sem emails é inválida")
    void pessoaSemEmail() {
        Person p = new Person("Maria Silva", 30);
        List<String> erros = dao.isValidToInclude(p);
        assertTrue(erros.contains("Person deve ter pelo menos um Email associado"));
    }

    @Test
    @DisplayName("Email sem @ é inválido")
    void emailSemArroba() {
        Person p = new Person("Maria Silva", 30);
        p.addEmail(new Email("mariaexemplo.com"));
        List<String> erros = dao.isValidToInclude(p);
        assertFalse(erros.isEmpty());
    }

    @Test
    @DisplayName("Email sem domínio é inválido")
    void emailSemDominio() {
        Person p = new Person("Maria Silva", 30);
        p.addEmail(new Email("maria@.com"));
        List<String> erros = dao.isValidToInclude(p);
        assertFalse(erros.isEmpty());
    }

    @Test
    @DisplayName("Email sem TLD é inválido")
    void emailSemTld() {
        Person p = new Person("Maria Silva", 30);
        p.addEmail(new Email("maria@exemplo."));
        List<String> erros = dao.isValidToInclude(p);
        assertFalse(erros.isEmpty());
    }

    @Test
    @DisplayName("Email sem parte local é inválido")
    void emailSemParteLocal() {
        Person p = new Person("Maria Silva", 30);
        p.addEmail(new Email("@exemplo.com"));
        List<String> erros = dao.isValidToInclude(p);
        assertFalse(erros.isEmpty());
    }

    @Test
    @DisplayName("Pode ter múltiplos emails — basta um inválido para gerar erro")
    void multiplosEmailsComUmInvalido() {
        Person p = new Person("Maria Silva", 30);
        p.addEmail(new Email("maria@exemplo.com"));
        p.addEmail(new Email("invalido"));
        List<String> erros = dao.isValidToInclude(p);
        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("invalido"));
    }

    @Test
    @DisplayName("Vários erros são reportados juntos")
    void varosErrosJuntos() {
        Person p = new Person("X", 0); // 1 parte + idade fora
        p.addEmail(new Email("ruim"));  // email inválido
        List<String> erros = dao.isValidToInclude(p);
        assertTrue(erros.size() >= 3);
    }
}
