package br.com.mackenzie.ambiente.visao;

import gueei.binding.Command;
import gueei.binding.labs.validation.ModelValidator;
import gueei.binding.labs.validation.ValidationResult;
import gueei.binding.labs.validation.validators.MaxLength;
import gueei.binding.labs.validation.validators.MinLength;
import gueei.binding.labs.validation.validators.Required;
import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.StringObservable;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.dao.AmbienteDao;
import br.com.mackenzie.ambiente.modelo.Ambiente;
import br.com.mackenzie.upload.visao.SQLiteDemoActivity;

/**
 * Esta classe implementa uma ViewModel para a funcionalidade de cadastro e
 * edição de ambientes. Nela são especificadas anotações que serão utilizadas
 * para a validação dos dados especificados pelo usuário.
 * 
 */
public class AmbienteCadastroVM {

	// Contexto - armazenado para quando for necessário acessar o contexto da
	// aplicação. Nos métodos de execução de comandos do Android Binding, o
	// contexto da visão
	// pode ser recuperado por meio da View recebida por parâmetro pelo método
	// invoke:
	// View.getContext().
	// Nos códigos apresentados para este exemplo, este contexto não é diretamente utilizado.
	private Context contexto;

	// Identificação do ambiente - para o caso de edição de ambientes.
	private Integer id;
	// Utilizado para armazenar o ambiente atualmente em edição (não é utilizado
	// durante a inserção de ambientes).
	private Ambiente ambienteEmEdicao;

	// *****************************************************
	// Observáveis.
	@Required(ErrorMessage = "O nome é obrigatório")
	@MinLength(ErrorMessage = "O nome deve ter no mínimo 2 caracteres", Length = 2)
	@MaxLength(ErrorMessage = "O nome deve ter no máximo 100 caracteres", Length = 100)
	public StringObservable Nome = new StringObservable();


	public StringObservable Porta = new StringObservable();

	// OBS:
	// No projeto original, a quantidade do ambiente era cadastrada em outra interface
	// (gestão de estoque) e aqui era apenas exibida (não alterada). Neste contexto,
	// havia sido utilizado um IntegerObservable.
	// No entanto, para permitir a alteração da quantidade na interface de cadastro
	// e edição de ambientes, o observável foi alterado para StringObservable, pois
	// um IntegerObservable permite a vinculação em apenas em um (1) sentido,
	// ou seja, o formulário será atualizado quando o observável mudar, mas não o
	// contrário.
	// public IntegerObservable Quantidade = new IntegerObservable(10);
	//@Required(ErrorMessagRes = "ambiente_cadastro_quantidade_erro")
	// A mensagem de erro não será exibida porque
	// há um erro na anotação no framework.
	// O atributo deveria se chamar ErrorMessageRes,
	// mas chama-se ErrorMessagRes (falta o "e" no Message).
	public StringObservable Janela = new StringObservable();
	public StringObservable Metragem = new StringObservable();

	// Atributos para o caso da edição de ambientes.
	public BooleanObservable Edicao = new BooleanObservable(false);

	/**
	 * Cria uma nova ViewModel para a edição do ambiente especificado. O contexto
	 * da aplicação também é passado por parâmetro.
	 * 
	 * @param ambiente
	 *            o ambiente que deve ser editado.
	 * @param contexto
	 *            o contexto da aplicação.
	 */
	public AmbienteCadastroVM(Ambiente ambiente, Context contexto) {
		this.ambienteEmEdicao = ambiente;
		this.contexto = contexto;
		this.id = ambiente.getId();
		Edicao.set(true);
		Nome.set(ambiente.getNome());
		Porta.set(Integer.toString(ambiente.getPorta()));
		Janela.set(Integer.toString(ambiente.getJanela()));
		Metragem.set(Integer.toString(ambiente.getMetragem()));
	}

	/**
	 * Cria uma nova ViewModel de cadastro de ambientes com o contexto de
	 * aplicação especificado.
	 * 
	 * @param contexto
	 *            o contexto da aplicação.
	 */
	public AmbienteCadastroVM(Context contexto) {
		this.contexto = contexto;
	}

	// Comando executado quando o botão "Salvar" da interface gráfica de
	// cadastro de ambiente
	// for acionado. Este comando tentar gravar (inserir ou atualizar)
	// o ambiente no banco de dados.
	public Command Salvar = new Command() {
		public void Invoke(android.view.View visao, Object... parametros) {
			// Valida os dados da visão por meio das anotações especificadas na
			// ViewModel.
			ModelValidator validador = new ModelValidator(contexto,
					AmbienteCadastroVM.this, R.string.class);
			ValidationResult resultado = validador.ValidateModel();
			// Se a validação foi realizada sem erros.
			if (resultado.isValid()) {
				// Cria um ambiente e grava os dados no banco.
				Ambiente ambiente = new Ambiente();
				ambiente.setNome(Nome.get());
				ambiente.setPorta(Integer.parseInt(Porta.get()));
				ambiente.setJanela(Integer.parseInt(Janela.get()));
				ambiente.setMetragem(Integer.parseInt(Metragem.get()));
				// Se tratar-se de uma atualização.
				if(Edicao.get()) {
					ambiente.setId(id);
					AmbienteDao.atualizar(ambiente);
					// Exibe uma mensagem de gravação realizada com sucesso.
					Toast.makeText(
							visao.getContext(),
							visao.getContext()
									.getResources()
									.getString(
											R.string.ambiente_cadastro_atualizado_sucesso),
							Toast.LENGTH_LONG).show();					
				}
				else {
					AmbienteDao.inserir(ambiente);
					// Exibe uma mensagem de gravação realizada com sucesso.
					Toast.makeText(
							visao.getContext(),
							visao.getContext()
									.getResources()
									.getString(
											R.string.ambiente_cadastro_gravado_sucesso),
							Toast.LENGTH_LONG).show();
				}
				// Volta para a listagem de ambientes.
				voltarParaListagemAmbientes(visao.getContext());
			// Se ocorreu algum erro de validação, exibe uma mensagem de
			// erro.
			} else {
				StringBuffer mensagem = new StringBuffer();
				mensagem.append(visao.getContext().getResources()
						.getString(R.string.ambiente_cadastro_erro)
						+ "\n");
				for (String erro : resultado.getValidationErrors()) {
					mensagem.append("- ").append(erro).append("\n");
				}
				Toast.makeText(visao.getContext(), mensagem.toString(),
						Toast.LENGTH_LONG).show();
			}
		};
	};

	// Comando executado quando o botão "Excluir" da interface gráfica de
	// cadastro de ambientes
	// for acionado. Este comando exclui o ambiente atual e retorna para a
	// listagem de ambientes.
	public Command Excluir = new Command() {
		public void Invoke(android.view.View visao, Object... parametros) {
			AmbienteDao.excluir(new Ambiente(id));
			// Exibe uma mensagem de exclusão realizada com sucesso.
			Toast.makeText(
					visao.getContext(),
					visao.getContext()
							.getResources()
							.getString(
									R.string.ambiente_cadastro_excluido_sucesso),
					Toast.LENGTH_LONG).show();
			voltarParaListagemAmbientes(visao.getContext());
		};
	};

	// Comando executado quando o botão "Cancelar" da interface gráfica de
	// cadastro de ambientes
	// for acionado. Este comando executa a atividade de listagem de ambientes.
	public Command Cancelar = new Command() {
		public void Invoke(android.view.View visao, Object... parametros) {
			voltarParaListagemAmbientes(visao.getContext());
		};
	};

	// Abre a atividade de listagem de ambientes.
	public void voltarParaListagemAmbientes(Context contexto) {
		Intent intencao = new Intent(contexto,
				ListagemAmbientes.class);
		contexto.startActivity(intencao);
	}
	
	public Command Imagem = new Command() {
		public void Invoke(android.view.View visao, Object... parametros) {

			Intent intencao = new Intent(visao.getContext(),
					SQLiteDemoActivity.class);
			
			Bundle param = new Bundle();			
			param.putSerializable("ambiente", ambienteEmEdicao);
			intencao.putExtras(param);	
			
			visao.getContext().startActivity(intencao);
		};
	};
	
	
	
}
