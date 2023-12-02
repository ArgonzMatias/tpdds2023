package ar.edu.utn.frba.dds.app.services.georef;

import ar.edu.utn.frba.dds.app.entities.georef.Departamentos;
import ar.edu.utn.frba.dds.app.entities.georef.Municipios;
import ar.edu.utn.frba.dds.app.entities.georef.Provincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {
    @GET("provincias")
    Call<Provincias> provincias();

    @GET("provincias")
    Call<Provincias> provincias(@Query("campos") String campos);

    @GET("municipios")
    Call<Municipios> municipios(@Query("provincia") int idProvincia);

    @GET("municipios")
    Call<Municipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos);

    @GET("municipios")
    Call<Municipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("max") int max);

    @GET("departamentos")
    Call<Departamentos> departamentos(@Query("provincia") int idProvincia);

    @GET("departamentos")
    Call<Departamentos> departamentos(@Query("provincia") int idProvincia, @Query("campos") String campos);

    @GET("departamentos")
    Call<Departamentos> departamentos(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("max") int max);
}