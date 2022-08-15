package com.deni.gunawan.sistemmanajemenricheesefactory.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vendor")
public class Vendors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nama_vendor", length = 50)
    private String namaVendor;
}
