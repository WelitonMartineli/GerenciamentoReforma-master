package br.com.mackenzie;

import gueei.binding.Binder;
import android.app.Application;
import br.com.mackenzie.dao.BDHelper;

/**
 * Esta classe � respons�vel por inicializar o suporte ao framework Android Binding.
 * Para tanto, o arquivo AndroidManifest.xml deve ser configurado, conforme trecho de c�digo seguinte:
 * ...
 * <application
 *       android:name="br.com.mundoj.Aplicacao"
 * ...
 * Desta forma, quando o processo da aplica��o for criado, o m�todo onCreate ser� executado.
 * @author Leandro Luque, �rico Veriscimo, Gird�cio Pereira
 * @version 1.0
 * @since 1.0
 */
public class Aplicacao extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		// Inicializa o framework especificando um kernel personalizado.
		// Este kernel cadastra um provedor para o componente de sele��o de datas (DatePicker).
		// Uma alternativa para isso utilizada em vers�es anteriores do framework envolveria inicializar o
		// framework pelo modo padr�o (conforme coment�rio mais abaixo) e invocar o m�todo de registro
		//	do provedor: AttributeBinder.getInstance().registerProvider(new DatePickerBindingProvider());
 		Binder.init(this, new NovoKernel());

 		// Modo padr�o de inicializa��o do framework:
 		// Binder.init(this);
 		// A invoca��o da inicializa��o do framework listada acima � equivalente � passar
 		// por par�metro um kernel padr�o de configura��o:
 		// Binder.init(this, new DefaultKernel());
 		
 		// Inicializa o banco de dados da aplica��o (n�o tem a ver com o Android Binding e sim com o SQLite).
 		BDHelper.inicializar(getApplicationContext());
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		// Finaliza o banco de dados da aplica��o.
		BDHelper.finalizar();
	}
	
}
