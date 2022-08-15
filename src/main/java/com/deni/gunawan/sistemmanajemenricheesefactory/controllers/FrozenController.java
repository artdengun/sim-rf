package com.deni.gunawan.sistemmanajemenricheesefactory.controllers;

import com.deni.gunawan.sistemmanajemenricheesefactory.entity.Frozen;
import com.deni.gunawan.sistemmanajemenricheesefactory.repository.FrozenRepo;
import com.deni.gunawan.sistemmanajemenricheesefactory.repository.UserRepo;
import com.deni.gunawan.sistemmanajemenricheesefactory.repository.VendorRepo;
import com.deni.gunawan.sistemmanajemenricheesefactory.services.FrozenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/frozen")
@Slf4j
public class FrozenController {

        private FrozenService frozenService;
        private FrozenRepo frozenRepo;
        private VendorRepo vendorRepo;
        private UserRepo usersRepo;

        @GetMapping(value = "/index")
        public String getList(ModelMap map, Pageable pageable){
            map.addAttribute("list", frozenRepo.findAll(pageable));
            map.addAttribute("listUsers", usersRepo.findAll());
            map.addAttribute("listVendors", vendorRepo.findAll());
            return "pages/frozen/index";
        }

        @GetMapping(value = "/form")
        public String getForm(ModelMap map){
            Frozen frozen = new Frozen();
            map.addAttribute("frozen", frozen);
            map.addAttribute("users", usersRepo.findAll());
            map.addAttribute("vendors", vendorRepo.findAll());
            return "pages/frozen/form";
        }

    @GetMapping(value = "/form/{id}")
    public String showEditForm(Model model, @PathVariable(value = "id") Long id){
        try {
            Frozen frozen = frozenService.findById(id)
                    .orElseThrow(()
                            -> new IllegalArgumentException("Gagal Get Data Id : " + id));
            model.addAttribute("frozen", frozen);
            model.addAttribute("users", usersRepo.findAll());
            model.addAttribute("vendors", vendorRepo.findAll());
            return "pages/frozen/edit";
        }catch (Exception e){
            return "pages/frozen/index";
        }
    }

    @PostMapping("/update/{id}")
    public String updateData(Model model, @ModelAttribute(value = "frozen") Frozen frozen) {
        {
            model.addAttribute("frozen", frozen);
            model.addAttribute("users", usersRepo.findAll());
            model.addAttribute("vendors", vendorRepo.findAll());
            frozenService.save(frozen);
            return "redirect:/frozen/index";
        }
    }
        @GetMapping(value = "/delete/{id}")
        public String remove(@PathVariable(value = "id") Long id){
            this.frozenService.delete(id);
            return "redirect:/frozen/index";
        }

        @PostMapping(value = "/submit")
        public String saved(@Valid @ModelAttribute Frozen frozen,
                            BindingResult result){
            if(result.hasErrors()){
                return "pages/frozen/form";
            }
            frozenService.save(frozen);
            return "redirect:/frozen/index";
        }
}
