package com.projeto.projetoexperiencias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppCommons {

    private AppCommons() {}

    public static ArrayList<Map<String, String>> createItemsList() {
        ArrayList<Map<String, String>> items = new ArrayList<Map<String, String>>();

        HashMap<String, String> states = new HashMap<String, String>();
        states.put("title", "AC");
        states.put("description", "Acre");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-acre.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "AL");
        states.put("description", "Alagoas");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-alagoas.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "AP");
        states.put("description", "Amap�");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-amapa.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "AM");
        states.put("description", "Amazonas");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-amazonas.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "BA");
        states.put("description", "Bahia");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-bahia.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "CE");
        states.put("description", "Cear�");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-ceara.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "DF");
        states.put("description", "Distrito Federal");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-distrito-federal.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "ES");
        states.put("description", "Esp�rito Santo");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-espirito-santo.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "GO");
        states.put("description", "Goi�s");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-goias.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "MA");
        states.put("description", "Maranh�o");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-maranhao.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "MT");
        states.put("description", "Mato Grosso");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-mato-grosso.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "MS");
        states.put("description", "Mato Grosso do Sul");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-mato-grosso-do-sul.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "MG");
        states.put("description", "Minas Gerais");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-minas-gerais.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "PA");
        states.put("description", "Par�");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-para.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "PB");
        states.put("description", "Para�ba");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-paraiba.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "PR");
        states.put("description", "Paran�");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-parana.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "PE");
        states.put("description", "Pernambuco");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-pernambuco.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "PI");
        states.put("description", "Piau�");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-piaui.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "RJ");
        states.put("description", "Rio de Janeiro");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-rio-de-janeiro.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "RN");
        states.put("description", "Rio Grande do Norte");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-tocantins.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "RS");
        states.put("description", "Rio Grande do Sul");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-rio-grande-do-sul.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "RO");
        states.put("description", "Rond�nia");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-rondonia.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "RR");
        states.put("description", "Roraima");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-roraima.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "SC");
        states.put("description", "Santa Catarina");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-santa-catarina.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "SP");
        states.put("description", "S�o Paulo");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-sao-paulo.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "SE");
        states.put("description", "Sergipe");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-sergipe.png");
        items.add(states);

        states = new HashMap<String, String>();
        states.put("title", "TO");
        states.put("description", "Tocantins");
        states.put("flag", "http://www.estadosecapitaisdobrasil.com/imagens/bandeiras/estados/pequeno/bandeira-tocantins.png");
        items.add(states);

        return items;
    }
}
