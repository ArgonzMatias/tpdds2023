package ar.edu.utn.frba.dds.calculo.impacto.incidentes.controllers;

import ar.edu.utn.frba.dds.calculo.impacto.incidentes.dto.ServiceRequestDTO;
import ar.edu.utn.frba.dds.calculo.impacto.incidentes.dto.ServiceResponseDTO;
import ar.edu.utn.frba.dds.calculo.impacto.incidentes.services.ImpactoIncidentesService;
import io.javalin.http.Context;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiRequestBody;
import io.javalin.openapi.OpenApiResponse;

public class ServicioController {
    @OpenApi(
            path = "/calculate",
            operationId = "calculate",
            summary = "Calcula los resultados",
            methods = HttpMethod.POST,
            tags = {"Calculadora de Incidentes"},
            requestBody = @OpenApiRequestBody(content = {@OpenApiContent(from = ServiceRequestDTO.class)}),
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = ServiceResponseDTO.class))
            }
    )
    public static void calculate(Context ctx) {
        // Parsea el cuerpo de la solicitud JSON
        ServiceRequestDTO requestDTO = ctx.bodyAsClass(ServiceRequestDTO.class);

        // Realiza el cálculo
        ServiceResponseDTO result = ImpactoIncidentesService.calculate(requestDTO);

        ctx.json(result);
    }
}
