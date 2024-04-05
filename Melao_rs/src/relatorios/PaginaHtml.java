package relatorios;

import cidades.Municipio;


import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.stream.Collectors;

public class PaginaHtml{

    private String municipio;
    private Map<String, String> dados;
    private String estruturaHtml;


    public void setMunicipio(Municipio mun) {
        this.municipio = mun.getMunicipio();
        this.dados = mun.getDados();
    }

    public void criaArquivoPagina() {

        Path caminho = Path.of(".\\Relatorio\\" + municipio + ".html");

        try {
            Files.createFile(caminho);
            montaHtml();
            FileWriter fw = new FileWriter(caminho.toFile());
            fw.write(estruturaHtml);
            fw.close();
        } catch (Exception e) {
            System.out.println("Erro na criação do arquivo");
            System.out.println(e);
        }
    }

    public String dadosDispostos() {
        StringBuffer sb = new StringBuffer();
        for (String ano : dados.keySet()) {
            if (dados.get(ano).equals("SemDados")) {
                sb.append("""
                        <article class="conjunto">
                        """
                        + "<span class=\"ano\">" + ano + "</span><span class=\"hectares\">" + "-" + "</span>" +
                        """           
                                            </article>
                                """);
            } else {
                sb.append("""
                        <article class="conjunto">
                        """
                        + "<span class=\"ano\">" + ano + "</span><span class=\"hectares\">" + dados.get(ano) + "</span>" +
                        """           
                                            </article>
                                """);
            }
        }
        return sb.toString();
    }

    public void montaHtml() {
        this.estruturaHtml = """
                <!DOCTYPE html>
                <html lang="pt-br">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <meta name="Author" content="Daniel Machado da rocha">
                    """
                + "<title>" + this.municipio + "</title>" +
                """ 
                        </head>
                        """
                + montaCss() +

                """
                        <body>
                               <main>
                               """
                + "<Span id=\"municipio\"><h2>" + this.municipio + "</h2></Span>" +
                """
                                   <h4>Dados da cultura de melão no período de 1974 à 2021</h4>
                                   <section id="dados">
                        """
                + dadosDispostos() +
                """
                                 </section>
                                 <footer>
                                        <fieldset>
                                                <legend>Dados estatísticos</legend>
                        """
                + montaFooter() +
                """
                                        </fieldset>
                                 </footer>
                                    
                                    </main>
                                </body>
                                </html>
                        """;
    }

    public String montaCss() {
        return """
                <style>
                    *{
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box;
                    }
                    body{
                        display: flex;
                        justify-content: center;
                        position: relative;
                        width: 100vw;
                        height: 100vh;
                                
                        &::before{
                        content: '';
                        position: absolute;
                        top: 0px;
                        bottom: 0px;
                        left: 0px;
                        right: 0px;
                        background-image: url(../src/recurso/melaoBackground2.jpg);
                        background-repeat: no-repeat;
                        background-size: cover;
                        opacity: .25;
                        }
                    }
                                
                    main{
                        margin-top: 20px;
                        border: 3px solid green;
                        border-radius: 5px;
                        width: 60vw;
                        height: 90vh;
                        display: flex;
                        flex-direction: column;
                        align-items: start;
                    }
                    section{
                        position: relative;
                        width: 100%;
                        display: grid;
                        grid-template-columns: repeat(6,1fr);
                        gap: 12px;
                        margin: 10px;
                    }
                                
                    #municipio > h2{
                        text-decoration: dotted;
                        color: rgb(21, 103, 21);
                    }
                    .conjunto{
                        border: 3px solid rgb(7, 91, 7);
                        border-radius: 3px;
                        background-color: rgb(41, 152, 41);
                        width: 100px;
                        height: 30px;
                        display: flex;
                        justify-content: space-between;
                    }
                                
                    .ano{
                        color: whitesmoke;
                        margin-left: 0;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                         position: relative;
                         width: 50%;  
                    }
                    .hectares{
                        background-color: rgb(191, 215, 194);
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        position: relative;
                        width: 50%;
                    }
                                
                     footer{
                             position: relative;
                             width: 100%;
                             height: 100%;
                             display: flex;
                             flex-direction: column;
                             padding-top: 5px;
                             padding-left: 5px;
                             font-size: large;
                             font-weight: 700;
                     }
                         .estatisticaLabel{
                             background-color: rgb(138, 173, 142);
                             width: 240px;
                             height: 20px;
                             border-radius: 3px;
                             font-size: medium;
                             font-weight: 300;
                             font-family: 'Times New Roman', Times, serif;
                             margin: 7px;
                             display: flex;
                             padding-left: 7px;
                             flex-direction: row;
                             justify-content: space-between;
                         }
                         .estatisticas{
                             color: bisque;
                             margin-right: 3px;
                         }
                </style>
                """;
    }

    public String montaFooter() {
        StringBuffer sb = new StringBuffer();
        DecimalFormat df = new DecimalFormat("#.#");

        var anosCultivados = dados.values().stream()
                .filter(ele -> !ele.equals("SemDados"))
                .count();

        var estatisticas = dados.values().stream()
                .filter(ano -> !ano.equals("SemDados"))
                .map(Integer::parseInt)
                .collect(Collectors.summarizingInt(ano -> ano));

        var media = estatisticas.getAverage();

        sb.append("<span class=\"estatisticaLabel\">Quantidade total de hectares<span class=\"estatisticas\">" + estatisticas.getSum() + "</span></span>");
        sb.append("<span class=\"estatisticaLabel\">Quantidade de anos cultivados<span class=\"estatisticas\">" + anosCultivados + "</span></span>");
        sb.append("<span class=\"estatisticaLabel\">Maior quantidade em 1 ano<span class=\"estatisticas\">" + estatisticas.getMax() + "</span></span>");
        sb.append("<span class=\"estatisticaLabel\">Menor quantidade em 1 ano<span class=\"estatisticas\">" + estatisticas.getMin() + "</span></span>");
        sb.append("<span class=\"estatisticaLabel\">Media (em anos plantados)<span class=\"estatisticas\">" + df.format(media) + "</span></span>");

        return sb.toString();
    }

}

