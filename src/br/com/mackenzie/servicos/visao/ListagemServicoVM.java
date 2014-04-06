package br.com.mackenzie.servicos.visao;

import gueei.binding.collections.CursorCollection;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.visao.ItemAmbienteVM;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

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
				
	
	}		
	
	public ListagemServicoVM(Servico servico, Context contexto) {
		this.contexto = contexto;
	}	
	
	

	
}
