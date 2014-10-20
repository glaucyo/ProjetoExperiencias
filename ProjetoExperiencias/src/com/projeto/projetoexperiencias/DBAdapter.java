package com.projeto.projetoexperiencias;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	/******************** if debug is set true then it will show all Logcat message ************/
	public static final boolean DEBUG = true;
	
	/******************** Logcat TAG ************/
	public static final String LOG_TAG = "DBAdapter";
	
	/******************** Table Fields ************/
	public static final String KEY_ID = "user_id";
	public static final String KEY_USER_NAME = "user_name";
	public static final String KEY_USER_EMAIL = "user_email";
	public static final String KEY_USER_LOGIN = "user_login";
	public static final String KEY_USER_PASSWORD = "user_password";
	public static final String KEY_USER_PHONE_NUMBER = "user_phone_number";
	public static final String KEY_USER_FASE_CADASTRO = "fase_cadastro";
	
	public static final String KEY_HORARIOS_ID = "horarios_id";
	public static final String KEY_HORARIOS_TIPO_ENTRADA = "horarios_tipo_entrada";//1-entrada manhã,2-saida manhã,3-entrada tarde,4-saída tarde
	public static final String KEY_HORARIOS_HORARIO = "horarios_horario";
	public static final String KEY_HORARIOS_USUARIO = "horarios_usuario";
	
	
	
	/******************** Database Name ************/
	public static final String DATABASE_NAME = "DB_BANCO_DE_HORAS";
	
	/******************** Database Version (Increase one if want to also upgrade your database) ************/
	public static final int DATABASE_VERSION = 1;// started at 1

	/** Table names */
	public static final String USER_TABLE = "tbl_user";
	public static final String HORARIOS_TABLE = "tbl_horarios";
	/******************** Set all table with comma seperated like USER_TABLE,ABC_TABLE ************/
	private static final String[] ALL_TABLES = { USER_TABLE,HORARIOS_TABLE };
	
	/** Create table syntax */
	private static final String USER_CREATE = "create table tbl_user(user_id integer primary key autoincrement, user_name text not null, user_email text not null, user_login text not null, user_password text not null, user_phone_number text not null, fase_cadastro text);";
	private static final String HORARIOS_CREATE = "create table tbl_horarios(horarios_id integer primary key autoincrement, horarios_tipo_entrada text not null, horarios_horario text not null, horarios_usuario integer not null);";
	/******************** Used to open database in syncronized way ************/
	private static DataBaseHelper DBHelper = null;

	protected DBAdapter() {
	}
    /******************* Initialize database *************/
	public static void init(Context context) {
		if (DBHelper == null) {
			if (DEBUG)
				Log.i("DBAdapter", context.toString());
			DBHelper = new DataBaseHelper(context);
		}
	}
	
  /********************** Main Database creation INNER class ********************/
	private static class DataBaseHelper extends SQLiteOpenHelper {
		public DataBaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			if (DEBUG)
				Log.i(LOG_TAG, "new create");
			try {
				db.execSQL(USER_CREATE);
				db.execSQL(HORARIOS_CREATE);
				

			} catch (Exception exception) {
				if (DEBUG)
					Log.i(LOG_TAG, "Exception onCreate() exception");
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			if (DEBUG)
				Log.w(LOG_TAG, "Upgrading database from version" + oldVersion
						+ "to" + newVersion + "...");

			for (String table : ALL_TABLES) {
				db.execSQL("DROP TABLE IF EXISTS " + table);
			}
			onCreate(db);
		}

	} // Inner class closed
	
	
	/********************** Open database for insert,update,delete in syncronized manner ********************/
	private static synchronized SQLiteDatabase open() throws SQLException {
		return DBHelper.getWritableDatabase();
	}


	/************************ General functions**************************/
	
	
	/*********************** Escape string for single quotes (Insert,Update)************/
	private static String sqlEscapeString(String aString) {
		String aReturn = "";
		
		if (null != aString) {
			//aReturn = aString.replace("'", "''");
			aReturn = DatabaseUtils.sqlEscapeString(aString);
			// Remove the enclosing single quotes ...
			aReturn = aReturn.substring(1, aReturn.length() - 1);
		}
		
		return aReturn;
	}
	/*********************** UnEscape string for single quotes (show data)************/
	@SuppressWarnings("unused")
	private static String sqlUnEscapeString(String aString) {
		
		String aReturn = "";
		
		if (null != aString) {
			aReturn = aString.replace("''", "'");
		}
		
		return aReturn;
	}
	
	
	/********************************************************************/
	
	
	 /**
     * All Operations (Create, Read, Update, Delete) 
     */
	
	
	
	/**
	 * Cadastra horário (bate o ponto)
	 * @param ponto
	 */
	public static void addHorario(PontoVo ponto) {
    	final SQLiteDatabase db = open();
		
		ContentValues cVal = new ContentValues();
		cVal.put(KEY_HORARIOS_TIPO_ENTRADA, ponto.getTipoEntrada());
		cVal.put(KEY_HORARIOS_HORARIO, ponto.getHorario());
		cVal.put(KEY_HORARIOS_USUARIO, "1");
		db.insert(HORARIOS_TABLE, null, cVal);
		
        db.close(); // Closing database connection
    }
	
	/**
	 * Lista todos os horários disponíveis
	 * @return
	 */
    public static List<PontoVo> getAllHorariosData() {
        List<PontoVo> pontosList = new ArrayList<PontoVo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + HORARIOS_TABLE;
        String dataCorrente = "";
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
        	do {
        		PontoVo ponto = new PontoVo();
        		
        		String tipoEntrada = cursor.getString(1);
        		String horarioRegistrado = cursor.getString(2);
        		horarioRegistrado = horarioRegistrado.substring(11, 19);
        		
        		ponto.setHorario(horarioRegistrado);
        		ponto.setTipoEntrada(tipoEntrada);
            	
        		pontosList.add(ponto);
            } while (cursor.moveToNext());
        }
        // return pontos list
        return pontosList;
    }
	
    // Adding new horarios
    public static void addTodosHorarios(HorariosDiaVo horario) {
    	final SQLiteDatabase db = open();
    	
		
		ContentValues cVal = new ContentValues();
		cVal.put(KEY_HORARIOS_TIPO_ENTRADA, "1");
		cVal.put(KEY_HORARIOS_HORARIO, horario.getEntradaManha());
		cVal.put(KEY_HORARIOS_USUARIO, "1");
		db.insert(HORARIOS_TABLE, null, cVal);
		
		cVal = new ContentValues();
		cVal.put(KEY_HORARIOS_TIPO_ENTRADA, "2");
		cVal.put(KEY_HORARIOS_HORARIO, horario.getSaidaManha());
		cVal.put(KEY_HORARIOS_USUARIO, "1");
		db.insert(HORARIOS_TABLE, null, cVal);
		
		cVal = new ContentValues();
		cVal.put(KEY_HORARIOS_TIPO_ENTRADA, "3");
		cVal.put(KEY_HORARIOS_HORARIO, horario.getEntradaTarde());
		cVal.put(KEY_HORARIOS_USUARIO, "1");
		db.insert(HORARIOS_TABLE, null, cVal);
		
		cVal = new ContentValues();
		cVal.put(KEY_HORARIOS_TIPO_ENTRADA, "4");
		cVal.put(KEY_HORARIOS_HORARIO, horario.getSaidaTarde());
		cVal.put(KEY_HORARIOS_USUARIO, "1");
		db.insert(HORARIOS_TABLE, null, cVal);

		
        db.close(); // Closing database connection
    }
    
    public static void limpaBase() {
    	Log.i("DBAdapter", "Limpando a base de dados");
    	final SQLiteDatabase db = open();
        db.delete(HORARIOS_TABLE, null, null);
        db.close();
        Log.i("DBAdapter", "Horários deletados");
	}
   /* public static void addUserData(UserData uData) {
    	final SQLiteDatabase db = open();
    	
    	String name = sqlEscapeString(uData.getName());
		String email = sqlEscapeString(uData.getEmail());
		String login = sqlEscapeString(uData.getLogin());
		String pin = sqlEscapeString(uData.getPin());
		String phoneNumber = sqlEscapeString(uData.getPhoneNumber());
		String faseCadastro = sqlEscapeString(uData.getFaseCadastro());
		ContentValues cVal = new ContentValues();
		cVal.put(KEY_USER_NAME, name);
		cVal.put(KEY_USER_EMAIL, email);
		cVal.put(KEY_USER_LOGIN, login);
		cVal.put(KEY_USER_PIN, pin);
		cVal.put(KEY_USER_PHONE_NUMBER, phoneNumber);
		cVal.put(KEY_USER_FASE_CADASTRO, faseCadastro);
		db.insert(USER_TABLE, null, cVal);
        db.close(); // Closing database connection
    }
    
	// Adding new contact
    
    public static void addParam(ParamData uData) {
    	final SQLiteDatabase db = open();
    	
    	String name = sqlEscapeString(uData.get_name());
		String desc = sqlEscapeString(uData.get_desc());
		int idUser = uData.get_idUser();
		String idClient = sqlEscapeString(uData.get_idClient());
		String group = sqlEscapeString(uData.get_group());
		String alias = sqlEscapeString(uData.get_alias());
		String label = sqlEscapeString(uData.get_label());
		
		ContentValues cVal = new ContentValues();
		cVal.put(KEY_PARAM_NAME, name);
		cVal.put(KEY_PARAM_DESC, desc);
		cVal.put(KEY_PARAM_IDUSER, idUser);
		cVal.put(KEY_PARAM_CLIENT_ID, idClient);
		cVal.put(KEY_PARAM_GROUP, group);
		cVal.put(KEY_PARAM_ALIAS, alias);
		cVal.put(KEY_PARAM_LABEL, label);
		db.insert(PARAM_TABLE, null, cVal);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public static UserData getUserData(int id) {
    	final SQLiteDatabase db = open();
 
        Cursor cursor = db.query(USER_TABLE, new String[] { KEY_ID,
        		KEY_USER_NAME, KEY_USER_EMAIL, KEY_USER_LOGIN, KEY_USER_PIN, KEY_USER_PHONE_NUMBER, KEY_USER_FASE_CADASTRO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        UserData data = new UserData(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        // return contact
        return data;
    }
    
    public static UserData getUserData2(int idUser) {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + USER_TABLE + " WHERE "+KEY_ID +" = ?";
        UserData data = null;
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(idUser)});
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	data = new UserData();
            	data.setID(Integer.parseInt(cursor.getString(0)));
            	data.setName(cursor.getString(1));
            	data.setEmail(cursor.getString(2));
            	data.setLogin(cursor.getString(3));
            	data.setPin(cursor.getString(4));
            	data.setPhoneNumber(cursor.getString(5));
            	data.setFaseCadastro(cursor.getString(6));
                // Adding contact to list
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return data;
    }
    
    // Getting All Params
    public static List<ParamData> getAllParamData() {
        List<ParamData> contactList = new ArrayList<ParamData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PARAM_TABLE;
 
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	ParamData data = new ParamData();
            	data.set_id(Integer.parseInt(cursor.getString(0)));
            	data.set_name(cursor.getString(1));
            	data.set_desc(cursor.getString(2));
            	data.set_idUser(Integer.parseInt(cursor.getString(3)));
            	data.set_idClient(cursor.getString(4));
            	data.set_group(cursor.getString(5));
            	data.set_alias(cursor.getString(6));
            	data.set_label(cursor.getString(7));
                // Adding contact to list
                contactList.add(data);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    
    
    // Getting All Params
    public static List<ParamData> getParamDataUser(int idUser) {
        List<ParamData> contactList = new ArrayList<ParamData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PARAM_TABLE + " WHERE "+KEY_PARAM_IDUSER +" = ?";
 
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(idUser)});
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	ParamData data = new ParamData();
            	data.set_id(Integer.parseInt(cursor.getString(0)));
            	data.set_name(cursor.getString(1));
            	data.set_desc(cursor.getString(2));
            	data.set_idUser(Integer.parseInt(cursor.getString(3)));
            	data.set_idClient(cursor.getString(4));
            	data.set_group(cursor.getString(5));
            	data.set_alias(cursor.getString(6));
            	data.set_label(cursor.getString(7));
                // Adding contact to list
                contactList.add(data);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    
    // Getting All Params
    public static List<ParamData> getParamDataClient(String idClient) {
        List<ParamData> contactList = new ArrayList<ParamData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PARAM_TABLE + " WHERE "+KEY_PARAM_CLIENT_ID +" = ?";
 
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(idClient)});
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	ParamData data = new ParamData();
            	data.set_id(Integer.parseInt(cursor.getString(0)));
            	data.set_name(cursor.getString(1));
            	data.set_desc(cursor.getString(2));
            	data.set_idUser(Integer.parseInt(cursor.getString(3)));
            	data.set_idClient(cursor.getString(4));
            	data.set_group(cursor.getString(5));
            	data.set_alias(cursor.getString(6));
            	data.set_label(cursor.getString(7));
                // Adding contact to list
                contactList.add(data);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    
    // Getting Params by Group
    public static List<ParamData> getParamDataGroup(String group) {
        List<ParamData> contactList = new ArrayList<ParamData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PARAM_TABLE + " WHERE "+KEY_PARAM_GROUP +" = ?";
 
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(group)});
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	ParamData data = new ParamData();
            	data.set_id(Integer.parseInt(cursor.getString(0)));
            	data.set_name(cursor.getString(1));
            	data.set_desc(cursor.getString(2));
            	data.set_idUser(Integer.parseInt(cursor.getString(3)));
            	data.set_idClient(cursor.getString(4));
            	data.set_group(cursor.getString(5));
            	data.set_alias(cursor.getString(6));
            	data.set_label(cursor.getString(7));
                // Adding contact to list
                contactList.add(data);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    
    // Getting Params by Group
    public static List<ParamData> getParamDataAlias(String alias) {
        List<ParamData> contactList = new ArrayList<ParamData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + PARAM_TABLE + " WHERE "+KEY_PARAM_ALIAS +" = ?";
 
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(alias)});
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	ParamData data = new ParamData();
            	data.set_id(Integer.parseInt(cursor.getString(0)));
            	data.set_name(cursor.getString(1));
            	data.set_desc(cursor.getString(2));
            	data.set_idUser(Integer.parseInt(cursor.getString(3)));
            	data.set_idClient(cursor.getString(4));
            	data.set_group(cursor.getString(5));
            	data.set_alias(cursor.getString(6));
            	data.set_label(cursor.getString(7));
                // Adding contact to list
                contactList.add(data);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
 
    // Getting All Contacts
    public static List<UserData> getAllUserData() {
        List<UserData> contactList = new ArrayList<UserData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + USER_TABLE;
 
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	UserData data = new UserData();
            	data.setID(Integer.parseInt(cursor.getString(0)));
            	data.setName(cursor.getString(1));
            	data.setEmail(cursor.getString(2));
            	data.setLogin(cursor.getString(3));
            	data.setPin(cursor.getString(4));
            	data.setPhoneNumber(cursor.getString(5));
            	data.setFaseCadastro(cursor.getString(6));
                // Adding contact to list
                contactList.add(data);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
    
    // Updating single param
    public static void updateParamDataUser(ParamData data) {
    	final SQLiteDatabase db = open();
    	ContentValues cv = new ContentValues();
    	cv.put("param_desc", data.get_desc());
    	cv.put("param_alias", data.get_alias());
    	db.update(PARAM_TABLE, cv, KEY_PARAM_ID + " = "+data.get_id(), null);
    }
 
    // Updating fase cadastro user
    public static void updateUserFaseCadastro(String userId, String faseCadastro) {
    	final SQLiteDatabase db = open();
    	ContentValues cv = new ContentValues();
    	cv.put("fase_cadastro", faseCadastro);
    	db.update(USER_TABLE, cv, KEY_ID + " = " + userId, null);
    }
    // Updating single contact
    public static int updateUserData(UserData data) {
    	final SQLiteDatabase db = open();
 
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, data.getName());
        values.put(KEY_USER_EMAIL, data.getEmail());
        values.put(KEY_USER_LOGIN, data.getLogin());
        values.put(KEY_USER_PIN, data.getPin());
        values.put(KEY_USER_PHONE_NUMBER, data.getPhoneNumber());
        // updating row
        return db.update(USER_TABLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getID()) });
    }
    
    public static int updateUserPIN(UserData data) {
    	final SQLiteDatabase db = open();
 
        ContentValues values = new ContentValues();
        values.put(KEY_USER_PIN, data.getPin());
        // updating row
        return db.update(USER_TABLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getID()) });
    }
 
    public static int updateEmailUser() {
    	final SQLiteDatabase db = open();
 
        ContentValues values = new ContentValues();
        values.put(KEY_USER_LOGIN, "glaucyo.leitao@cmsw.com");
        values.put(KEY_USER_EMAIL, "22bb");
        // updating row
        return db.update(USER_TABLE, values, null, null);
    }
    
    // Deleting single contact
    public static void deleteUserData(UserData data) {
    	final SQLiteDatabase db = open();
        db.delete(USER_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getID()) });
        db.close();
    }
    
    
    public static void deleteParametros() {
    	Log.i("DBAdapter", "Deletando parametros");
    	final SQLiteDatabase db = open();
        db.delete(PARAM_TABLE, null, null);
        db.close();
        Log.i("DBAdapter", "Parametros deletados");
	}
 
    // Getting contacts Count
    public static int getUserDataCount() {
        String countQuery = "SELECT  * FROM " + USER_TABLE;
        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }*/
}
