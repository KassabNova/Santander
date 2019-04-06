package com.ds.dummysantander;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> transferencias = new ArrayList<>();
        transferencias.add("Cuentas propias");
        transferencias.add("Terceros mismo banco");
        transferencias.add("Otros bancos");


        List<String> pago = new ArrayList<String>();
        pago.add("SAT");
        pago.add("CFE");
        pago.add("SIAPA");


        List<String> compras = new ArrayList<String>();
        compras.add("Tiempo aire");

        expandableListDetail.put("Transferencias", transferencias);
        expandableListDetail.put("Realizar pago", pago);
        expandableListDetail.put("Compras", compras);
        return expandableListDetail;
    }
}
