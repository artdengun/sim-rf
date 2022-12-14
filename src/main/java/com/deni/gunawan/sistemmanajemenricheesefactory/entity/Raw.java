package com.deni.gunawan.sistemmanajemenricheesefactory.entity;


import com.deni.gunawan.sistemmanajemenricheesefactory.enums.Negara;
import com.deni.gunawan.sistemmanajemenricheesefactory.enums.UOM;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author denigunawan
 */

@Entity
@Data
public class Raw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Data Nama Product Required")
    @Column(name = "nama_product", length = 50)
    private String namaProduct;
    @NotEmpty(message = "Data Production Date Required")
    @Column(name = "production_date", length = 30)
    private String productionDate;
    @NotEmpty(message = "Data Expdate Required")
    @Column(name = "exp_date", length = 30)
    private String expDate;
    @NotEmpty(message = "Data Quantity Barang Required")
    @Column(name = "quantity", length = 30)
    private String quantity;
    @NotEmpty(message = "Data Code Barang Required")
    @Column(name = "codeBarang", length = 30)
    private String codeBarang;
    @NotEmpty(message = "Data Tanggal Penerimaan Required")
    @Column(name = "tanggal_penerimaan")
    private String tanggalPenerimaan;
    @NotEmpty(message = "Data Deskripsi Required")
    @Column(name = "deskripsi", length = 100)
    private String deskripsi;

    @OneToOne
    @JoinColumn(name = "vendor_id")
    private Vendors vendors;

    @Enumerated(EnumType.STRING)
    private Negara negara;

    @Enumerated(EnumType.STRING)
    private UOM uom;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User users;

}
