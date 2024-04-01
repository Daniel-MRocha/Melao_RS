package cidades;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Municipio {

    private String municipio;
    private Map<String,String> dados = new TreeMap<>();

    public void insere(String[] cab ,String[] hectares){
        this.municipio = hectares[0];

        for(int c =4;c<cab.length;c++){
            var testaSemDados = hectares[c];

            if(testaSemDados.equals("...") || testaSemDados.equals("-")){
                dados.put(cab[c], "SemDados");
            }else {
                dados.put(cab[c], hectares[c]);
            }
        }
    }


    public String getMunicipio() {
        return municipio;
    }
    public Map<String,String> getDados(){
        return this.dados;
    }
    public void todosOsAnos(){
        System.out.println("Municipio de " + this.municipio);

        for(String key : dados.keySet()){
            System.out.println( key +" : " + dados.get(key) );
        }
    }

}
