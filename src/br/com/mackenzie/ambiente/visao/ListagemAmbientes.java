package br.com.mackenzie.ambiente.visao;

import gueei.binding.app.BindingActivity;
import android.os.Bundle;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.modelo.Ambiente;

/**
 * Esta classe implementa uma atividade de listagem de Ambientes. O papel desta
 * classe � relacionar um layout XML a uma vis�o de modelo (ViewModel). Como na
 * classe CadastroAmbiente foi demonstrada a vincula��o utilizando a nova vers�o
 * de BindingActivity (V30), optamos por ilustrar aqui a implementa��o com a
 * vers�o antiga.
 * 
 */
public class ListagemAmbientes extends BindingActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Lista vinculada a uma cole��o de cursores.
		setAndBindRootView(R.layout.ambiente_lista, new ListaAmbienteVM(
				getApplicationContext()));
	}

}
