package br.com.mackenzie.servico.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import br.com.mackenzie.dao.BDHelper;
import br.com.mackenzie.servico.modelo.ServicoAmbiente;

public class ServicoAmbienteDao {

	/**
	 * Retorna todos os ambientes armazenados no banco ordenados pelo nome. OBS:
	 * Ao inv�s de usar cursores, seria poss�vel retornar uma lista de ambientes.
	 * 
	 * @return um cursor com todos os ambientes.
	 */
	public static Cursor listarTodos() {
		return BDHelper
				.getInstance()
				.getBanco()
				.query(BDHelper.ServicoAmbienteTabela,
						new String[] { BDHelper.ServicoAmbienteColunas.ID.nome(),
								BDHelper.ServicoAmbienteColunas.ID_AMBIENTE.nome(),
								BDHelper.ServicoAmbienteColunas.COD_SERVICO.nome(),
								BDHelper.ServicoAmbienteColunas.DES_SERVICO.nome() },
						null, null, null, null,
						BDHelper.ServicoAmbienteColunas.DES_SERVICO.nome());

	}

	/**
	 * Insere no banco de dados o ambiente especificado por par�metro.
	 * 
	 * @param ambiente
	 *            o ambiente que deve ser inserido.
	 * @return verdadeiro, se o ambiente foi inclu�do. falso, caso contr�rio.
	 */
	public static boolean inserir(ServicoAmbiente servicoAmbiente) {
		// Tenta inserir os dados no banco.
		ContentValues valores = new ContentValues();
		valores.put(BDHelper.ServicoAmbienteColunas.COD_SERVICO.nome(), servicoAmbiente.getCodServico());
		valores.put(BDHelper.ServicoAmbienteColunas.DES_SERVICO.nome(),
				servicoAmbiente.getDesServico());
		long resultado = BDHelper.getInstance().getBanco()
				.insert(BDHelper.ServicoAmbienteTabela, null, valores);
		// Retorna o resultado da inser��o.
		if (resultado > 0) {
			Log.i(BDHelper.ServicoAmbienteTabela, "O servico " + servicoAmbiente.getDesServico()
					+ " foi gravado com sucesso!");
			return true;
		} else {
			Log.i(BDHelper.AmbienteTabela, "O ambiente " + servicoAmbiente.getDesServico()
					+ " n�o foi gravado!");
			return false;
		}
	}

	/**
	 * Atualiza no banco de dados o ambiente especificado por par�metro.
	 * 
	 * @param ambiente
	 *            o ambiente que deve ser atualizado.
	 * @return verdadeiro, se o ambiente foi atualizado. falso, caso contr�rio.
	 */
	public static boolean atualizar(ServicoAmbiente servicoAmbiente) {
		// Tenta atualizar os dados no banco.
		ContentValues valores = new ContentValues();
		valores.put(BDHelper.ServicoAmbienteColunas.COD_SERVICO.nome(), servicoAmbiente.getCodServico());
		valores.put(BDHelper.ServicoAmbienteColunas.DES_SERVICO.nome(),servicoAmbiente.getDesServico());
		long resultado = BDHelper
				.getInstance()
				.getBanco()
				.update(BDHelper.ServicoAmbienteTabela, valores,
						BDHelper.ServicoAmbienteColunas.ID.nome() + "=?",
						new String[] { servicoAmbiente.getId().toString() });
		// Retorna o resultado da atualizacao.
		if (resultado > 0) {
			Log.i(BDHelper.ServicoAmbienteTabela, "O ambiente " + servicoAmbiente.getId()
					+ " foi atualizado com sucesso!");
			return true;
		} else {
			Log.i(BDHelper.ServicoAmbienteTabela, "O ambiente " + servicoAmbiente.getId()
					+ " n�o foi atualizado!");
			return false;
		}
	}

	/**
	 * Apaga do banco de dados o ambiente especificado por par�metro.
	 * 
	 * @param ambiente
	 *            o ambiente que deve ser exclu�do do banco.
	 * @return verdadeiro, se o ambiente foi exclu�do. falso, caso contr�rio.
	 */
	public static boolean excluir(ServicoAmbiente servicoAmbiente) {
		if (BDHelper
				.getInstance()
				.getBanco()
				.delete(BDHelper.ServicoAmbienteTabela,
						BDHelper.ServicoAmbienteColunas.ID.name() + "=?",
						new String[] { servicoAmbiente.getId().toString() }) > 0) {
			Log.i(BDHelper.ServicoAmbienteTabela, "O ambiente " + servicoAmbiente.getDesServico()
					+ " foi exclu�do com sucesso!");
			return true;
		} else {
			Log.i(BDHelper.ServicoAmbienteTabela, "O ambiente " + servicoAmbiente.getDesServico()
					+ " n�o foi exclu�do!");
			return false;
		}
	}	
	
}
