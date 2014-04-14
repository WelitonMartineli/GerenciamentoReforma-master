package br.com.mackenzie;

import android.os.AsyncTask;

/**
 * Esta classe ilustra a execução de um código demorado em background.
 * Deve ser usada quando é necessário executar um comando demorado no MVVM.
 * Como os comandos são executados na thread de visão e, caso a execução não
 * seja em background, a interação com o usuário pode ser prejudicada.
 */
public class TarefaAssincrona extends AsyncTask<Void, Void, Void>{  

	  @Override  
      protected Void doInBackground(Void... params) {  
           // Código que realiza algum processamento demorado.
		  try {
			  Thread.sleep(10000);
		  }
		  catch(InterruptedException erro) {
			  // ... Tratamento ou lançamento da exceção.
		  }
		  return null;
      }  

}  