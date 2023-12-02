package ar.edu.utn.frba.dds.calculo.impacto.incidentes.swagger;

import io.javalin.openapi.OpenApiContact;
import io.javalin.openapi.OpenApiInfo;
import io.javalin.openapi.OpenApiLicense;
import io.javalin.openapi.plugin.OpenApiConfiguration;

public class SwaggerInitializer {

    private static OpenApiContact getOpenApiContact() {
        OpenApiContact openApiContact = new OpenApiContact();
        openApiContact.setName("API Support");
        openApiContact.setUrl("https://www.example.com/support");
        openApiContact.setEmail("support@example.com");
        return openApiContact;
    }

    private static OpenApiLicense getOpenApiLicense() {
        OpenApiLicense openApiLicense = new OpenApiLicense();
        openApiLicense.setName("Apache 2.0");
        openApiLicense.setIdentifier("Apache-2.0");
        return openApiLicense;
    }

    private static OpenApiInfo getOpenApiInfo(OpenApiContact openApiContact, OpenApiLicense openApiLicense) {
        OpenApiInfo openApiInfo = new OpenApiInfo();
        openApiInfo.setTitle("Servicio Calculo Impacto Incidentes");
        openApiInfo.setSummary("App summary");
        openApiInfo.setDescription("App description goes right here");
        openApiInfo.setTermsOfService("https://example.com/tos");
        openApiInfo.setContact(openApiContact);
        openApiInfo.setLicense(openApiLicense);
        openApiInfo.setVersion("1.0.0");
        return openApiInfo;
    }

    public static OpenApiConfiguration getOpenApiConfiguration() {
        OpenApiContact openApiContact = getOpenApiContact();
        OpenApiLicense openApiLicense = getOpenApiLicense();
        OpenApiInfo openApiInfo = getOpenApiInfo(openApiContact, openApiLicense);

        OpenApiConfiguration openApiConfiguration = new OpenApiConfiguration();
        openApiConfiguration.setInfo(openApiInfo);
        return openApiConfiguration;
    }
}
