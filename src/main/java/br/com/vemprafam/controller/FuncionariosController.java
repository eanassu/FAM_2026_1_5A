package br.com.vemprafam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.vemprafam.dao.DaoFuncionario;
import br.com.vemprafam.pojo.Funcionario;

@Controller
@RequestMapping("/funcionarios")
public class FuncionariosController {
	@GetMapping
	public String showFuncHomeVazio(Model model) {
		return "funcionarios";
	}
	@GetMapping("/")
	public String showFuncHome(Model model) {
		return "funcionarios";
	}
	@GetMapping("/new")
	public String showFuncForm(Model model) {
		Funcionario f = new Funcionario();
		model.addAttribute("funcionario", f);
		return "create-func";
	}
	DaoFuncionario dao = new DaoFuncionario();
	@PostMapping
	public String insert(@ModelAttribute Funcionario f) {
		dao.insert(f);
		return "redirect:funcionarios";
	}
	@GetMapping("/list")
	public String getList(Model model) {
		List<Funcionario> funcionarios = dao.getLista();
		model.addAttribute("funcionarios", funcionarios);
		return "funcionarios-list";
	}

}
