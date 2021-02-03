package br.com.arca.commons.util;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CleanData {

    public String cleanCpf(String cpf){
        if(cpf == null) return null;
        
        return cpf.replaceAll("\\D", "");
    }

    public String cleanPhone(String phone){
        if(phone == null) return null;
        
        return phone.replaceAll("\\D", "");
    }

    public String cleanArcaId(String arcaId) {
        if(arcaId == null) return null;

        return arcaId.replaceAll("^0+", "");
    }
}
