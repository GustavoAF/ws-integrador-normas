package br.com.normas.integrador.webservice;

import br.com.normas.integrador.services.NormaService;
import br.com.normas.integrador.webservice.gen.GetNormasRequest;
import br.com.normas.integrador.webservice.gen.GetNormasResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class NormasEndpoint {

    private static final String NAMESPACE_URI = "http://www.normas.com.br/integrador/webservice/gen";

    private NormaService normaService;

    @Autowired
    public NormasEndpoint(NormaService normaService) {
        this.normaService = normaService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNormasRequest")
    @ResponsePayload
    public GetNormasResponse getNormas(@RequestPayload GetNormasRequest request) {
        this.normaService.obterNormas();
        GetNormasResponse response = new GetNormasResponse();
        response.setStatus("Dados Coletados Com Sucesso ".concat(request.getName()));
        return response;
    }
}
