package ar.edu.utn.frba.dds.app.services.grupo1Servicio1;

import ar.edu.utn.frba.dds.app.services.grupo1Servicio1.dto.FusionRequestDTO;
import ar.edu.utn.frba.dds.app.services.grupo1Servicio1.dto.FusionResponseDTO;
import ar.edu.utn.frba.dds.app.services.grupo1Servicio1.dto.RecommendationRequestDTO;
import ar.edu.utn.frba.dds.app.services.grupo1Servicio1.dto.RecommendationResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Servicio1 {

    @POST("/api/fusion")
    Call<FusionResponseDTO> fusionComunidades(@Body FusionRequestDTO requestDTO);

    @POST("/api/recommendations")
    Call<RecommendationResponseDTO> recomendacionComunidades(@Body RecommendationRequestDTO requestDTO);
}
