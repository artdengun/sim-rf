package com.deni.gunawan.sistemmanajemenricheesefactory.services;

import com.deni.gunawan.sistemmanajemenricheesefactory.entity.Vendors;
import com.deni.gunawan.sistemmanajemenricheesefactory.repository.VendorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class VendorService {

    private VendorRepo vendorRepo;

    public List<Vendors> getList(){
        return vendorRepo.findAll();
    }
    public Vendors save(Vendors vendor){
        return vendorRepo.save(vendor);
    }
    public Optional<Vendors> findById(Long id){
        return vendorRepo.findById(id);
    }
    public void delete(Long id){
        vendorRepo.deleteById(id);
    }
}
