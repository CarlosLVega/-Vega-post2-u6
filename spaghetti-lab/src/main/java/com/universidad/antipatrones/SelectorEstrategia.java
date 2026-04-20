package com.universidad.antipatrones;

import java.util.Map;

// Selector de estrategia: un solo punto de decision
public class SelectorEstrategia {
    private final Map<String, EstrategiaDescuento> estrategias = Map.of(
            "VIP", new DescuentoVIP(),
            "PREMIUM", new DescuentoPremium(),
            "ESTANDAR", new DescuentoEstandar()
    );

    public EstrategiaDescuento seleccionar(String tipoCliente) {
        return estrategias.getOrDefault(tipoCliente, estrategias.get("ESTANDAR"));
    }
}
