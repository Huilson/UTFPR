package br.edu.utfpr.jogadaforca.controller;

import br.edu.utfpr.jogadaforca.model.JogoDaForca;
import br.edu.utfpr.jogadaforca.service.Service;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@SessionAttributes("jogoDaForca")
public class JogoDaForcaController {

    private Service service = new Service();
    private Gson gson = new Gson();

    @GetMapping("/jogo_da_forca")
    public String jogoDaForca(Model model) {
        JogoDaForca jogoDaForca = new JogoDaForca();
        model.addAttribute("jogoDaForca", jogoDaForca);

        return "jogo_da_forca";
    }

    @RequestMapping(value = "/letra", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String Letra(@RequestParam("letra") String letra, Model model) {
        JogoDaForca jogoDaForca = (JogoDaForca) model.getAttribute("jogoDaForca");
        jogoDaForca.setUltLetra(letra);
        jogoDaForca.setSituacao("ok");
        if (!service.checkLetra(jogoDaForca.getPalavra(), letra)) {
            jogoDaForca.setUltLetraAcertou(false);
            jogoDaForca.setTentativasRestantes(jogoDaForca.getTentativasRestantes()-1);
            if (jogoDaForca.getTentativasRestantes()<1){
                jogoDaForca.setSituacao("perdeu");
            }
        } else {
            jogoDaForca.setUltLetraAcertou(true);
            jogoDaForca.setLetrasRestantes(service.checkLetrasRestantes(jogoDaForca, letra));
            if (jogoDaForca.getLetrasRestantes() == 0){
                jogoDaForca.setSituacao("ganhou");
            }
        }
        System.out.println(jogoDaForca.toString());
        return gson.toJson(jogoDaForca);
    }

    @RequestMapping(value = "/dificuldade", method = RequestMethod.POST)
    public @ResponseBody String Dificuldade(@RequestParam("dificuldade") String dificuldade, Model model) throws IOException {
        JogoDaForca jogoDaForca = new JogoDaForca();
        jogoDaForca.setTentativasRestantes(10);
        jogoDaForca.setDificuldade(dificuldade);
        jogoDaForca.setPalavra(service.getPalavraByDificuldade(dificuldade));
        jogoDaForca.setLetrasRestantes(jogoDaForca.getPalavra().length());
        model.addAttribute("jogoDaForca", jogoDaForca);

        return gson.toJson(jogoDaForca);
    }

}
