package br.com.utfpr.hotelpet.controller;

import br.com.utfpr.hotelpet.model.Tutor;
import br.com.utfpr.hotelpet.model.Pets;
import br.com.utfpr.hotelpet.repository.PetsRepository;
import br.com.utfpr.hotelpet.repository.TutorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TutorController {

    @Autowired
    private TutorRepository repository;
    @Autowired
    private PetsRepository petsRepository;

    @GetMapping("/tutor/cadTutor")
    public String cadastrar(Model model) {
        model.addAttribute("tutor", new Tutor());
        model.addAttribute("tituloPagina", "Cadastrar Tutor");
        model.addAttribute("acaoFormulario", "/tutor/salvar");
        
        return "tutor/cadTutor";
    }

    @PostMapping("/tutor/salvar")
    public String salvar(@ModelAttribute Tutor tutor) {
        repository.salvar(tutor);
        return "redirect:/tutor/tutor";
    }

    @GetMapping("/tutor/tutor")
    public String listar(Model model) {
        List<Tutor> tutores = repository.listar();
        model.addAttribute("tutores", tutores);
        return "tutor/tutor";
    }

    @PostMapping("/tutor/excluir")
    public String excluir(@ModelAttribute("id") String id) {
        repository.excluir(id);
        return "redirect:/tutor/tutor";
    }
    
    @GetMapping("/tutor/tutorPets/{nomeTutor}")
    public String listarPetsTutor(@PathVariable String nomeTutor, Model model){
        List<Pets> listaPetsTutor = petsRepository.listarPetsTutor(nomeTutor);
        
        model.addAttribute("listaPetsTutor", listaPetsTutor);
        model.addAttribute("nomeTutor", nomeTutor);
        
        return "tutor/tutorPets";
        
    }
    
    @GetMapping("/tutor/cadTutor/{idTutor}")
    public String editar(@PathVariable String idTutor, Model model) {
        Tutor tutor =  repository.editar(idTutor);
        model.addAttribute("tutor", tutor);
        model.addAttribute("tituloPagina", "Editar Tutor");
        model.addAttribute("acaoFormulario", "/tutor/salvar");
        
        return "tutor/cadTutor";
        
    }

}
