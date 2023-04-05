package com.Hotelreservations.Hotelreservations.dto;

public class ClienteDTO {

    private  Long cedula;
    private  String apellido;
    private String nombre;
    private String correoElectronico;

    public ClienteDTO(Long cedula,String nombre, String apellido, String correoElectronico) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.cedula=cedula;
    }

    public ClienteDTO() {
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public Long getCedula() {
        return cedula;
    }
}
