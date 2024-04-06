package relatorios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Relatorio {
    public void criaDiretorio() {
        try {
            Files.createDirectory(Path.of("Relatorio").toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro na parte de diretório");
        }
    }
}
