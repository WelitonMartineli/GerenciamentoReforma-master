package br.com.mackenzie.ambiente.visao;

import java.text.ParseException;

import gueei.binding.Command;
import gueei.binding.Observable;
import gueei.binding.collections.CursorCollection;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.com.mackenzie.ambiente.dao.AmbienteDao;
import br.com.mackenzie.ambiente.modelo.Ambiente;

/**
 * Esta classe implementa uma ViewModel para a funcionalidade de listagem de ambientes utilizando
 * uma cole��o de cursores. Outra alternativa seria utilizar ArrayLists observ�veis.
 * A discuss�o sobre vantagens e desvantagens de cada pode ser encontrada no artigo.
 * Um exemplo de uso de arraylists observ�veis pode ser encontrado no c�digo-fonte do artigo
 * de Giuliano Firmino (MundoJ n.54).        
 */
public class ListaAmbienteVM {

	// Contexto - armazenado para quando for necess�rio acessar o contexto da
	// aplica��o. Nos m�todos de execu��o de comandos do Android Binding, o
	// contexto da vis�o
	// pode ser recuperado por meio da View recebida por par�metro pelo m�todo
	// invoke:
	// View.getContext().
	// Nos c�digos apresentados para este exemplo, este contexto n�o � diretamente utilizado.
	private Context contexto;
	
	// Cole��o de cursores de ambientes.
	public CursorCollection<ItemAmbienteVM> Ambientes = new CursorCollection<ItemAmbienteVM>(ItemAmbienteVM.class);	

	public final Observable<Object> ItemSelecionado = new Observable<Object>(Object.class);
	
	// Ao acionar o bot�o "Adicionar", na interface gr�fica de listagem de ambientes, este comando
	// ser� executado e abrir� a interface gr�fica de cadastro de ambientes.
	public Command Adicionar = new Command() {		
		@Override
		public void Invoke(View visao, Object... argumentos) {
			Intent intencao = new Intent(visao.getContext(), CadastroAmbiente.class);
			// Caso a atividade ListagemAmbientes tivesse sido passada como contexto (Context), n�o seria
			// necess�rio definir essa flag.
			intencao.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        ListaAmbienteVM.this.contexto.startActivity(intencao);
		}
	};
	
	/**
	 * Cria uma ViewModel de listagem de ambientes especificando um contexto de aplica��o.
	 * @param contexto o contexto de aplica��o.
	 */
	public ListaAmbienteVM(Context contexto) {
		this.contexto = contexto;
		
		// Recupera os dados do banco e preenche a cole��o de cursores.
		preencherLista();
	}
	
	/**
	 * Preenche a cole��o de cursores com os dados do banco.
	 */
	private void preencherLista() {
		Ambientes.setCursor(AmbienteDao.listarTodos());
	}
	

	public Command Selecionar = new Command() {
		@Override
		public void Invoke(View visao, Object... argumentos) {
			// Converte o item atualmente selecionado (armazenado como um observ�vel de
			// objeto) para um ItemAmbienteVM.
			ItemAmbienteVM itemSelecionado = ((ItemAmbienteVM)ItemSelecionado.get());

			// Cria um ambiente para transferi-lo para View de edi��o de ambientes.
			// Aqui, um ItemAmbienteVM poderia ser transferido, mas, para desacoplar
			// as Views da aplica��o, optou-se por transferir um Ambiente.
			Ambiente ambienteParaEdicao = new Ambiente();
			ambienteParaEdicao.setId((int)itemSelecionado.Id.get().longValue());
			ambienteParaEdicao.setNome(itemSelecionado.Nome.get());
			ambienteParaEdicao.setPorta(itemSelecionado.Porta.get());
			ambienteParaEdicao.setJanela(itemSelecionado.Janela.get());			
			ambienteParaEdicao.setMetragem(itemSelecionado.Metragem.get());
			
			// Cria um pacote de par�metros para passar para a atividade de edi��o de ambientes.
			Bundle parametros = new Bundle();			
			parametros.putSerializable("ambiente", ambienteParaEdicao);
			
			// Abre a atividade de edi��o de ambientes.
			Intent intencao = new Intent(visao.getContext(), EdicaoAmbiente.class);
			intencao.putExtras(parametros);
			visao.getContext().startActivity(intencao);				
		}
	};
	
	
}

