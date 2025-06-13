package persistencia;

import java.util.List;

import model.Contato;

public interface IContatoDAO {
    public boolean salvar(Contato contato);
    public boolean atualizar(Contato contato);
    public boolean excluir(Contato contato);
    public List<Contato> listar();

}
