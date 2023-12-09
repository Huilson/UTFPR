package br.com.utfpr.hotelpet.controller;

import br.com.utfpr.hotelpet.model.Andar;
import br.com.utfpr.hotelpet.repository.AndarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AndarController {
    
    @Autowired
    private AndarRepository repository;
    
    @GetMapping("/andar/cadAndar")
    public String cadastrar(Model model){
        model.addAttribute("andar", new Andar());
        model.addAttribute("tituloPagina", "Cadastrar Andar");
        model.addAttribute("acaoFormulario", "/andar/salvar");
        return "andar/cadAndar";
    }
    
    @PostMapping("/andar/salvar")
    public String salvar(@ModelAttribute Andar andar){
        repository.salvar(andar);
        return "redirect:/andar/andar";
    }
    
    @GetMapping("/andar/andar")
    public String listar(Model model){
        List<Andar> andares = repository.listar();
        model.addAttribute("andares", andares);
        return"andar/andar";
    }
    
    @PostMapping("/andar/excluir")
    public String excluir(@ModelAttribute("id") String id) {
        repository.excluir(id);
        return "redirect:/andar/andar";
    }
    
    @GetMapping("/andar/cadAndar/{idAndar}")
    public String editar(@PathVariable String idAndar, Model model) {
        Andar andar = repository.obterId(idAndar);
        
        model.addAttribute("andar", andar);
        model.addAttribute("tituloPagina", "Editar Andar");
        model.addAttribute("acaoFormulario", "/andar/salvar");
        
        return "andar/cadAndar";
        
    }
}
