package sv.edu.catolica.project_final;

import java.util.Date;

public class ListWorker {
    public String nombre;
    public String profesion;
    public String departamento;
    public String categoria;
    public String nivel;
    public String horario;
    public Date fecha;
    public String precio;

    public ListWorker(String nombre, String profesion, String departamento, String categoria, String nivel, String horario, Date fecha, String precio) {
        this.nombre = nombre;
        this.profesion = profesion;
        this.departamento = departamento;
        this.categoria = categoria;
        this.nivel = nivel;
        this.horario = horario;
        this.fecha = fecha;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
