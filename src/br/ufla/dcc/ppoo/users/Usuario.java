package br.ufla.dcc.ppoo.users;

import br.ufla.dcc.ppoo.apps.Aplicativo;
import br.ufla.dcc.ppoo.miscellaneous.Data;
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
    private final List<Aplicativo> apps;
    private Data dataNasce;

    /**
     * Novo usuário padrão
     * @param login Login
     * @param senha Senha
     * @param nome Nome
     */
    public Usuario(String login, String senha, String nome) {
        super(login, senha, nome);
        this.dataNasce = null;
        this.apps = new LinkedList();
    }

    /**
     * Setter
     * @param dataNasce Data de nascimento
     */
    public void setDataNasce(Data dataNasce) {
        this.dataNasce = dataNasce;
    }
    
    /**
     * Getter
     * @return Data de nascimento
     */
    public Data getDataNasce() {
        return dataNasce;
    }
    
    /**
     * Setter, adiciona novo app na lista
     * @param app Referência para o app a ser adicionado
     */
    public void addApp(Aplicativo app) {
        apps.add(app);
    }
    
    /**
     * Remove app
     * @param app Referência para o app a ser removido
     */
    public void removeApp(Aplicativo app) {
        apps.remove(app);
    }

    /**
     * Getter
     * @return Lista de apps imutável
     */
    public List<Aplicativo> getApps() {
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
