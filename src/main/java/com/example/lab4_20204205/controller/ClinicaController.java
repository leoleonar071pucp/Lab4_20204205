package com.example.lab4_20204205.controller;
import com.example.lab4_20204205.entity.Clinicas;
import com.example.lab4_20204205.repository.ClinicaRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.util.Optional;


@Controller
@RequestMapping("/Clinica")
public class ClinicaController {

    final ClinicaRepository ClinicaRepository;
    private final ClinicaRepository clinicaRepository;


    public ClinicaController(ClinicaRepository ClinicaRepository, ClinicaRepository clinicaRepository) {
        this.ClinicaRepository = ClinicaRepository;
        this.clinicaRepository = clinicaRepository;
    }

    @GetMapping(value = {"", "/"})
    public String listaProductos(Model model) {
        model.addAttribute("listaHospital", ClinicaRepository.findAll());
        return "Clinica/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(Model model, @ModelAttribute("product") Clinicas Clinicas) {
        return "product/editFrm";
    }

    @PostMapping("/save")
    public String guardarProducto(RedirectAttributes attr, Model model,
                                  @ModelAttribute("Clinicas") @Valid Clinicas Clinicas, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) { //si no hay errores, se realiza el flujo normal

            if (Clinicas.getName().equals("gaseosa")) {
                model.addAttribute("msg", "Error al crear producto");

                return "Clinica/editFrm";
            } else {
                if (Clinicas.getId() == 0) {
                    attr.addFlashAttribute("msg", "Producto creado exitosamente");
                } else {
                    attr.addFlashAttribute("msg", "Producto actualizado exitosamente");
                }
                clinicaRepository.save(Clinicas);
                return "redirect:/Clinica";
            }

        } else { //hay al menos 1 error

            return "Clinica/editFrm";
        }
    }

    @GetMapping("/edit")
    public String editarClinica(@ModelAttribute("Clinicas") Clinicas Clinicas,
                                      Model model, @RequestParam("id") int id) {

        Optional<Clinicas> optProduct = clinicaRepository.findById(id);

        if (optProduct.isPresent()) {
            Clinicas = optProduct.get();
            model.addAttribute("Clinicas", Clinicas);

            return "Clinica/editFrm";
        } else {
            return "redirect:/Clinica";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(@RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Clinicas> optProduct = clinicaRepository.findById(id);

        if (optProduct.isPresent()) {
            clinicaRepository.deleteById(id);
            attr.addFlashAttribute("msg", "clinica borrado exitosamente");
        }
        return "redirect:/Clinica";

    }

   /* @InitBinder("product")
    public void validador(WebDataBinder binder) {

        binder.registerCustomEditor(BigDecimal.class, "unitprice", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try{
                    BigDecimal bigDecimal = new BigDecimal(text);
                    this.setValue(bigDecimal);
                }catch (NumberFormatException e){
                    this.setValue(0);
                }
            }
        });
    }*/


}


