package br.ufla.dcc.ppoo.users;

import br.ufla.dcc.ppoo.apps.Licenca;
import br.ufla.dcc.ppoo.miscellaneous.Data;
import br.ufla.dcc.ppoo.users.enums.TipoUsuario;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Usuário padrão do sistema
 * @author william
 */
public class Usuario extends Cadastro {
    private final List<Licenca> apps;
    private final Data dataNasce;
    private String email;
    private TipoUsuario tipo;
    private double credito;

    /**
     * Novo usuário padrão, demais atributos fazer um set
     * @param login Login
     * @param senha Senha
     * @param nome Nome
     * @param dataNasce Data de nascimento
     */
    public Usuario(String login, String senha, String nome, Data dataNasce) {
        super(login, senha, nome);
        this.dataNasce = dataNasce;
        this.tipo = null;
        this.email = null;
        this.apps = new LinkedList();
    }

    /**
     * Setter
     * @param email E-mail do usuário
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter
     * @param tipo Tipo de conta (enum)
     */
    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    /**
     * Setter, adiciona crédito (recarga)
     * @param creditoPlus Valor a ser adicionado
     */
    public void addCredito(double creditoPlus) {
        credito += creditoPlus;
    }
    
    /**
     * Setter, adiciona nova licenca de app
     * @param licenca Referência para licença a ser adicionada
     */
    public void addApp(Licenca licenca) {
        apps.add(licenca);
    }
    
    /**
     * Remove licenca de app
     * @param licenca Referência para licença a ser removida
     */
    public void removeApp(Licenca licenca) {
        apps.remove(licenca);
    }
        
    /**
     * Getter
     * @return E-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter
     * @return Tipo conta (enum)
     */
    public TipoUsuario getTipo() {
        return tipo;
    }

    /**
     * Getter
     * @return Valor do saldo
     */
    public double getCredito() {
        return credito;
    }

    /**
     * Getter
     * @return Data de nascimento
     */
    public Data getDataNasce() {
        return dataNasce;
    }

    /**
     * Getter
     * @return Lista de licenças (apps) imutável
     */
    public List<Licenca> getApps() {
        return Collections.unmodifiableList(apps);
    }
    
    /**
     * Idade de acordo com a data e a hora do sistema
     * @return Idade atual, se for negativo é inválido (data do futuro)
     */
    public int getIdade() {
        Date date = new Date();
        int diaHoje = Integer.parseInt( new SimpleDateFormat("dd").format(date) );
        int mesHoje = Integer.parseInt( new SimpleDateFormat("MM").format(date) );
        int anoHoje = Integer.parseInt( new SimpleDateFormat("yyyy").format(date) );
        
        int deltaDia = diaHoje - dataNasce.getDia();
        int deltaMes = mesHoje - dataNasce.getMes();
        int deltaAno = anoHoje - dataNasce.getAno();
        
        if (deltaMes > 0) {
            return deltaAno;
        }
        else if (deltaMes < 0) {
            return deltaAno - 1;
        }
        else {
            if (deltaDia >= 0) {
                return deltaAno;
            }
            else {
                return deltaAno - 1;
            }
        }
    }
    
}
