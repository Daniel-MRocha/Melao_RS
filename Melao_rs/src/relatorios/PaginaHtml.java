package relatorios;

import cidades.Municipio;


import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class PaginaHtml {

    private String municipio ;
    private Map<String,String> dados;
    private String estruturaHtml;



    public void criaPagina(){

        Path caminho = Path.of("C:\\Users\\Daniel M\\Documents\\DataCienceRs\\Melao_rs\\Relatorio\\"+ municipio + ".html");
        try {
            Files.createFile(caminho);
            criaHtml();
            FileWriter fw = new FileWriter(caminho.toFile());
            fw.write(estruturaHtml);
            fw.close();
        }catch (Exception e){
            System.out.println("Erro na criação do arquivo");
            System.out.println(e);
        }
    }

    public void criaHtml(){
        this.estruturaHtml = """
                <!DOCTYPE HTML>
                <html lang="pt-br">
                       <head> """
                         + "  <title>Municipio de "+municipio+"</title>" +
                        """    
                       <meta charset="utf-8">
                       </head>
                <body>
                <h1>Dados</h1><br>"""
                + dadosDispostos() +
                """
                </body>
                </html>
                """;
    }

    public void setMunicipio(Municipio mun){
        this.municipio = mun.getMunicipio();
        this.dados = mun.getDados();
    }

    public String dadosDispostos(){

        StringBuffer sb = new StringBuffer();
        for(String ano : dados.keySet()){
            sb.append("<h3> "+ ano +" :--> "+ dados.get(ano)+"</h3> <br>");
        }

        return sb.toString();
    }
}
