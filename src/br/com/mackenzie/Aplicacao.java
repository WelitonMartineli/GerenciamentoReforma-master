package br.com.mackenzie;

import gueei.binding.Binder;
import android.app.Application;
import br.com.mackenzie.dao.BDHelper;

/**
 * Esta classe é responsável por inicializar o suporte ao framework Android Binding.
 * Para tanto, o arquivo AndroidManifest.xml deve ser configurado, conforme trecho de código seguinte:
 * ...
 * <application
 *       android:name="br.com.mundoj.Aplicacao"
 * ...
 * Desta forma, quando o processo da aplicação for criado, o método onCreate será executado.
 * @author Leandro Luque, Érico Veriscimo, Girdácio Pereira
 * @version 1.0
 * @since 1.0
 */
public class Aplicacao extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		// Inicializa o framework especificando um kernel personalizado.
		// Este kernel cadastra um provedor para o componente de seleção de datas (DatePicker).
		// Uma alternativa para isso utilizada em versões anteriores do framework envolveria inicializar o
		// framework pelo modo padrão (conforme comentário mais abaixo) e invocar o método de registro
		//	do provedor: AttributeBinder.getInstance().registerProvider(new DatePickerBindingProvider());
 		Binder.init(this, new NovoKernel());

 		// Modo padrão de inicialização do framework:
 		// Binder.init(this);
 		// A invocação da inicialização do framework listada acima é equivalente é passar
 		// por parâmetro um kernel padrão de configuração:
 		// Binder.init(this, new DefaultKernel());
 		
 		// Inicializa o banco de dados da aplicação (não tem a ver com o Android Binding e sim com o SQLite).
 		BDHelper.inicializar(getApplicationContext());
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		// Finaliza o banco de dados da aplicação.
		BDHelper.finalizar();
	}
	
}
