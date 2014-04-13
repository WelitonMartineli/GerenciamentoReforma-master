package br.com.mackenzie.servico.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import br.com.mackenzie.ambiente.modelo.Ambiente;
import br.com.mackenzie.dao.BDHelper;
import br.com.mackenzie.servico.modelo.ServicoAmbiente;

public class ServicoAmbienteDao {

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
				.query(BDHelper.ServicoAmbienteTabela,
						new String[] { BDHelper.ServicoAmbienteColunas.ID.nome(),
								BDHelper.ServicoAmbienteColunas.ID_AMBIENTE.nome(),
								BDHelper.ServicoAmbienteColunas.COD_SERVICO.nome(),
								BDHelper.ServicoAmbienteColunas.DES_SERVICO.nome() },
						null, null, null, null,
						BDHelper.ServicoAmbienteColunas.DES_SERVICO.nome());

	}

	/**
	 * Insere no banco de dados o ambiente especificado por parâmetro.
	 * 
	 * @param ambiente
	 *            o ambiente que deve ser inserido.
	 * @return verdadeiro, se o ambiente foi incluído. falso, caso contrário.
	 */
	public static boolean inserir(ServicoAmbiente servicoAmbiente) {
		// Tenta inserir os dados no banco.
		ContentValues valores = new ContentValues();
		valores.put(BDHelper.ServicoAmbienteColunas.ID.nome(), servicoAmbiente.getId());
		valores.put(BDHelper.ServicoAmbienteColunas.COD_SERVICO.nome(), servicoAmbiente.getCodServico());
		valores.put(BDHelper.ServicoAmbienteColunas.DES_SERVICO.nome(),
				servicoAmbiente.getDesServico());
		valores.put(BDHelper.ServicoAmbienteColunas.ID_AMBIENTE.nome(),
				servicoAmbiente.getIdAmbiente());
		long resultado = BDHelper.getInstance().getBanco()
				.insert(BDHelper.ServicoAmbienteTabela, null, valores);
		// Retorna o resultado da inserção.
		if (resultado > 0) {
			Log.i(BDHelper.ServicoAmbienteTabela, "O servico " + servicoAmbiente.getDesServico()
					+ " foi gravado com sucesso!");
			return true;
		} else {
			Log.i(BDHelper.AmbienteTabela, "O ambiente " + servicoAmbiente.getDesServico()
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
	public static boolean excluir(ServicoAmbiente servicoAmbiente) {
		if (BDHelper
				.getInstance()
				.getBanco()
				.delete(BDHelper.ServicoAmbienteTabela,
						BDHelper.ServicoAmbienteColunas.ID.name() + "=?",
						new String[] { servicoAmbiente.getId().toString() }) > 0) {
			Log.i(BDHelper.ServicoAmbienteTabela, "O ambiente " + servicoAmbiente.getDesServico()
					+ " foi excluído com sucesso!");
			return true;
		} else {
			Log.i(BDHelper.ServicoAmbienteTabela, "O ambiente " + servicoAmbiente.getDesServico()
					+ " não foi excluído!");
			return false;
		}
	}

	public static boolean findByServicoAmbientebyIDs(ServicoAmbiente servicoAmbiente) {
		
		Cursor result = BDHelper
		.getInstance()
		.getBanco().rawQuery("select id, id_ambiente from servico_ambiente where id = " + servicoAmbiente.getId() +
				             " and id_ambiente = " + servicoAmbiente.getIdAmbiente(), null);

		
		if (result.getCount() > 0 ){
			return true;
		}
		
		return false;
	}

	public static String[] listarTodosServicos(Ambiente ambiente) {
		
		Cursor result = BDHelper
		.getInstance()
		.getBanco().rawQuery("select des_servico from servico_ambiente where id_ambiente = " + ambiente.getId(), null);

		result.moveToFirst();
	    ArrayList<String> names = new ArrayList<String>();
	    while(!result.isAfterLast()) {
	        names.add(result.getString(result.getColumnIndex("des_servico")));
	        result.moveToNext();
	    }
	    result.close();
	    
	    return (String[]) names.toArray(new String[0]);
		
	}	
	
}
