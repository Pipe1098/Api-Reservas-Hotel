package com.Hotelreservations.Hotelreservations.dto;

import com.Hotelreservations.Hotelreservations.model.TipoHabitacion;

public class HabitacionDTO {

    private String id;
    private TipoHabitacion tipo;
    private double precioBase;

    public HabitacionDTO(TipoHabitacion tipo, double precioBase) {
        this.tipo = tipo;
        this.precioBase = precioBase;
    }

    public HabitacionDTO() {
    }

    public HabitacionDTO(String id, TipoHabitacion tipo, double precioBase) {
        this.tipo = tipo;
        this.id = id;
        this.precioBase = precioBase;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }
}
