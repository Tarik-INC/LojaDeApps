package br.ufla.dcc.ppoo.devices;

import br.ufla.dcc.ppoo.miscellaneous.SistemaOperacional;
import br.ufla.dcc.ppoo.users.Usuario;

public class Dispositivo {
    private String identificador;
    private Usuario proprietario;
    private SistemaOperacional sistema;
    private String versao;
    private String modelo;
    private Configuracao config;
}
