import cidades.Municipio;
import relatorios.I_htmlBuilder;
import relatorios.PaginaHtml;
import relatorios.Relatorio;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {

        System.out.println("Dados do melão");
        System.out.println("--------------");

        Path arquivo = Path.of("src/recurso/dee-1738_melao.csv");
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

            //todo testes
            Municipio teste = muniList.stream()
                    .filter(ele -> ele.getMunicipio().equals("Agudo"))
                    .findAny().get();

            Relatorio rel = new Relatorio();
            rel.criaDiretorio();

            I_htmlBuilder i_B = new PaginaHtml();
            i_B.setDeMunicipio(teste);
            i_B.criaPaginanoPool();
            System.out.println(i_B.toString());
        }catch (IOException e){
            System.out.println("erro");
        }
        }




public static String[] trataCab(BufferedReader br)throws IOException {

        String cabLimpo =  br.readLine().replace("\""," ").trim();
    System.out.println(cabLimpo);
        String[] cabecalho = cabLimpo.split(",");
            for (int c = 0; c < cabecalho.length; c++) {
                if (cabecalho[c].length() > 63) {
                cabecalho[c] = cabecalho[c].substring(55, 59);
                }
            }
    return cabecalho;
    }
}


