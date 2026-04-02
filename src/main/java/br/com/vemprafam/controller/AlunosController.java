package br.com.vemprafam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.vemprafam.dao.DaoAluno;
import br.com.vemprafam.pojo.Aluno;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
	@GetMapping
	public String showAlunosHomeVazio(Model model) {
		return "alunos";
	}
	@GetMapping("/")
	public String showAlunosHome(Model model) {
		return "alunos";
	}
	@GetMapping("/new")
	public String showAlunosForm(Model model) {
		Aluno a = new Aluno();
		model.addAttribute("aluno", a);
		return "create-aluno";
	}
	DaoAluno dao = new DaoAluno();
	@PostMapping
	public String insert(@ModelAttribute Aluno a) {
		dao.insert(a);
		return "redirect:alunos";
	}

}
