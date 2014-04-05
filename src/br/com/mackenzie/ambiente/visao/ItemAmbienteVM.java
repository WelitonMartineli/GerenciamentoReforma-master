package br.com.mackenzie.ambiente.visao;

import gueei.binding.DependentObservable;
import gueei.binding.cursor.IdField;
import gueei.binding.cursor.IntegerField;
import gueei.binding.cursor.StringField;
import br.com.mackenzie.dao.BDHelper;

// Esta classe poderia ter sido definida como uma classe est�tica dentro de ListaAmbienteVM.
/**
 * Esta classe define os dados que ser�o exibidos em cada item da lista
 * de ambientes;
 * @author Leandro Luque, �rico Veriscimo, Gird�cio Pereira
 * @version 1.0
 * @since 1.0
 */
public class ItemAmbienteVM extends gueei.binding.cursor.RowModel {
	
	// Observ�veis relacionados �s colunas do banco de dados.
	public IdField Id = new IdField(BDHelper.AmbienteColunas.ID.nome());
	public StringField Nome = new StringField(BDHelper.AmbienteColunas.NOME.nome());
	public IntegerField Porta = new IntegerField(BDHelper.AmbienteColunas.PORTA.nome());
	public IntegerField Janela = new IntegerField(BDHelper.AmbienteColunas.JANELA.nome());
	public IntegerField Metragem = new IntegerField(BDHelper.AmbienteColunas.METRAGEM.nome());

	
	// Observ�vel dependente - poderia ser implementado com conversores diretamente no
	// XML do item.
/*	public DependentObservable<Boolean> AbaixoLimiteEstoque = new DependentObservable<Boolean>(Boolean.class, Quantidade) {		
		@Override
		public Boolean calculateValue(Object... observaveis) throws Exception {
			if((Integer)observaveis[0] < 15) {
				return true;
			}
			return false;
		}
	};*/

	// Observ�vel dependente - poderia ser implementado com conversores diretamente no
	// XML do item.	
//	public DependentObservable<Boolean> CuidadoNaVenda = new DependentObservable<Boolean>(Boolean.class, Perecivel, RestricaoConsumo) {		
//		@Override
//		public Boolean calculateValue(Object... observaveis) throws Exception {
//			return((Boolean)observaveis[0] || (Boolean)observaveis[1]);
//		}		
//	};
	
}
