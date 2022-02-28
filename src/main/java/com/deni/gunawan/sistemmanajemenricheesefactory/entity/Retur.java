package com.deni.gunawan.sistemmanajemenricheesefactory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deni.gunawan.sistemmanajemenricheesefactory.enums.UOM;
import com.deni.gunawan.sistemmanajemenricheesefactory.enums.Vendor;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "retur")
public class Retur {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36, unique = true)
    private String id;
    @Column(name = "vendor", nullable = false)
    private Vendor vendor;
    @Column(name = "nama_barang", length = 50)
    private String namaBarang;
    @Column(name = "production_date")
    private Date productionDate;
    @Column(name = "exp_date")
    private Date expDate;
    @Column(name = "negara")
    private String negara;
    @Column(name = "jumlah_retur")
    private String jumlahRetur;
    @Column(name = "uom", nullable = false)
    private UOM uom;
    @Column(name = "code_barang", length = 20)
    private String codeBarang;
    @Column(name = "tanggal_retur")
    private Date tanggalRetur;
    @Column(name = "deskripsi", length = 100)
    private String deskripsi;
    
    @ManyToOne
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id")
    private Karyawan karyawan;
}