package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @Mock
    private PetRepository oPetRepository;
    @Mock
    private Pet oPet;
    @Mock
    SolicitacaoAdocaoDto oSolicitacaoAdocaoDto;

    @InjectMocks
    private ValidacaoPetDisponivel oValidacaoPetDisponivel;

    @Test
    void deveriaPermitirSolicitacaoDeAdocaoPet() {
        // ARRANGE
        BDDMockito.given(oPetRepository.getReferenceById(oSolicitacaoAdocaoDto.idPet())).willReturn(oPet);
        BDDMockito.given(oPet.getAdotado()).willReturn(Boolean.FALSE);

        // ASSERT + ACT
        Assertions.assertDoesNotThrow(() -> oValidacaoPetDisponivel.validar(oSolicitacaoAdocaoDto));
    }

    @Test
    void naoDeveriaPermitirSolicitacaoDeAdocaoPet() {
        // ARRANGE
        BDDMockito.given(oPetRepository.getReferenceById(oSolicitacaoAdocaoDto.idPet())).willReturn(oPet);
        BDDMockito.given(oPet.getAdotado()).willReturn(Boolean.TRUE);

        // ASSERT + ACT
        Assertions.assertThrows(
                ValidacaoException.class,
                () -> oValidacaoPetDisponivel.validar(oSolicitacaoAdocaoDto)
        );
    }
}