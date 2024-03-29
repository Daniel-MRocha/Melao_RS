import cidades.Municipio;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

                String[] auxArr = auxLinhas.split(",");



                Municipio nAux = new Municipio();
                nAux.insere(cab,auxArr);
                muniList.add(nAux);
                auxLinhas = br.readLine();

            }

            muniList.stream()
                    .filter(cid -> cid.getDados().get("1995") != "SemDados")
                    .forEach(cid ->{
                        System.out.print(cid.getMunicipio());
                                var aux = cid.getDados();
                                for(String key : aux.keySet()){
                                    System.out.print("| "+ key + " " + aux.get(key));
                                }
                        System.out.println("\n");
                            });

        }catch (IOException e){
            System.out.println("erro");
        }
        }




public static String[] trataCab(BufferedReader br)throws IOException {
    String[] cabecalho = br.readLine().split(",");
    for (int c = 0; c < cabecalho.length; c++) {
        if (cabecalho[c].length() > 63) {
            cabecalho[c] = cabecalho[c].substring(55, 59);
        }
}
    return cabecalho;
}
}


