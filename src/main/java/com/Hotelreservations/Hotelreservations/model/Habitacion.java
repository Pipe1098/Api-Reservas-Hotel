package com.Hotelreservations.Hotelreservations.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_habitacion")
    private TipoHabitacion tipo;

    @Column(name = "precio_base")
    private double precioBase;





    public Habitacion(Long id, TipoHabitacion tipo, double precioBase) {
        this.id = id;
        this.tipo = tipo;
        this.precioBase = precioBase;
    }

    public Habitacion() {
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public Long getId() {
        return id;
    }



    public TipoHabitacion getTipo() {
        return tipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }



}
