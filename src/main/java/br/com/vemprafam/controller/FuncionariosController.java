package br.com.vemprafam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.vemprafam.dao.DaoFuncionario;
import br.com.vemprafam.pojo.Funcionario;

@Controller
@RequestMapping("/funcionarios")
public class FuncionariosController {
	@Autowired
	DaoFuncionario dao;
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
	@GetMapping("/exclusao")
	public String showDelete() {
		return "delete-funcionario";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam int re) {
		Funcionario f = dao.buscarPeloRe(re);
		dao.delete(f);
		return "funcionarios";
	}
	@GetMapping("/busca")
	public String showBusca() {
		return "buscar-funcionario";
	}
	@GetMapping("/formUpdate")
	public String formUpdate(@RequestParam int re, Model model) {
		Funcionario f = dao.buscarPeloRe(re);
		model.addAttribute("funcionario", f);
		return "alterar-funcionario";
	}
	@PostMapping("/update")
	public String update(@ModelAttribute Funcionario f) {
		dao.update(f);
		return "funcionarios";
	}
}
