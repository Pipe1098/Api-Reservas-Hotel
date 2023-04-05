package com.Hotelreservations.Hotelreservations.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "habitaciones")
public class Habitacion {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private String id;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_habitacion")
    private TipoHabitacion tipo;

    @Column(name = "precio_base")
    private double precioBase;





    public Habitacion(String id, TipoHabitacion tipo, double precioBase) {
        this.id = id;
        this.tipo = tipo;
        this.precioBase = precioBase;
    }

    public Habitacion() {
    }

    public void setId(String id) {
        this.id = id;
    }



    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getId() {
        return id;
    }



    public TipoHabitacion getTipo() {
        return tipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }



}
