package br.com.utfpr.hotelpet.controller;

import br.com.utfpr.hotelpet.model.Andar;
import br.com.utfpr.hotelpet.model.Pets;
import br.com.utfpr.hotelpet.model.Checkin;
import br.com.utfpr.hotelpet.repository.AndarRepository;
import br.com.utfpr.hotelpet.repository.CheckinRepository;
import br.com.utfpr.hotelpet.repository.PetsRepository;
import br.com.utfpr.hotelpet.repository.TutorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckinController {

    @Autowired
    private CheckinRepository repository;
    @Autowired
    private AndarRepository andarRepository;
    @Autowired
    private PetsRepository petsRepository;
    @Autowired
    private TutorRepository tutorRepository;

    @GetMapping("/checkin/cadCheckin")
    public String cadastrar(Model model) {
        List<Andar> andares = andarRepository.listar();
        List<Pets> pets = petsRepository.listar();

        for (Pets pet : pets) {
            String tutorId = pet.getTutorPet();
            String nomeTutor = tutorRepository.obterNomeTutorPorId(tutorId);
            pet.setTutorPet(nomeTutor);
        }

        model.addAttribute("andares", andares);
        model.addAttribute("pets", pets);
        model.addAttribute("checkin", new Checkin());
        return "checkin/cadCheckin";
    }

    @PostMapping("/checkin/salvar")
    public String salvar(@ModelAttribute Checkin checkin) {
        repository.salvar(checkin);
        return "redirect:/checkin/checkin";
    }

    @GetMapping("/checkin/checkin")
    public String listar(Model model) {
        List<Checkin> checkins = repository.listar();
        model.addAttribute("checkins", checkins);
        return "checkin/checkin";
    }

@GetMapping("/getValorDiaria")
@ResponseBody
public ResponseEntity<?> getValorDiaria(@RequestParam("numAndar") int numAndar) {
    try {
        Double valorDiaria = andarRepository.obterValorAndar(numAndar);

        if (valorDiaria != null) {
            return ResponseEntity.ok(valorDiaria);
        } else {
            String mensagemErro = "Valor da diária não encontrado para o andar: " + numAndar;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
        }
    } catch (Exception e) {
        String mensagemErro = "Ocorreu um erro ao obter o valor da diária.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensagemErro);
    }
}




}
