package co.edu.poli.ces3.pitagorasapi.pitagorasapi.servlet;

import co.edu.poli.ces3.pitagorasapi.pitagorasapi.dao.Inscripcion;
import co.edu.poli.ces3.pitagorasapi.pitagorasapi.service.InscripcionService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "inscripcionServlet", value = "/inscripciones/*")
public class InscripcionServlet extends HttpServlet {

    private InscripcionService inscripcionService;

    private Gson gson = new Gson();

    public void init() {
        inscripcionService = new InscripcionService();
        System.out.println("InscripcionServlet inicializado");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        String pathInfo = req.getPathInfo();

        if(pathInfo != null && pathInfo.equals("/carrera")){
            String nombreParam = req.getParameter("nombre");

            if(nombreParam == null){
                out.print("Parametros invalidos");
                return;
            }
            ArrayList<Inscripcion> inscripcionesPorNombre = this.inscripcionService.buscarInscripcionesPorNombre(nombreParam);
            out.print(gson.toJson(inscripcionesPorNombre));
            return;
        }
        if(pathInfo != null && pathInfo.equals("/priorizadas")){
            ArrayList<Inscripcion> inscripcionesOrdenadas = this.inscripcionService.buscarInscripcionesPorPrioridad();
            out.print(gson.toJson(inscripcionesOrdenadas));
            return;
        }

        ArrayList<Inscripcion> inscripciones = inscripcionService.buscarInscripciones();

        out.print(gson.toJson(inscripciones));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        BufferedReader reader = req.getReader();
        StringBuilder jsonBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }

        Inscripcion nuevaInscripcion = gson.fromJson(jsonBody.toString(), Inscripcion.class);

        if (
                nuevaInscripcion == null
                || nuevaInscripcion.getAsignatura() == null
                || nuevaInscripcion.getFechaInscripcion() == null
                || nuevaInscripcion.getCarrera() == null
                || nuevaInscripcion.getCreditos() == 0
                || nuevaInscripcion.getEstado() == null
                || nuevaInscripcion.getDocumento() == null
                || nuevaInscripcion.getSemestre() == null
                || nuevaInscripcion.getEstudiante() == null
                || nuevaInscripcion.getPrioridad() == 0
                || nuevaInscripcion.getPromedioAcumulado() == 0
        ){
            out.print("Parametros inválidos");
            return;
        }

        if(inscripcionService.buscarInscripcionPorEstudianteYAsginatura(nuevaInscripcion.getEstudiante(), nuevaInscripcion.getAsignatura())){
            out.print("El usuario ya esta registrado para la asignatura");
            return;
        }

        int cantidadInscripciones = this.inscripcionService.contarInscripcionesPorEstudiantes(nuevaInscripcion.getEstudiante());
        if(cantidadInscripciones > 7){
            out.print("El usuario ya tien el máximo de inscripciones posibles");
            return;
        }

        out.print(gson.toJson(this.inscripcionService.guardarInscripcion(nuevaInscripcion)));
    }
}
