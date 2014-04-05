package br.com.mackenzie.servicos.visao;

import gueei.binding.collections.CursorCollection;
import br.com.mackenzie.ambiente.visao.ItemAmbienteVM;
import android.content.Context;

public class ListagemServicoVM {
	
	private Context contexto;	
	// Cole��o de cursores de ambientes.
	public CursorCollection<ItemAmbienteVM> Ambientes = new CursorCollection<ItemAmbienteVM>(ItemAmbienteVM.class);	
	

	
	/**
	 * Cria uma nova ViewModel de cadastro de ambientes com o contexto de
	 * aplica��o especificado.
	 * 
	 * @param contexto
	 *            o contexto da aplica��o.
	 */
	public ListagemServicoVM(Context contexto) {
		this.contexto = contexto;
				
		// Recupera os dados do banco e preenche a cole��o de cursores.
		//preencherLista();		
	}		
	
	public ListagemServicoVM(Servico servico, Context contexto) {
		this.contexto = contexto;
	}	
	
	
	
	
}
