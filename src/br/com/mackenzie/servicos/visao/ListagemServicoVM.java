package br.com.mackenzie.servicos.visao;

import gueei.binding.collections.CursorCollection;
import br.com.mackenzie.ambiente.visao.ItemAmbienteVM;
import android.content.Context;

public class ListagemServicoVM {
	
	private Context contexto;	
	// Coleção de cursores de ambientes.
	public CursorCollection<ItemAmbienteVM> Ambientes = new CursorCollection<ItemAmbienteVM>(ItemAmbienteVM.class);	
	

	
	/**
	 * Cria uma nova ViewModel de cadastro de ambientes com o contexto de
	 * aplicação especificado.
	 * 
	 * @param contexto
	 *            o contexto da aplicação.
	 */
	public ListagemServicoVM(Context contexto) {
		this.contexto = contexto;
				
		// Recupera os dados do banco e preenche a coleção de cursores.
		//preencherLista();		
	}		
	
	public ListagemServicoVM(Servico servico, Context contexto) {
		this.contexto = contexto;
	}	
	
	
	
	
}
