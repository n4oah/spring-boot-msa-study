package com.msa.account.component;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public interface IJwtClaim {
    default Map<String, ?> toMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(this, Map.class);
    }
}
