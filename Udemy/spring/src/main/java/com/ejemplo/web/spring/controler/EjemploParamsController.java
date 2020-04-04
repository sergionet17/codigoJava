package com.ejemplo.web.spring.controler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class EjemploParamsController {
	@GetMapping("/")
	public String index() {

		return "params/index";
	}

	@GetMapping("/string")
	public String param(@RequestParam(name = "texto", required = false) String texto, Model model) {
		model.addAttribute("resultado", "el texto enviado es :" + texto);
		return "params/ver";
	}

	@GetMapping("/mix-params")
	public String param(HttpServletRequest request, Model model) {
		String saludo = request.getParameter("saludo");
		

		Integer saludoEntero = 0;
		try {
			saludoEntero = Integer.parseInt(request.getParameter("saludoEntero"));
		} catch (NumberFormatException e) {
			saludoEntero = 0;
		}
		model.addAttribute("resultado", "el texto enviado es :" + saludo + "y el numero es : " + saludoEntero);
		return "params/ver";
	}

}
