package com.i2p.barcodeusingjavafx.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "reference")
    private String reference;
    @Column(name = "code")
    private String code;
    @Column(name = "codetype")
    private String codeType;
    @Column(name = "name")
    private String name;
    @Column(name = "pricebuy")
    private double pricebuy;
    @Column(name = "pricesell")
    private double pricesell;
    @Column(name = "category")
    private String category;
    @Column(name = "taxcat")
    private String taxcat;
    @Column(name = "attributeset_id")
    private String attributeset_id;
    @Column(name = "stockcost")
    private double stockcost;
    @Column(name = "stockvolume")
    private double stockvolume;
    @Column(name = "iscom")
    private boolean iscom;
    @Column(name = "isscale")
    private boolean isscale;
    @Column(name = "isconstant")
    private boolean isconstant;
    @Column(name = "printkb")
    private boolean printkb;
    @Column(name = "sendstatus")
    private boolean sendstatus;
    @Column(name = "isservice")
    private boolean isservice;
    @Column(name = "display")
    private String display;
    @Column(name = "isvprice")
    private int isvprice;
    @Column(name = "isverpatrib")
    private int isverpatrib;
    @Column(name = "texttip")
    private String texttip;
    @Column(name = "warranty")
    private int warranty;
    @Column(name = "stockunits")
    private double stockunits;
    @Column(name = "printto")
    private String printto;
    @Column(name = "supplier")
    private String supplier;
    @Column(name = "uom")
    private String uom;


}
