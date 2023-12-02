package ar.edu.utn.frba.dds.app.importador;


import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frba.dds.app.domain.Entidad;
import ar.edu.utn.frba.dds.app.domain.TipoDeEntidad;
import ar.edu.utn.frba.dds.app.domain.prestacion.Empresa;
import ar.edu.utn.frba.dds.app.domain.prestacion.OrganismoDeControl;
import ar.edu.utn.frba.dds.app.domain.sociedad.Usuario;
import ar.edu.utn.frba.dds.app.importador.factory.EmpresaFactory;
import ar.edu.utn.frba.dds.app.importador.factory.OrganismoDeControlFactory;
import ar.edu.utn.frba.dds.app.importador.mocks.EmpresaRepositoryMock;
import ar.edu.utn.frba.dds.app.importador.mocks.EntidadRepositoryMock;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImportadorTest {

    @Test
    @DisplayName("Se carga una empresa de forma correcta")
    public void TestCargarEmpresaOk() {
        EntidadRepositoryMock entidadRepositoryMock = new EntidadRepositoryMock();
        TipoDeEntidad tipoDeEntidad = new TipoDeEntidad("Linea","Estacion");
        Entidad linea = new Entidad("COD-113","Test", tipoDeEntidad);
        entidadRepositoryMock.agregarEntidad(linea);

        EmpresaFactory empresaFactory = new EmpresaFactory(entidadRepositoryMock);
        List<Empresa> empresas = empresaFactory.importar("empresa_example.csv");

        assertEquals(1, empresas.size());
        Empresa empresaCargada = empresas.get(0);
        assertEquals("COD-TA", empresaCargada.getCodigo());
        assertEquals("Trenes Argentinos", empresaCargada.getNombre());
        assertEquals("trenes@mail.com", empresaCargada.getUsuario().getCorreoElectronico());
        assertEquals(3, empresaCargada.getEntidades().size());
    }

    @Test
    @DisplayName("Se carga un organismo de control de forma correcta")
    public void TestCargarOrganismoDeControlOk() {
        EmpresaRepositoryMock empresaRepositoryMock = new EmpresaRepositoryMock();
        Usuario usuario = new Usuario();
        usuario.setCorreoElectronico("trenes@gmail.com");
        Empresa empresa = new Empresa("COD-TA","Trenes Argentinos", usuario);
        empresaRepositoryMock.agregarEmpresa(empresa);

        OrganismoDeControlFactory organismoDeControlFactory = new OrganismoDeControlFactory(empresaRepositoryMock);
        List<OrganismoDeControl> organismosDeControl = organismoDeControlFactory.importar("organismo_de_control_example.csv");

        assertEquals(1, organismosDeControl.size());
        OrganismoDeControl organismoDeControlCargado = organismosDeControl.get(0);
        assertEquals("COD-CNRT", organismoDeControlCargado.getCodigo());
        assertEquals("CNRT", organismoDeControlCargado.getNombre());
        assertEquals("cnrt@mail.com", organismoDeControlCargado.getUsuario().getCorreoElectronico());
        assertEquals(3, organismoDeControlCargado.getEmpresas().size());
    }
}

