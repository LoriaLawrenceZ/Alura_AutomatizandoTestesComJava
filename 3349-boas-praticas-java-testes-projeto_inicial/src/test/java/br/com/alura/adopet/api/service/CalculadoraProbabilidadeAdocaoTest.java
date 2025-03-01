package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    void cenario01() {
        //idade 4 anos e 4kg - Alta

        Abrigo abrigo = new Abrigo(
                new CadastroAbrigoDto(
                        "Abrigo Feliz",
                        "94999999999",
                        "abrigofeliz@email.com.br"
                )
        );
        Pet pet = new Pet(
                new CadastroPetDto(
                        TipoPet.GATO,
                        "Miau",
                        "Siames",
                        4,
                        "Cinza",
                        4.0f
                ),
                abrigo
        );

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }

    @Test
    void cenario02() {
        //idade 15 anos e 4kg - Média

        Abrigo abrigo = new Abrigo(
                new CadastroAbrigoDto(
                        "Abrigo Feliz",
                        "94999999999",
                        "abrigofeliz@email.com.br"
                )
        );
        Pet pet = new Pet(
                new CadastroPetDto(
                        TipoPet.GATO,
                        "Miau",
                        "Siames",
                        15,
                        "Cinza",
                        4.0f
                ),
                abrigo
        );

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }
}