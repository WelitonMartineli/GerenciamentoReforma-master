package br.com.mackenzie.menu.visao;

import gueei.binding.Command;
import gueei.binding.collections.CursorCollection;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.visao.ItemAmbienteVM;
import br.com.mackenzie.ambiente.visao.ListagemAmbientes;
import br.com.mackenzie.servicos.visao.Servico;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuVM {
	
	private Context contexto;	
	ImageButton imageButton;
	
	

	/**
	 * Cria uma nova ViewModel de cadastro de ambientes com o contexto de
	 * aplicação especificado.
	 * 
	 * @param contexto
	 *            o contexto da aplicação.
	 */
	public MenuVM(Context contexto) {
		this.contexto = contexto;
	}
	
	// Comando executado quando o botão "Cancelar" da interface gráfica de
	// cadastro de ambientes
	// for acionado. Este comando executa a atividade de listagem de ambientes.
	public Command Ambientes = new Command() {
		public void Invoke(final android.view.View visao, Object... parametros) {
			
			
			Intent intencao = new Intent(visao.getContext(),
					ListagemAmbientes.class);
			intencao.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);			
			visao.getContext().startActivity(intencao);			
			
			
//			//imageButton = (ImageButton) findViewById(R.id.imgBtnAmbientes);
//			 
//			imageButton.setOnClickListener(new OnClickListener() {
//	 
//				@Override
//				public void onClick(View arg0) {
//					
//				   Toast.makeText(visao.getContext(),
//					"Ambientes", Toast.LENGTH_SHORT).show();
//				   
//					Intent intencao = new Intent(visao.gSeetContext(),
//							ListagemAmbientes.class);
//					intencao.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					visao.getContext().startActivity(intencao);
//	 
//				}
//	 
//			});
			
		};	
	
	};
	
	
	// Comando executado quando o botão "Cancelar" da interface gráfica de
	// cadastro de ambientes
	// for acionado. Este comando executa a atividade de listagem de servicos.
	public Command Servicos = new Command() {
		public void Invoke(final android.view.View visao, Object... parametros) {
			
			
			Intent intencao = new Intent(visao.getContext(),
					Servico.class);
			intencao.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);			
			visao.getContext().startActivity(intencao);			
			
			
//			//imageButton = (ImageButton) findViewById(R.id.imgBtnAmbientes);
//			 
//			imageButton.setOnClickListener(new OnClickListener() {
//	 
//				@Override
//				public void onClick(View arg0) {
//					
//				   Toast.makeText(visao.getContext(),
//					"Ambientes", Toast.LENGTH_SHORT).show();
//				   
//					Intent intencao = new Intent(visao.getContext(),
//							ListagemAmbientes.class);
//					intencao.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					visao.getContext().startActivity(intencao);
//	 
//				}
//	 
//			});
			
		};	
	
	};	
	
}
