package br.com.vemprafam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.vemprafam.dao.DaoAluno;
import br.com.vemprafam.pojo.Aluno;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
	@Autowired
	DaoAluno dao;
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

	@PostMapping
	public String insert(@ModelAttribute Aluno a) {
		dao.insert(a);
		return "redirect:alunos";
	}
	@GetMapping("/list")
	public String getList(Model model) {
		List<Aluno> alunos = dao.getLista();
		model.addAttribute("alunos", alunos);
		return "alunos-list";
	}
	@GetMapping("/exclusao")
	public String showDelete() {
		return "delete-aluno";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam int ra) {
		Aluno a = dao.buscarPeloRa(ra);
		dao.delete(a);
		return "alunos";
	}
	@GetMapping("/busca")
	public String showBusca() {
		return "buscar-aluno";
	}
	@GetMapping("/formUpdate")
	public String formUpdate(@RequestParam int ra, Model model) {
		Aluno a = dao.buscarPeloRa(ra);
		model.addAttribute("aluno", a);
		return "alterar-aluno";
	}
	@PostMapping("/update")
	public String update(@ModelAttribute Aluno a) {
		dao.update(a);
		return "alunos";
	}
}
