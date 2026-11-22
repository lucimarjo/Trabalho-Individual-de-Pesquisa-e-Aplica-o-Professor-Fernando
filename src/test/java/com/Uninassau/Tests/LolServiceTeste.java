package com.Uninassau.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Uninassau.Tests.entity.Campeao;
import com.Uninassau.Tests.repository.CampeaoRepository;
import com.Uninassau.Tests.service.LolService;

@ExtendWith(MockitoExtension.class)
public class LolServiceTeste {

    /* Cria um objeto mock */
    @Mock 
    private CampeaoRepository repository;

    /*Injeta o objeto mock na classe */
    @InjectMocks 
    private LolService service;

    @Test 
    void CadastrarCampeaoNomeValido(){
        // Arrange - Preparação do teste
        Campeao campeao = new Campeao();
        /* Normalmente criaria um arquivo com os nomes e roles 
        do jogo mas pra ser mais rápido fica as magic strings */
        campeao.setNome("Sona");
        campeao.setRole("Sup");

        when(repository.salvar(campeao)).thenReturn(campeao);

        // Act - Executando o método
        /* Aqui é onde acontece o código do service e é salvo em resultado que será usado para verificação*/
        Campeao resultado = service.cadastrar(campeao);

        // Assert - Verificando o resultado
        assertNotNull(resultado); // verifica se o resultado não é nulo
        assertEquals(campeao, resultado); //verifica se a variável campeao é igual ao resultado da função cadastrar
        verify(repository, times(1)).salvar(campeao); //verifica se o método .salvar foi chamado exatamente 1 vez
    }
}