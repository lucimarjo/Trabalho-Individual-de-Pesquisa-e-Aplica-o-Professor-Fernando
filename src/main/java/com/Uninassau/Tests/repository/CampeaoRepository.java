package com.Uninassau.Tests.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Uninassau.Tests.entity.Campeao;

@Repository 
public class CampeaoRepository {
    private final List<Campeao> campeoes = new ArrayList<>();

    public Campeao salvar(Campeao campeao){
        campeoes.add(campeao);
        return campeao;
    }
}