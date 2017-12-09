package br.ufla.dcc.ppoo.miscellaneous;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Funções Geradoras
 * @author william
 */
public abstract class Gerador {
    /**
     * Gera chave única no sistema com base na hora
     * @return Chave única de 9 dígitos
     */
    public static int gerarChave() {
        /** CUIDADO! Este método ainda pode gerar chaves igual em anos diferentes
         *  ou em um intervalo de tempo muito próximo, mas a chance é quase nula. */
        // DDD - 3 dígitos para o dia do ano (1 a 366)
        // HH  - 2 dígitos para a hora atual (0 a 23)
        // mm  - 2 dígitos para os minutos   (0 a 59)
        // ss  - 2 dígitos para os segundos  (0 a 59)
        // SSS - 3 dígitos para os miliseg   (0 a 999)
        return Integer.parseInt( new SimpleDateFormat("DDDHHmmss").format(new Date()) );
    }
    
    /**
     * Gera String de id com base na hora
     * @return Chave de 23 caracteres
     */
    public static String gerarId() {
        /** CUIDADO! Este método ainda pode gerar chaves igual em um intervalo de 
         *  tempo muito próximo. */
        // yyyy - 4 dígitos para o ano
        // MM   - 2 dígitos para o mês
        // dd   - 2 dígitos para o dia do mês
        return new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss.SSS").format(new Date());
    }
}
