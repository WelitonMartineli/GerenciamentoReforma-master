package br.com.mackenzie.servicos.visao;

import gueei.binding.Command;
import gueei.binding.Observable;
import gueei.binding.collections.CursorCollection;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.dao.AmbienteDao;
import br.com.mackenzie.ambiente.modelo.Ambiente;
import br.com.mackenzie.ambiente.visao.EdicaoAmbiente;
import br.com.mackenzie.ambiente.visao.ItemAmbienteVM;
import br.com.mackenzie.ambiente.visao.ListagemAmbientes;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ServicoVM {

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
	public ServicoVM(Context contexto) {
		this.contexto = contexto;
				
		// Recupera os dados do banco e preenche a coleção de cursores.
		preencherLista();		
	}		
	
	public ServicoVM(Servico servico, Context contexto) {
		this.contexto = contexto;
	}	
	
	
	/**
	 * Preenche a coleção de cursores com os dados do banco.
	 */
	private void preencherLista() {
		Ambientes.setCursor(AmbienteDao.listarTodos());
	}	
	
	public Command Selecionar = new Command() {
		@Override
		public void Invoke(View visao, Object... argumentos) {
			
			Intent intencao = new Intent(visao.getContext(),
					ListagemServico.class);
			visao.getContext().startActivity(intencao);
		}	
			
	};
}
