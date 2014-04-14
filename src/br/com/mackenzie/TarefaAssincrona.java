package br.com.mackenzie;

import android.os.AsyncTask;

/**
 * Esta classe ilustra a execu��o de um c�digo demorado em background.
 * Deve ser usada quando � necess�rio executar um comando demorado no MVVM.
 * Como os comandos s�o executados na thread de vis�o e, caso a execu��o n�o
 * seja em background, a intera��o com o usu�rio pode ser prejudicada.
 */
public class TarefaAssincrona extends AsyncTask<Void, Void, Void>{  

	  @Override  
      protected Void doInBackground(Void... params) {  
           // C�digo que realiza algum processamento demorado.
		  try {
			  Thread.sleep(10000);
		  }
		  catch(InterruptedException erro) {
			  // ... Tratamento ou lan�amento da exce��o.
		  }
		  return null;
      }  

}  