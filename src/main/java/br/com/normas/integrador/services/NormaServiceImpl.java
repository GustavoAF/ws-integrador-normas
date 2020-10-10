package br.com.normas.integrador.services;

import br.com.normas.integrador.entity.NormaEntity;
import br.com.normas.integrador.repositories.NormaRepository;
import https.repositorionormas.Norma;
import https.repositorionormas_com.Normas;
import https.repositorionormas_com.RepositorioNormasPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.BindingProvider;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
public class NormaServiceImpl implements NormaService{
    private static final String ENDPOINT_NORMAS = "http://repositorionormas-env.eba-gmcmpscm.us-east-1.elasticbeanstalk.com:8080/services/Normas?wsdl";

    private final NormaRepository normaRepository;

    @Autowired
    public NormaServiceImpl(NormaRepository normaRepository) {
        this.normaRepository = normaRepository;
    }

    @Override
    public Norma getNormas() {
        Normas serviceImpl = new Normas();
        RepositorioNormasPort port = serviceImpl.getRepositorioNormasPort();
        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT_NORMAS);
        return port.normas("123");
    }

    @Override
    public void obterNormas() {
        Norma norma = this.getNormas();

       this.normaRepository.save(NormaEntity
               .builder()
               .data_public(stringToDate(norma.getData(), "dd/MM/yyyy"))
               .descricao(norma.getDescricao())
               .status(norma.getStatus())
               .id(norma.getCodigo())
               .build());
    }


    public Date stringToDate(String date, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
