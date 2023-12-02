package ar.edu.utn.frba.dds.app.services.grupo1Servicio1.dto;

import java.util.Date;
import java.util.List;

public class ComunidadDTO {

    public String id;

    public String name;

    public Date lastTimeMerged;

    public Float degreeOfConfidence;

    public List<MiembroDTO> members;

    public List<ServiciosDTO> interestingServices;

    public List<EstablecimientoDTO> interestingEstablishments;
}

/* JSON EJEMPLO SERVICIO1
{
    "id": string,
    "name": string,
    "lastTimeMerged": Date,
    "degreeOfConfidence": number,
    "members": {
        "id": string,
        "name": string,
    }
    "interestingServices": {
        "id": string,
        "name": string,
    }
    "interestingEstablishments": {
        "id": string,
        "name": string,
    }
}
 */