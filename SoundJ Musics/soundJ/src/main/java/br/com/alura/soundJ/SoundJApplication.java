package br.com.alura.soundJ;

import br.com.alura.soundJ.principal.Principal;
import br.com.alura.soundJ.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoundJApplication implements CommandLineRunner {

    @Autowired
    private ArtistaRepository repositorio;

    public static void main(String[] args) {
        SpringApplication.run(SoundJApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repositorio);
        principal.exibeMenu();

    }
}
