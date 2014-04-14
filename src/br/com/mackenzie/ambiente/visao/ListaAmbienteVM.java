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
 * uma coleção de cursores. Outra alternativa seria utilizar ArrayLists observáveis.
 * A discussão sobre vantagens e desvantagens de cada pode ser encontrada no artigo.
 * Um exemplo de uso de arraylists observáveis pode ser encontrado no código-fonte do artigo
 * de Giuliano Firmino (MundoJ n.54).        
 */
public class ListaAmbienteVM {

	// Contexto - armazenado para quando for necessário acessar o contexto da
	// aplicação. Nos métodos de execução de comandos do Android Binding, o
	// contexto da visão
	// pode ser recuperado por meio da View recebida por parâmetro pelo método
	// invoke:
	// View.getContext().
	// Nos códigos apresentados para este exemplo, este contexto não é diretamente utilizado.
	private Context contexto;
	
	// Coleção de cursores de ambientes.
	public CursorCollection<ItemAmbienteVM> Ambientes = new CursorCollection<ItemAmbienteVM>(ItemAmbienteVM.class);	

	public final Observable<Object> ItemSelecionado = new Observable<Object>(Object.class);
	
	// Ao acionar o botão "Adicionar", na interface gráfica de listagem de ambientes, este comando
	// será executado e abrirá a interface gráfica de cadastro de ambientes.
	public Command Adicionar = new Command() {		
		@Override
		public void Invoke(View visao, Object... argumentos) {
			Intent intencao = new Intent(visao.getContext(), CadastroAmbiente.class);
			// Caso a atividade ListagemAmbientes tivesse sido passada como contexto (Context), não seria
			// necessário definir essa flag.
			intencao.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        ListaAmbienteVM.this.contexto.startActivity(intencao);
		}
	};
	
	/**
	 * Cria uma ViewModel de listagem de ambientes especificando um contexto de aplicação.
	 * @param contexto o contexto de aplicação.
	 */
	public ListaAmbienteVM(Context contexto) {
		this.contexto = contexto;
		
		// Recupera os dados do banco e preenche a coleção de cursores.
		preencherLista();
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
			// Converte o item atualmente selecionado (armazenado como um observável de
			// objeto) para um ItemAmbienteVM.
			ItemAmbienteVM itemSelecionado = ((ItemAmbienteVM)ItemSelecionado.get());

			// Cria um ambiente para transferi-lo para View de edição de ambientes.
			// Aqui, um ItemAmbienteVM poderia ser transferido, mas, para desacoplar
			// as Views da aplicação, optou-se por transferir um Ambiente.
			Ambiente ambienteParaEdicao = new Ambiente();
			ambienteParaEdicao.setId((int)itemSelecionado.Id.get().longValue());
			ambienteParaEdicao.setNome(itemSelecionado.Nome.get());
			ambienteParaEdicao.setPorta(itemSelecionado.Porta.get());
			ambienteParaEdicao.setJanela(itemSelecionado.Janela.get());			
			ambienteParaEdicao.setMetragem(itemSelecionado.Metragem.get());
			
			// Cria um pacote de parâmetros para passar para a atividade de edição de ambientes.
			Bundle parametros = new Bundle();			
			parametros.putSerializable("ambiente", ambienteParaEdicao);
			
			// Abre a atividade de edição de ambientes.
			Intent intencao = new Intent(visao.getContext(), EdicaoAmbiente.class);
			intencao.putExtras(parametros);
			visao.getContext().startActivity(intencao);				
		}
	};
	
	
}

