package com.Hotelreservations.Hotelreservations.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(name = "cedula")
    private Long cedula;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "correo_electronico")
    private String correoElectronico;


    public Cliente(Long cedula, String nombre, String apellido, String direccion, Integer edad, String correoElectronico) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
    }

    public Cliente() {
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }



    public Long getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }


}
