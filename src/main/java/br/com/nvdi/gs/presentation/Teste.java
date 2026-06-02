package br.com.nvdi.gs.presentation;

import br.com.nvdi.gs.application.APIApplication;
import br.com.nvdi.gs.application.DadosAtuais;
import br.com.nvdi.gs.domain.Fazenda;
import br.com.nvdi.gs.domain.FazendaFrutas;
import br.com.nvdi.gs.domain.FazendaGraos;
import br.com.nvdi.gs.infrastructure.Repository;

public class Teste {

    public static void main(String[] args) {

        APIApplication app = new APIApplication();
        Repository repo = new Repository();

        Fazenda f = new FazendaGraos("casta", "Carlos", 13.410001, 14.510001);
        repo.salvar(f);



    }
}
