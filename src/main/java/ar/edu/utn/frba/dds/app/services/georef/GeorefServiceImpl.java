package ar.edu.utn.frba.dds.app.services.georef;

import ar.edu.utn.frba.dds.app.entities.georef.Municipios;
import ar.edu.utn.frba.dds.app.entities.georef.Provincias;
import ar.edu.utn.frba.dds.app.entities.georef.Departamentos;
import ar.edu.utn.frba.dds.app.entities.georef.UbicacionGeografica;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeorefServiceImpl {
    private static GeorefServiceImpl instancia = null;
    private static int maximaCantidadRegistrosDefault = 200;
    private static final String urlApi = "https://apis.datos.gob.ar/georef/api/"; //esto tenemos que pasarlo a un archivo para usar de referencia

    private Retrofit retrofit;

    private GeorefServiceImpl() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GeorefServiceImpl instancia() {
        if(instancia== null){
            instancia = new GeorefServiceImpl();
        }
        return instancia;
    }

    public Provincias listadoDeProvincias() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<Provincias> requestProvinciasArgentinas = georefService.provincias();
        Response<Provincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        return responseProvinciasArgentinas.body();
    }

    public Municipios listadoDeMunicipiosDeProvincia(UbicacionGeografica provincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<Municipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
        Response<Municipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        return responseListadoDeMunicipios.body();
    }

    public Departamentos listadoDeDepartamenotsDeProvincia(UbicacionGeografica provincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<Departamentos> requestListadoDeDepartamentos = georefService.departamentos(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
        Response<Departamentos> responseListadoDeDepartamentosDeProvincia = requestListadoDeDepartamentos.execute();
        return responseListadoDeDepartamentosDeProvincia.body();
    }
}