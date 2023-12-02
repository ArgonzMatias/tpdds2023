package ar.edu.utn.frba.dds.app.controllers;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.prestacion.Empresa;
import ar.edu.utn.frba.dds.app.domain.prestacion.OrganismoDeControl;
import ar.edu.utn.frba.dds.app.domain.sociedad.Incidente;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.repositories.EmpresasRepository;
import ar.edu.utn.frba.dds.app.repositories.EntidadesRepository;
import ar.edu.utn.frba.dds.app.repositories.Repository;
import ar.edu.utn.frba.dds.app.repositories.UsuariosRepository;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.*;
import javax.persistence.NoResultException;

public class RandomController extends Controller {
    public void ranking(Context context) {
        super.usuarioLogueado(context);
        Map<String, Object> model = new HashMap<>();
        context.render("ranking.hbs", model);
    }

    public void cargarEntidad(Context context) {
        super.usuarioLogueado(context);
        Incidente incidente = null;
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("cargaEntidades.hbs", model);

    }

    public void cargarEntidadCSV(Context context) throws IOException {
        super.usuarioLogueado(context);
        List<Empresa> empresas = new ArrayList<>();
        List<OrganismoDeControl> organismos = new ArrayList<>();
        InputStream fileContent = context.uploadedFile("csvFile").content();
        BufferedReader br = new BufferedReader(new InputStreamReader(fileContent));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(";");
            System.out.print(datos[0]);
            if (Objects.equals(datos[0], "EMPRESA")) {
                Empresa empresa = mapearEmpresaCsv(datos);
                empresas.add(empresa);
            } else if (Objects.equals(datos[0], "ORGANISMO")) {
                OrganismoDeControl organismoDeControl = mapearOrganismoCsv(datos);
                organismos.add(organismoDeControl);
            }
        }
        context.result("¡Archivo CSV cargado exitosamente!");
        Map<String, Object> model = new HashMap<>();
        model.put("empresas", empresas);
        model.put("organismos", organismos);
        context.render("entidadesSubidas.hbs", model);
    }

    private static Empresa mapearEmpresaCsv(String[] datos) {
        Repository repository = new Repository();
        UsuariosRepository usuariosRepository = new UsuariosRepository();
        EntidadesRepository entidadesRepository = new EntidadesRepository();
        String codigo = datos[1];
        String nombre = datos[2];
        String mail = datos[3];
        try {
            Usuario usuario = usuariosRepository.buscarUsuarioPorCorreo(mail);
            Empresa empresa = new Empresa(codigo, nombre, usuario);
            String[] codigosEntidades = datos[4].split(",");
            for (String codigoEntidad : codigosEntidades) {
                String codigoEntidadFormat = codigoEntidad.trim();
                Entidad entidad = entidadesRepository.buscarEntidadPorCodigo(codigoEntidadFormat);
                empresa.agregarEntidad(entidad);
            }
            repository.guardar(empresa);
            return empresa;
        } catch (NoResultException e) {
            throw e;
        }
    }

    private static OrganismoDeControl mapearOrganismoCsv(String[] datos) {
        Repository repository = new Repository();
        UsuariosRepository usuariosRepository = new UsuariosRepository();
        EmpresasRepository empresasRepository = new EmpresasRepository();
        String codigo = datos[1];
        String nombre = datos[2];
        String mail = datos[3];
        try {
            Usuario usuario = usuariosRepository.buscarUsuarioPorCorreo(mail);
            OrganismoDeControl organismoDeControl = new OrganismoDeControl(codigo, nombre, usuario);
            String[] codigosEmpresa = datos[4].split(",");
            for (String codigoEmpresa : codigosEmpresa) {
                String codigoEmpresaFormat = codigoEmpresa.trim();
                Empresa empresa = empresasRepository.buscarEmpresaPorCodigo(codigoEmpresaFormat);
                organismoDeControl.agregarEmpresaPrestadora(empresa);
            }
            repository.guardar(organismoDeControl);
            return organismoDeControl;
        } catch (NoResultException e) {
            throw e;
        }
    }


    public void faq(Context context) {
        Map<String, Object> model = new HashMap<>();
        context.render("faq.hbs", model);
    }
}
