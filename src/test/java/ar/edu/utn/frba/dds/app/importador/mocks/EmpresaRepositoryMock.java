package ar.edu.utn.frba.dds.app.importador.mocks;

import ar.edu.utn.frba.dds.app.domain.prestacion.Empresa;
import ar.edu.utn.frba.dds.app.importador.IEntityRepository;

import java.util.ArrayList;
import java.util.List;

public class EmpresaRepositoryMock implements IEntityRepository<Empresa> {

    List<Empresa> empresas = new ArrayList<>();
    @Override
    public Empresa getByCodigo(String codigo) {
        return empresas.get(0);
    }

    public void agregarEmpresa(Empresa empresa) {
        empresas.add(empresa);
    }
}
