import cidades.Municipio;
import relatorios.PaginaHtml;
import relatorios.Relatorio;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {

        long agora = System.currentTimeMillis();

        System.out.println("Dados do melão");
        System.out.println("--------------");
        System.out.println("Gerando relatórios em html---------||");

        Path arquivo = Path.of("./dee-1738.csv");
        BufferedReader br = new BufferedReader(new FileReader(arquivo.toFile()));
        br.readLine();

        List<Municipio> muniList = new ArrayList<>();
        try {
            String[] cab = trataCab(br);

            String auxLinhas = br.readLine();
            while(auxLinhas!=null){

                String[] auxArr = auxLinhas.replace("\"","").trim().split(",");

                Municipio nAux = new Municipio();
                nAux.insere(cab,auxArr);
                muniList.add(nAux);
                auxLinhas = br.readLine();
            }

            Relatorio montaDiretorio = new Relatorio();
            montaDiretorio.criaDiretorio();

            muniList.stream()
                    .forEach(municipio ->{
                        PaginaHtml pg = new PaginaHtml();
                        pg.setMunicipio(municipio);
                        pg.criaArquivoPagina();
                    });

            long fim = System.currentTimeMillis();
            System.out.println("Operação concluida em " + (fim - agora) + " milisegundos");

        }catch (IOException e){
            System.out.println("erro");
        }
    }




public static String[] trataCab(BufferedReader br)throws IOException {

        String cabLimpo =  br.readLine().replace("\""," ").trim();
        String[] cabecalho = cabLimpo.split(",");
            for (int c = 0; c < cabecalho.length; c++) {
                if (cabecalho[c].length() > 63) {
                cabecalho[c] = cabecalho[c].substring(55, 59);
                }
            }
    return cabecalho;
    }
}


