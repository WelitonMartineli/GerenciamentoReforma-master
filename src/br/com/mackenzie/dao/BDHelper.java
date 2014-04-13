package br.com.mackenzie.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.mackenzie.upload.modelo.Contact;

/**
 * Esta classe gerencia o banco de dados da aplicação.
 * 
 * @author Leandro Luque, Érico Veriscimo, Girdácio Pereira
 * @version 1.0
 * @since 1.0
 */
public class BDHelper extends SQLiteOpenHelper {

	// Instância desta classe.
	private static BDHelper instancia;
	// Referência para o banco de dados.
	private SQLiteDatabase banco;

	// *****************************************************
	// Nome do banco de dados.
	private static final String NOME_BANCO = "imovel.bd";
	// Versão do banco.
	private static final int VERSAO = 5;

	// *****************************************************
	// Definições da tabela de ambientes.
	public static final String AmbienteTabela = "ambiente";
	public static final String ServicoAmbienteTabela = "servico_ambiente";
	private static final String TABLE_CONTACTS = "contacts";
	

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
	
	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_IMAGE = "image";	
	
	private static final String AmbienteSQLCriacao = "CREATE TABLE "
			+ AmbienteTabela + " (" + AmbienteColunas.ID.nome()
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ AmbienteColunas.NOME.nome() + " TEXT NOT NULL, "
			+ AmbienteColunas.PORTA.nome() + " INTEGER, "
			+ AmbienteColunas.JANELA.nome() + " INTEGER, "
			+ AmbienteColunas.METRAGEM.nome() + " INTEGER NOT NULL);";
	
	private static final String ServicoAmbienteSQLCriacao = "CREATE TABLE "
			+ ServicoAmbienteTabela + " (" + ServicoAmbienteColunas.ID.nome()
			+ " INTEGER NOT NULL, " //PRIMARY KEY AUTOINCREMENT, "
			+ ServicoAmbienteColunas.ID_AMBIENTE.nome() + " INTEGER NOT NULL, "
			+ ServicoAmbienteColunas.COD_SERVICO.nome() + " TEXT NOT NULL, "
			+ ServicoAmbienteColunas.DES_SERVICO.nome() + " TEXT NOT NULL);";
			//+ " FOREIGN KEY(id_ambiente) REFERENCES ambiente(id) " +
			//		");";
	
	private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
			+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
			+ KEY_IMAGE + " BLOB" + ")";
	
	/**
	 * Cria um novo objeto da classe para o contexto de aplicação especificado
	 * por parâmetro.
	 * 
	 * @param contexto
	 *            o contexto da aplicação.
	 */
	public BDHelper(Context contexto) {
		super(contexto.getApplicationContext(), NOME_BANCO, null, VERSAO);
	}

	/**
	 * Inicializa o banco da aplicação. Deve ser executado quando a aplicação
	 * for iniciada.
	 * 
	 * @param contexto
	 *            o contexto da aplicação.
	 */
	public static void inicializar(Context contexto) {
		if (instancia == null) {
			instancia = new BDHelper(contexto);
		}
		instancia.abrir();
	}

	/**
	 * Libera o banco da aplicação. Deve ser executado quando a aplicação for
	 * finalizada.
	 */
	public static void finalizar() {
		instancia.fechar();
	}

	/**
	 * Abre o banco da aplicação.
	 */
	public void abrir() {
		this.banco = getWritableDatabase();
	}

	/**
	 * Fecha o banco da aplicação.
	 */
	public void fechar() {
		this.banco.close();
	}

	/**
	 * Retorna uma instância para esta classe. Deve ser executado nos DAOs.
	 * 
	 * @return uma instância desta classe.
	 */
	public static BDHelper getInstance() {
		return instancia;
	}

	/**
	 * Retorna uma referência para o banco de dados da aplicação.
	 * 
	 * @return um referência para o banco.
	 */
	public SQLiteDatabase getBanco() {
		return this.banco;
	}

	@Override
	public void onCreate(SQLiteDatabase banco) { // Cria o banco, caso ele não exista.
		// Cria a tabela de ambientes.
		banco.execSQL(AmbienteSQLCriacao);
		banco.execSQL(ServicoAmbienteSQLCriacao);
		banco.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase banco, int versaoAnterior,
			int novaVersao) { // Atualiza o banco, caso a versão dele tenha sido alterada.
		banco.execSQL("DROP TABLE IF EXISTS " + AmbienteTabela);
		banco.execSQL("DROP TABLE IF EXISTS " + ServicoAmbienteTabela);
		banco.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		onCreate(banco);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	public// Adding new contact
	void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName()); // Contact Name
		values.put(KEY_IMAGE, contact.getImage()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	Contact getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
				KEY_NAME, KEY_IMAGE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getBlob(1));

		// return contact
		return contact;

	}

	// Getting All Contacts
	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM contacts ORDER BY name";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setImage(cursor.getBlob(2));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		// close inserting data from database
		db.close();
		// return contact list
		return contactList;

	}

	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_IMAGE, contact.getImage());

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });

	}

	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}

	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	
	
}
