package com.deni.gunawan.sistemmanajemenricheesefactory.controllers;

import com.deni.gunawan.sistemmanajemenricheesefactory.entity.Vendors;
import com.deni.gunawan.sistemmanajemenricheesefactory.repository.VendorRepo;
import com.deni.gunawan.sistemmanajemenricheesefactory.services.VendorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/vendors")
@Slf4j
public class VendorController {


    private VendorRepo vendorRepo;
    private VendorService vendorService;

    @GetMapping(value = "/index")
    public String getList(ModelMap map, Pageable pageable) {
        map.addAttribute("listVendor", vendorRepo.findAll(pageable));
        return "pages/vendor/index";
    }

    @GetMapping(value = "/form")
    public String getForm(ModelMap map) {
        Vendors vendor = new Vendors();
        map.addAttribute("vendor", vendor);
        return "pages/vendor/form";
    }

    @GetMapping(value = "/form/{id}")
    public String showEditForm(Model model, @PathVariable(value = "id") Long id) {
        try {
            Vendors vendor = vendorService.findById(id)
                    .orElseThrow(()
                            -> new IllegalArgumentException("Gagal Get Data Id : " + id));
            model.addAttribute("vendor", vendor);
            return "pages/vendor/edit";
        } catch (Exception e) {
            return "pages/vendor/index";
        }
    }

    @PostMapping("/update/{id}")
    public String updateData(Model model, @ModelAttribute(value = "vendor") Vendors vendor) {
        {
            model.addAttribute("vendor", vendor);
            vendorRepo.save(vendor);
            return "redirect:/vendors/index";
        }
    }

    @PostMapping(value = "/submit")
    public String saved(@Valid @ModelAttribute Vendors vendor, BindingResult result) {
        if (result.hasErrors()) {
            return "pages/vendor/form";
        }
        vendorService.save(vendor);
        return "redirect:/vendors/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String remove(@PathVariable(value = "id") Long id,
                         RedirectAttributes redirectAttributes) {
        this.vendorService.delete(id);
        redirectAttributes.addFlashAttribute("alertSuccess", "Data Berhasil Remove");
        return "redirect:/vendors/index";
    }
}
