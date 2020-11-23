package com.xdesign.munro.library.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table( name = "munro")
public class Munro
{
    @Id
    @Column(name = "id")
    private long id;

    @Column( name = "DoBIHNumber" )
    private String doBIHNumber;

    @Column( name = "Streetmap" )
    private String streetmap;

    @Column( name = "Geograph" )
    private String geograph;

    @Column( name = "HillBagging" )
    private String hillBagging;

    @Column( name = "Name" )
    private String name;

    @Column( name = "SMCSection" )
    private String sMCSection;

    @Column( name = "RHBSection" )
    private String rhbSection;

    @Column( name = "Section" )
    private String section;

    @Column( name = "HeightInMtrs" )
    private String heightInMetres;

    @Column( name = "HeightInFt" )
    private String heightInFeet;

    @Column( name = "Map150" )
    private String map150;

    @Column( name = "Map125" )
    private String map125;

    @Column( name = "GridRef" )
    private String gridRef;

    @Column( name = "GridRefXY" )
    private String gridRefXY;

    @Column( name = "xcoord" )
    private String xcoord;

    @Column( name = "ycoord" )
    private String ycoord;

    @Column( name = "y1891" )
    private String y1891;

    @Column( name = "y1921" )
    private String y1921;

    @Column( name = "y1933" )
    private String y1933;

    @Column( name = "y1953" )
    private String y1953;

    @Column( name = "y1969" )
    private String y1969;

    @Column( name = "y1974" )
    private String y1974;

    @Column( name = "y1981" )
    private String y1981;

    @Column( name = "y1984" )
    private String y1984;

    @Column( name = "y1990" )
    private String y1990;

    @Column( name = "y1997" )
    private String y1997;

    @Column( name = "Post1997" )
    private String post1997;

    @Column( name = "Comments" )
    private String comments;

    public Munro( long id, String doBIHNumber, String streetmap, String geograph, String hillBagging, String name, String sMCSection, String rhbSection, String section, String heightInMetres, String heightInFeet, String map150, String map125, String gridRef, String gridRefXY, String xcoord, String ycoord, String y1891, String y1921, String y1933, String y1953, String y1969, String y1974, String y1981, String y1984, String y1990, String y1997, String post1997, String comments )
    {
        this.id = id;
        this.doBIHNumber = doBIHNumber;
        this.streetmap = streetmap;
        this.geograph = geograph;
        this.hillBagging = hillBagging;
        this.name = name;
        this.sMCSection = sMCSection;
        this.rhbSection = rhbSection;
        this.section = section;
        this.heightInMetres = heightInMetres;
        this.heightInFeet = heightInFeet;
        this.map150 = map150;
        this.map125 = map125;
        this.gridRef = gridRef;
        this.gridRefXY = gridRefXY;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.y1891 = y1891;
        this.y1921 = y1921;
        this.y1933 = y1933;
        this.y1953 = y1953;
        this.y1969 = y1969;
        this.y1974 = y1974;
        this.y1981 = y1981;
        this.y1984 = y1984;
        this.y1990 = y1990;
        this.y1997 = y1997;
        this.post1997 = post1997;
        this.comments = comments;
    }

    public Munro() {}
}
