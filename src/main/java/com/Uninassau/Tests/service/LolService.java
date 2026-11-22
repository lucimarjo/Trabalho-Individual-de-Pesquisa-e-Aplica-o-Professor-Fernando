package com.Uninassau.Tests.service;

import org.springframework.stereotype.Service;

import com.Uninassau.Tests.entity.Campeao;
import com.Uninassau.Tests.repository.CampeaoRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class LolService {
    private final CampeaoRepository repository;

    public Campeao cadastrar(Campeao campeao){
        if (campeao.getNome() == null || campeao.getNome().isBlank()){
            throw new IllegalArgumentException("Dê o nome do boneco");
        }
        return repository.salvar(campeao);
    }
}