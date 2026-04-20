# Refactoring Lab - Spaghetti Code

## Información del proyecto

**Asignatura:** Patrones de Diseño de Software  
**Unidad:** Unidad 6 - Antipatrones de Diseño  
**Actividad:** Post-Contenido 2  
**Estudiante:** Carlos Vega  
**Repositorio:** Vega-post2-u6  

## Descripción

Este proyecto corresponde a un laboratorio de refactorización en Java. El objetivo principal es identificar y eliminar el antipatrón conocido como **Spaghetti Code** dentro de un sistema de procesamiento de pedidos.

Inicialmente, el sistema se implementó con una clase llamada `ProcesadorPedidos`, la cual contenía una gran cantidad de condicionales anidados para calcular descuentos y procesar pedidos. Posteriormente, el diseño fue refactorizado aplicando los patrones **Strategy** y **Command**.

## Antipatrón identificado: Spaghetti Code

El antipatrón **Spaghetti Code** aparece cuando el flujo del programa se vuelve difícil de seguir debido a condicionales anidados, lógica mezclada y baja separación de responsabilidades.

En este proyecto, la clase `ProcesadorPedidos` presentaba este problema porque:

- Tenía múltiples niveles de condicionales `if` y `else`  
- Mezclaba reglas de descuento con procesamiento de pedidos  
- Mezclaba lógica de negocio con notificaciones  
- Era difícil de extender  
- Tenía baja testabilidad  

## Problema inicial

La clase `ProcesadorPedidos` tenía un método principal `procesarPedido`, encargado de:

- Identificar el tipo de cliente  
- Calcular descuentos  
- Evaluar promociones  
- Calcular el total final  
- Generar alertas  
- Imprimir resultados  

Esto generaba alta complejidad ciclomática y baja mantenibilidad.

## Patrones aplicados

### Patrón Strategy

Se aplicó el patrón **Strategy** para encapsular las reglas de descuento en clases independientes.

Interfaz:

```text
EstrategiaDescuento
````

Implementaciones:

* DescuentoVIP
* DescuentoPremium
* DescuentoEstandar

**Ventajas:**

* Eliminación de condicionales anidados
* Bajo acoplamiento
* Mayor extensibilidad
* Cumplimiento del principio Abierto-Cerrado

### Patrón Command

Se aplicó el patrón **Command** para encapsular la operación de procesamiento.

Interfaz:

```text
ComandoPedido
```

Implementación:

* ComandoProcesarPedido

**Ventajas:**

* Desacoplamiento entre invocador y ejecución
* Mejor organización del flujo
* Mayor flexibilidad para extender operaciones

### Selector de estrategia

Se implementó la clase:

```text
SelectorEstrategia
```

Encargada de seleccionar dinámicamente la estrategia según el tipo de cliente:

* VIP
* PREMIUM
* ESTANDAR

## Diseño inicial

```text
Pedido
ProcesadorPedidos
Main
```

El método `procesarPedido` contenía toda la lógica centralizada.

## Diseño refactorizado

```text
Pedido
EstrategiaDescuento
DescuentoVIP
DescuentoPremium
DescuentoEstandar
ComandoPedido
ComandoProcesarPedido
SelectorEstrategia
Main
```

## Comparación antes y después

### Antes (Spaghetti Code)

```java
if (tipo != null) {
    if (tipo.equals("VIP")) {
        if (total > 1000) {
            if (promo != null && promo.equals("VIPEXTRA")) {
                // lógica de descuento
            }
        }
    } else if (tipo.equals("PREMIUM")) {
        if (total > 500) {
            // lógica de descuento
        } else {
            if (promo != null && promo.equals("PREM10")) {
                // lógica de descuento
            }
        }
    } else {
        if (promo != null) {
            if (promo.startsWith("FIRST")) {
                // lógica de descuento
            }
        }
    }
}
```

### Después (Refactorizado)

```java
EstrategiaDescuento estrategia = selector.seleccionar(pedido.getTipoCliente());
ComandoPedido comando = new ComandoProcesarPedido(pedido, estrategia);
comando.ejecutar();
```

## Cómo ejecutar el proyecto

Entrar al proyecto:

```bash
cd spaghetti-lab
```

Compilar:

```bash
mvn compile
```

Ejecutar:

```bash
mvn exec:java
```

O en una sola línea:

```bash
mvn compile && mvn exec:java
```

## Salida esperada

```text
Procesando pedido: P001
  Estrategia: VIP | Descuento: 45%
  Total final: $660.00
  [ALERTA] Pedido de alto valor: P001
Pedido P001 procesado.

Procesando pedido: P002
  Estrategia: VIP | Descuento: 30%
  Total final: $420.00
Pedido P002 procesado.

Procesando pedido: P003
  Estrategia: PREMIUM | Descuento: 15%
  Total final: $255.00
Pedido P003 procesado.

Procesando pedido: P004
  Estrategia: ESTANDAR | Descuento: 8%
  Total final: $138.00
Pedido P004 procesado.

Procesando pedido: P005
  Estrategia: ESTANDAR | Descuento: 0%
  Total final: $80.00
Pedido P005 procesado.
```

## Conclusión

La refactorización permitió eliminar el antipatrón Spaghetti Code mediante la separación de responsabilidades y la aplicación de los patrones Strategy y Command. Como resultado, el sistema presenta menor complejidad, mayor cohesión y mejor capacidad de evolución.

````
