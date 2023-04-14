package HotelReservations.dto;

import HotelReservations.model.TipoHabitacion;

public class HabitacionDTO {

    private TipoHabitacion tipo;
    private double precioBase;

    public HabitacionDTO(TipoHabitacion tipo, double precioBase) {
        this.tipo = tipo;
        this.precioBase = precioBase;
    }

    public HabitacionDTO() {
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

}
