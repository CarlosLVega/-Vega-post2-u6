package com.universidad.antipatrones;

public class Main {
    public static void main(String[] args) {
        ProcesadorPedidos procesador = new ProcesadorPedidos();

        Pedido pedido1 = new Pedido("P001", "VIP", 1200.0, "VIPEXTRA");
        Pedido pedido2 = new Pedido("P002", "VIP", 600.0, "VIP20");
        Pedido pedido3 = new Pedido("P003", "PREMIUM", 300.0, "PREM10");
        Pedido pedido4 = new Pedido("P004", "ESTANDAR", 150.0, "FIRST50");
        Pedido pedido5 = new Pedido("P005", "ESTANDAR", 80.0, null);

        procesador.procesarPedido(pedido1);
        procesador.procesarPedido(pedido2);
        procesador.procesarPedido(pedido3);
        procesador.procesarPedido(pedido4);
        procesador.procesarPedido(pedido5);
    }
}
