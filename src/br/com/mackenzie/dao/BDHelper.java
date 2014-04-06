package br.com.mackenzie.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Esta classe gerencia o banco de dados da aplica��o.
 * 
 * @author Leandro Luque, �rico Veriscimo, Gird�cio Pereira
 * @version 1.0
 * @since 1.0
 */
public class BDHelper extends SQLiteOpenHelper {

	// Inst�ncia desta classe.
	private static BDHelper instancia;
	// Refer�ncia para o banco de dados.
	private SQLiteDatabase banco;

	// *****************************************************
	// Nome do banco de dados.
	private static final String NOME_BANCO = "imovel.bd";
	// Vers�o do banco.
	private static final int VERSAO = 2;

	// *****************************************************
	// Defini��es da tabela de ambientes.
	public static final String AmbienteTabela = "ambiente";
	public static final String ServicoAmbienteTabela = "servico_ambiente";

	public static enum AmbienteColunas {
		ID("id"), NOME("nome"), PORTA("porta"), JANELA("janela"), METRAGEM("metragem");
		private final String nome;

		AmbienteColunas(String nome) {
			this.nome = nome;
		}

		public String nome() {
			return this.nome;
		}
	}

	public static enum ServicoAmbienteColunas {
		ID("id"), ID_AMBIENTE("id_ambiente"), COD_SERVICO("cod_servico"), DES_SERVICO("des_servico");
		private final String nome;

		ServicoAmbienteColunas(String nome) {
			this.nome = nome;
		}

		public String nome() {
			return this.nome;
		}
	}	
	
	
	private static final String AmbienteSQLCriacao = "CREATE TABLE "
			+ AmbienteTabela + " (" + AmbienteColunas.ID.nome()
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ AmbienteColunas.NOME.nome() + " TEXT NOT NULL, "
			+ AmbienteColunas.PORTA.nome() + " INTEGER, "
			+ AmbienteColunas.JANELA.nome() + " INTEGER, "
			+ AmbienteColunas.METRAGEM.nome() + " INTEGER NOT NULL);";
	
	private static final String ServicoAmbienteSQLCriacao = "CREATE TABLE "
			+ ServicoAmbienteTabela + " (" + ServicoAmbienteColunas.ID.nome()
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ ServicoAmbienteColunas.ID_AMBIENTE.nome() + " INTEGER NOT NULL, "
			+ ServicoAmbienteColunas.COD_SERVICO.nome() + " TEXT NOT NULL, "
			+ ServicoAmbienteColunas.DES_SERVICO.nome() + " TEXT NOT NULL"
			+ " FOREIGN KEY(id_ambiente) REFERENCES ambiente(id) " +
					");";
	

	/**
	 * Cria um novo objeto da classe para o contexto de aplica��o especificado
	 * por par�metro.
	 * 
	 * @param contexto
	 *            o contexto da aplica��o.
	 */
	public BDHelper(Context contexto) {
		super(contexto.getApplicationContext(), NOME_BANCO, null, VERSAO);
	}

	/**
	 * Inicializa o banco da aplica��o. Deve ser executado quando a aplica��o
	 * for iniciada.
	 * 
	 * @param contexto
	 *            o contexto da aplica��o.
	 */
	public static void inicializar(Context contexto) {
		if (instancia == null) {
			instancia = new BDHelper(contexto);
		}
		instancia.abrir();
	}

	/**
	 * Libera o banco da aplica��o. Deve ser executado quando a aplica��o for
	 * finalizada.
	 */
	public static void finalizar() {
		instancia.fechar();
	}

	/**
	 * Abre o banco da aplica��o.
	 */
	public void abrir() {
		this.banco = getWritableDatabase();
	}

	/**
	 * Fecha o banco da aplica��o.
	 */
	public void fechar() {
		this.banco.close();
	}

	/**
	 * Retorna uma inst�ncia para esta classe. Deve ser executado nos DAOs.
	 * 
	 * @return uma inst�ncia desta classe.
	 */
	public static BDHelper getInstance() {
		return instancia;
	}

	/**
	 * Retorna uma refer�ncia para o banco de dados da aplica��o.
	 * 
	 * @return um refer�ncia para o banco.
	 */
	public SQLiteDatabase getBanco() {
		return this.banco;
	}

	@Override
	public void onCreate(SQLiteDatabase banco) { // Cria o banco, caso ele n�o exista.
		// Cria a tabela de ambientes.
		banco.execSQL(AmbienteSQLCriacao);
		banco.execSQL(ServicoAmbienteSQLCriacao);
	}

	@Override
	public void onUpgrade(SQLiteDatabase banco, int versaoAnterior,
			int novaVersao) { // Atualiza o banco, caso a vers�o dele tenha sido alterada.
		banco.execSQL("DROP TABLE IF EXISTS " + AmbienteTabela);
		banco.execSQL("DROP TABLE IF EXISTS " + ServicoAmbienteTabela);
		onCreate(banco);
	}

}
