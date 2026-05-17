package br.edu.lab5.exercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PersonDAO {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[^@\\s]+@[^@\\s.]+\\.[^@\\s.]+$");

    private static final Pattern NAME_PART_PATTERN =
            Pattern.compile("^\\p{L}+$");

    public void save(Person p) {
        // Persistência fora do escopo deste exercício.
    }

    public List<String> isValidToInclude(Person p) {
        List<String> erros = new ArrayList<>();

        validarNome(p, erros);
        validarIdade(p, erros);
        validarEmails(p, erros);

        return erros;
    }

    private void validarNome(Person p, List<String> erros) {
        String nome = p.getName();
        if (nome == null || nome.trim().isEmpty()) {
            erros.add("Nome é obrigatório");
            return;
        }
        String[] partes = nome.trim().split("\\s+");
        if (partes.length < 2) {
            erros.add("Nome deve ser composto por ao menos 2 partes");
        }
        for (String parte : partes) {
            if (!NAME_PART_PATTERN.matcher(parte).matches()) {
                erros.add("Nome deve ser composto apenas por letras");
                break;
            }
        }
    }

    private void validarIdade(Person p, List<String> erros) {
        int idade = p.getAge();
        if (idade < 1 || idade > 200) {
            erros.add("Idade deve estar no intervalo [1, 200]");
        }
    }

    private void validarEmails(Person p, List<String> erros) {
        List<Email> emails = p.getEmails();
        if (emails == null || emails.isEmpty()) {
            erros.add("Person deve ter pelo menos um Email associado");
            return;
        }
        for (Email email : emails) {
            String nome = email.getName();
            if (nome == null || !EMAIL_PATTERN.matcher(nome).matches()) {
                erros.add("Email inválido: " + nome);
            }
        }
    }
}
