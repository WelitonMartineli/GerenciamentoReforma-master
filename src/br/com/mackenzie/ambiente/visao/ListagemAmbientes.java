package br.com.mackenzie.ambiente.visao;

import gueei.binding.app.BindingActivity;
import android.os.Bundle;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.modelo.Ambiente;

/**
 * Esta classe implementa uma atividade de listagem de Ambientes. O papel desta
 * classe é relacionar um layout XML a uma visão de modelo (ViewModel). Como na
 * classe CadastroAmbiente foi demonstrada a vinculação utilizando a nova versão
 * de BindingActivity (V30), optamos por ilustrar aqui a implementação com a
 * versão antiga.
 * 
 * @author Leandro Luque, Érico Veriscimo, Girdácio Pereira
 * @version 1.0
 * @since 1.0
 */
public class ListagemAmbientes extends BindingActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Lista vinculada a uma coleção de cursores.
		setAndBindRootView(R.layout.ambiente_lista, new ListaAmbienteVM(
				getApplicationContext()));
	}

}
