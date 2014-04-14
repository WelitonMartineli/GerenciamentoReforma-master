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
 * edi��o de ambientes. Nela s�o especificadas anota��es que ser�o utilizadas
 * para a valida��o dos dados especificados pelo usu�rio.
 * 
 */
public class AmbienteCadastroVM {

	// Contexto - armazenado para quando for necess�rio acessar o contexto da
	// aplica��o. Nos m�todos de execu��o de comandos do Android Binding, o
	// contexto da vis�o
	// pode ser recuperado por meio da View recebida por par�metro pelo m�todo
	// invoke:
	// View.getContext().
	// Nos c�digos apresentados para este exemplo, este contexto n�o � diretamente utilizado.
	private Context contexto;

	// Identifica��o do ambiente - para o caso de edi��o de ambientes.
	private Integer id;
	// Utilizado para armazenar o ambiente atualmente em edi��o (n�o � utilizado
	// durante a inser��o de ambientes).
	private Ambiente ambienteEmEdicao;

	// *****************************************************
	// Observ�veis.
	@Required(ErrorMessage = "O nome � obrigat�rio")
	@MinLength(ErrorMessage = "O nome deve ter no m�nimo 2 caracteres", Length = 2)
	@MaxLength(ErrorMessage = "O nome deve ter no m�ximo 100 caracteres", Length = 100)
	public StringObservable Nome = new StringObservable();


	public StringObservable Porta = new StringObservable();

	// OBS:
	// No projeto original, a quantidade do ambiente era cadastrada em outra interface
	// (gest�o de estoque) e aqui era apenas exibida (n�o alterada). Neste contexto,
	// havia sido utilizado um IntegerObservable.
	// No entanto, para permitir a altera��o da quantidade na interface de cadastro
	// e edi��o de ambientes, o observ�vel foi alterado para StringObservable, pois
	// um IntegerObservable permite a vincula��o em apenas em um (1) sentido,
	// ou seja, o formul�rio ser� atualizado quando o observ�vel mudar, mas n�o o
	// contr�rio.
	// public IntegerObservable Quantidade = new IntegerObservable(10);
	//@Required(ErrorMessagRes = "ambiente_cadastro_quantidade_erro")
	// A mensagem de erro n�o ser� exibida porque
	// h� um erro na anota��o no framework.
	// O atributo deveria se chamar ErrorMessageRes,
	// mas chama-se ErrorMessagRes (falta o "e" no Message).
	public StringObservable Janela = new StringObservable();
	public StringObservable Metragem = new StringObservable();

	// Atributos para o caso da edi��o de ambientes.
	public BooleanObservable Edicao = new BooleanObservable(false);

	/**
	 * Cria uma nova ViewModel para a edi��o do ambiente especificado. O contexto
	 * da aplica��o tamb�m � passado por par�metro.
	 * 
	 * @param ambiente
	 *            o ambiente que deve ser editado.
	 * @param contexto
	 *            o contexto da aplica��o.
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
	 * aplica��o especificado.
	 * 
	 * @param contexto
	 *            o contexto da aplica��o.
	 */
	public AmbienteCadastroVM(Context contexto) {
		this.contexto = contexto;
	}

	// Comando executado quando o bot�o "Salvar" da interface gr�fica de
	// cadastro de ambiente
	// for acionado. Este comando tentar gravar (inserir ou atualizar)
	// o ambiente no banco de dados.
	public Command Salvar = new Command() {
		public void Invoke(android.view.View visao, Object... parametros) {
			// Valida os dados da vis�o por meio das anota��es especificadas na
			// ViewModel.
			ModelValidator validador = new ModelValidator(contexto,
					AmbienteCadastroVM.this, R.string.class);
			ValidationResult resultado = validador.ValidateModel();
			// Se a valida��o foi realizada sem erros.
			if (resultado.isValid()) {
				// Cria um ambiente e grava os dados no banco.
				Ambiente ambiente = new Ambiente();
				ambiente.setNome(Nome.get());
				ambiente.setPorta(Integer.parseInt(Porta.get()));
				ambiente.setJanela(Integer.parseInt(Janela.get()));
				ambiente.setMetragem(Integer.parseInt(Metragem.get()));
				// Se tratar-se de uma atualiza��o.
				if(Edicao.get()) {
					ambiente.setId(id);
					AmbienteDao.atualizar(ambiente);
					// Exibe uma mensagem de grava��o realizada com sucesso.
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
					// Exibe uma mensagem de grava��o realizada com sucesso.
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
			// Se ocorreu algum erro de valida��o, exibe uma mensagem de
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

	// Comando executado quando o bot�o "Excluir" da interface gr�fica de
	// cadastro de ambientes
	// for acionado. Este comando exclui o ambiente atual e retorna para a
	// listagem de ambientes.
	public Command Excluir = new Command() {
		public void Invoke(android.view.View visao, Object... parametros) {
			AmbienteDao.excluir(new Ambiente(id));
			// Exibe uma mensagem de exclus�o realizada com sucesso.
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

	// Comando executado quando o bot�o "Cancelar" da interface gr�fica de
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
