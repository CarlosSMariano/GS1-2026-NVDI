package br.com.nvdi.gs.infrastructure;

import br.com.nvdi.gs.domain.Fazenda;

import java.util.ArrayList;

public class Repository {
    private ArrayList<Fazenda> fazendas;

    public Repository() {
        this.fazendas = new ArrayList<Fazenda>();
    }

    public void salvar(Fazenda fazenda){
        fazenda.setId(this.fazendas.size() + 1);
        this.fazendas.add(fazenda);
    }

    public Fazenda encontrarFazenda(int id){
        return fazendas.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    public ArrayList<Fazenda> exibirFazendas() {
        return fazendas;
    }

    public void remover(int id){

        try{
            this.fazendas.removeIf(f -> f.getId() == id);
        }catch (Exception e){
            System.out.println("! Erro fazenda não encontrada!");
            return;
        }

        for(int i = 0; i < this.fazendas.size(); i++){
            fazendas.get(i).setId(this.fazendas.size() + 1);
        }

        System.out.println("--> Fazenda removida com sucesso!!");
    }


}
