package br.com.mackenzie.ambiente.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import br.com.mackenzie.ambiente.modelo.Ambiente;
import br.com.mackenzie.dao.BDHelper;

/**
 * Esta classe implementa o padrão de projeto DAO (Data Access Object) para
 * ambientes. Ela é responsável por gerenciar o banco de dados de ambientes.
 * 
 * @author Leandro Luque, Érico Veriscimo, Girdácio Pereira
 * @version 1.0
 * @since 1.0
 */
public class AmbienteDao {

	/**
	 * Retorna todos os ambientes armazenados no banco ordenados pelo nome. OBS:
	 * Ao invés de usar cursores, seria possível retornar uma lista de ambientes.
	 * 
	 * @return um cursor com todos os ambientes.
	 */
	public static Cursor listarTodos() {
		return BDHelper
				.getInstance()
				.getBanco()
				.query(BDHelper.AmbienteTabela,
						new String[] { BDHelper.AmbienteColunas.ID.nome(),
								BDHelper.AmbienteColunas.NOME.nome(),
								BDHelper.AmbienteColunas.PORTA.nome(),
								BDHelper.AmbienteColunas.JANELA.nome(),
								BDHelper.AmbienteColunas.METRAGEM.nome() },
						null, null, null, null,
						BDHelper.AmbienteColunas.NOME.nome());

	}

	/**
	 * Insere no banco de dados o ambiente especificado por parâmetro.
	 * 
	 * @param ambiente
	 *            o ambiente que deve ser inserido.
	 * @return verdadeiro, se o ambiente foi incluído. falso, caso contrário.
	 */
	public static boolean inserir(Ambiente ambiente) {
		// Tenta inserir os dados no banco.
		ContentValues valores = new ContentValues();
		valores.put(BDHelper.AmbienteColunas.NOME.nome(), ambiente.getNome());
		valores.put(BDHelper.AmbienteColunas.PORTA.nome(),
				ambiente.getPorta());
		valores.put(BDHelper.AmbienteColunas.JANELA.nome(),
				ambiente.getJanela());
		valores.put(BDHelper.AmbienteColunas.METRAGEM.nome(),
				ambiente.getMetragem());
		long resultado = BDHelper.getInstance().getBanco()
				.insert(BDHelper.AmbienteTabela, null, valores);
		// Retorna o resultado da inserção.
		if (resultado > 0) {
			Log.i(BDHelper.AmbienteTabela, "O ambiente " + ambiente.getNome()
					+ " foi gravado com sucesso!");
			return true;
		} else {
			Log.i(BDHelper.AmbienteTabela, "O ambiente " + ambiente.getNome()
					+ " não foi gravado!");
			return false;
		}
	}

	/**
	 * Atualiza no banco de dados o ambiente especificado por parâmetro.
	 * 
	 * @param ambiente
	 *            o ambiente que deve ser atualizado.
	 * @return verdadeiro, se o ambiente foi atualizado. falso, caso contrário.
	 */
	public static boolean atualizar(Ambiente ambiente) {
		// Tenta atualizar os dados no banco.
		ContentValues valores = new ContentValues();
		valores.put(BDHelper.AmbienteColunas.NOME.nome(), ambiente.getNome());
		valores.put(BDHelper.AmbienteColunas.PORTA.nome(),
				ambiente.getPorta());
		valores.put(BDHelper.AmbienteColunas.JANELA.nome(),
				ambiente.getJanela());
		valores.put(BDHelper.AmbienteColunas.METRAGEM.nome(),
				ambiente.getMetragem());
		long resultado = BDHelper
				.getInstance()
				.getBanco()
				.update(BDHelper.AmbienteTabela, valores,
						BDHelper.AmbienteColunas.ID.nome() + "=?",
						new String[] { ambiente.getId().toString() });
		// Retorna o resultado da atualizacao.
		if (resultado > 0) {
			Log.i(BDHelper.AmbienteTabela, "O ambiente " + ambiente.getId()
					+ " foi atualizado com sucesso!");
			return true;
		} else {
			Log.i(BDHelper.AmbienteTabela, "O ambiente " + ambiente.getId()
					+ " não foi atualizado!");
			return false;
		}
	}

	/**
	 * Apaga do banco de dados o ambiente especificado por parâmetro.
	 * 
	 * @param ambiente
	 *            o ambiente que deve ser excluído do banco.
	 * @return verdadeiro, se o ambiente foi excluído. falso, caso contrário.
	 */
	public static boolean excluir(Ambiente ambiente) {
		if (BDHelper
				.getInstance()
				.getBanco()
				.delete(BDHelper.AmbienteTabela,
						BDHelper.AmbienteColunas.ID.name() + "=?",
						new String[] { ambiente.getId().toString() }) > 0) {
			Log.i(BDHelper.AmbienteTabela, "O ambiente " + ambiente.getNome()
					+ " foi excluído com sucesso!");
			return true;
		} else {
			Log.i(BDHelper.AmbienteTabela, "O ambiente " + ambiente.getNome()
					+ " não foi excluído!");
			return false;
		}
	}

}
