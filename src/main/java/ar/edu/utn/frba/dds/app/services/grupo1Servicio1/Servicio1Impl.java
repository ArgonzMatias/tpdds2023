package ar.edu.utn.frba.dds.app.services.grupo1Servicio1;

import ar.edu.utn.frba.dds.app.entities.georef.Provincias;
import ar.edu.utn.frba.dds.app.services.georef.GeorefService;
import ar.edu.utn.frba.dds.app.services.grupo1Servicio1.dto.FusionRequestDTO;
import ar.edu.utn.frba.dds.app.services.grupo1Servicio1.dto.FusionResponseDTO;
import ar.edu.utn.frba.dds.app.services.grupo1Servicio1.dto.RecommendationRequestDTO;
import ar.edu.utn.frba.dds.app.services.grupo1Servicio1.dto.RecommendationResponseDTO;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Servicio1Impl {

    private static Servicio1Impl instancia = null;
    private static final String urlApi = "https://localhost:3000"; //esto tenemos que pasarlo a un archivo para usar de referencia

    private Retrofit retrofit;

    private Servicio1Impl() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Servicio1Impl instancia() {
        if(instancia== null){
            instancia = new Servicio1Impl();
        }
        return instancia;
    }

    public FusionResponseDTO fusionDeComunidades(FusionRequestDTO requestDTO) throws IOException {
        Servicio1 servicio1 = this.retrofit.create(Servicio1.class);
        Call<FusionResponseDTO> requestDTOCall = servicio1.fusionComunidades(requestDTO);
        Response<FusionResponseDTO> responseDTO = requestDTOCall.execute();
        return responseDTO.body();
    }

    public RecommendationResponseDTO recomendacionDeComunidades(RecommendationRequestDTO requestDTO) throws IOException {
        Servicio1 servicio1 = this.retrofit.create(Servicio1.class);
        Call<RecommendationResponseDTO> requestDTOCall = servicio1.recomendacionComunidades(requestDTO);
        Response<RecommendationResponseDTO> responseDTO = requestDTOCall.execute();
        return responseDTO.body();
    }
}
