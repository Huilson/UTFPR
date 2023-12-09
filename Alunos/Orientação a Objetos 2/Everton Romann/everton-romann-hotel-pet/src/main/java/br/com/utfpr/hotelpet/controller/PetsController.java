package br.com.utfpr.hotelpet.controller;

import br.com.utfpr.hotelpet.model.Andar;
import br.com.utfpr.hotelpet.model.Pets;
import br.com.utfpr.hotelpet.model.Tutor;
import br.com.utfpr.hotelpet.repository.AndarRepository;
import br.com.utfpr.hotelpet.repository.PetsRepository;
import br.com.utfpr.hotelpet.repository.TutorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetsController {

    @Autowired
    private PetsRepository repository;
    @Autowired
    private AndarRepository andarRepository;
    @Autowired
    private TutorRepository tutorRepository;

    @GetMapping("/pets/cadPets")
    public String cadastrar(Model model) {
        List<Andar> andares = andarRepository.listar();
        List<Tutor> tutores = tutorRepository.listar();

        List<String> especiesUnicas = new ArrayList<>();

        for (Andar andar : andares) {
            String especie = andar.getEspecieAndar();
            if (!especiesUnicas.contains(especie)) {
                especiesUnicas.add(especie);
            }
        }

        model.addAttribute("tutores", tutores);
        model.addAttribute("andares", especiesUnicas);
        model.addAttribute("pets", new Pets());
        model.addAttribute("tituloPagina", "Cadastrar Pet");
        model.addAttribute("acaoFormulario", "/pets/salvar");

        return "pets/cadPets";
    }

    @PostMapping("/pets/salvar")
    public String salvar(@ModelAttribute Pets pet) {
        repository.salvar(pet);
        return "redirect:/pets/pets";
    }

    @GetMapping("/pets/pets")
    public String listar(Model model) {
        List<Pets> pets = repository.listar();

        for (Pets pet : pets) {
            String tutorId = pet.getTutorPet();
            String nomeTutor = tutorRepository.obterNomeTutorPorId(tutorId);
            pet.setTutorPet(nomeTutor);
        }

        model.addAttribute("pets", pets);
        return "pets/pets";
    }

    @PostMapping("/pets/excluir")
    public String excluir(@ModelAttribute("id") String id) {
        repository.excluir(id);
        return "redirect:/pets/pets";
    }

    @GetMapping("/pets/cadPets/{idPet}")
    public String editar(@PathVariable String idPet, Model model) {
        List<Andar> andares = andarRepository.listar();
        List<Tutor> tutores = tutorRepository.listar();
        Pets pets = repository.editar(idPet);

        model.addAttribute("pets", pets);
        model.addAttribute("andares", andares);
        model.addAttribute("tutores", tutores);
        model.addAttribute("tituloPagina", "Editar Pet");
        model.addAttribute("acaoFormulario", "/pets/salvar");

        return "pets/cadPets";

    }

}
