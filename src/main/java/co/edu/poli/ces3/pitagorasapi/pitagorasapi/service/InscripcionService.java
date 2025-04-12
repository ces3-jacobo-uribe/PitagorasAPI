package co.edu.poli.ces3.pitagorasapi.pitagorasapi.service;

import co.edu.poli.ces3.pitagorasapi.pitagorasapi.dao.Inscripcion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InscripcionService {

    private ArrayList<Inscripcion> inscripciones;

    public InscripcionService() {
        this.inscripciones = new ArrayList<>();
    }

    public ArrayList<Inscripcion> buscarInscripciones(){
        return this.inscripciones;
    }

    public ArrayList<Inscripcion> buscarInscripcionesPorNombre(String nombre){
        ArrayList<Inscripcion> inscripcionesEncontradas = new ArrayList<>();

        for(int i = 0; i < this.inscripciones.size(); i++){
            if(this.inscripciones.get(i).getCarrera().equals(nombre)){
                inscripcionesEncontradas.add(this.inscripciones.get(i));
            }
        }

        return inscripcionesEncontradas;
    }

    public ArrayList<Inscripcion> buscarInscripcionesPorPrioridad(){
        ArrayList<Inscripcion> inscripciones = new ArrayList<>(this.inscripciones);
        inscripciones.sort((i1, i2) ->
                Double.compare(obtenerPuntajePrioridad(i2), obtenerPuntajePrioridad(i1))
        );
        return inscripciones;
    }

    public double obtenerPuntajePrioridad(Inscripcion inscripcion){
        return (
                (inscripcion.getPromedioAcumulado() * 0.6)
                        + ((inscripcion.getCreditos() / 10) * 0.3)
                        + (x(inscripcion.getFechaInscripcion()) * 0.1)
        );
    }

    public boolean buscarInscripcionPorEstudianteYAsginatura(String estudiante, String asignatura){
        boolean encontrado = false;
        for(int i = 0; i < inscripciones.size(); i++){
            Inscripcion inscripcionActual = inscripciones.get(i);
            if(inscripcionActual.getEstudiante().equalsIgnoreCase(estudiante) && inscripcionActual.getAsignatura().equalsIgnoreCase(asignatura)){
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    public int contarInscripcionesPorEstudiantes(String estudiante){
        int contador = 0;
        for(int i = 0; i < this.inscripciones.size(); i++){
            if(inscripciones.get(i).getEstudiante().equalsIgnoreCase(estudiante)){
                contador++;
            }
        }
        return contador;
    }

    private long x(String fechaInscripcion) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInsc = sdf.parse(fechaInscripcion);
            Date fechaActual = new Date();

            long diferenciaMilis = fechaActual.getTime() - fechaInsc.getTime();

            return diferenciaMilis / (24 * 60 * 60 * 1000);
        }catch (ParseException e) {
            return 0;
        }
    }

    public Inscripcion guardarInscripcion(Inscripcion nuevaInscripcion) {
        nuevaInscripcion.setId(this.inscripciones.size());
        this.inscripciones.add(nuevaInscripcion);
        return nuevaInscripcion;
    }
}
