package mhst.project.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	static final String dbName = "demoDB";
	static final String employeeTable = "Employees";
	static final String colID = "EmployeeID";
	static final String colName = "EmployeeName";
	static final String colAge = "Age";
	static final String colDept = "Dept";
	
	static final String deptTable = "Dept";
	static final String colDeptID = "DeptID";
	static final String colDeptName = "DeptName";
	
	static final String viewEmps = "ViewEmps";
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, dbName, null, 33);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + deptTable + " (" + colDeptID + " integer primary key, " + colDeptName + " text)");
		db.execSQL("create table " + employeeTable + " (" + colID + " integer primary key autoincrement, " +
		        colName + " text, " + colAge + " integer, " + colDept + " integer not null, foreign key (" + colDept + ") references " +
				deptTable + " (" + colDeptID + "));");
		db.execSQL("CREATE TRIGGER fk_empdept_deptid " +
			    " BEFORE INSERT "+
			    " ON "+employeeTable+
			    
			    " FOR EACH ROW BEGIN"+
			    " SELECT CASE WHEN ((SELECT "+colDeptID+" FROM "+deptTable+ 
			    " WHERE "+colDeptID+"=new."+colDept+" ) IS NULL)"+
			    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
			    "  END;");
		db.execSQL("CREATE VIEW "+viewEmps+
			    " AS SELECT "+employeeTable+"."+colID+" AS _id,"+
			    " "+employeeTable+"."+colName+","+
			    " "+employeeTable+"."+colAge+","+
			    " "+deptTable+"."+colDeptName+""+
			    " FROM "+employeeTable+" JOIN "+deptTable+
			    " ON "+employeeTable+"."+colDept+" ="+deptTable+"."+colDeptID
			    );
		insertDepts(db);
	}

	public void insertDepts(SQLiteDatabase db) {
		ContentValues cv = new ContentValues();
		cv.put(colDeptID, 1);
		cv.put(colDeptName, "Sales");
		db.insert(deptTable, colDeptID, cv);
		
		cv.put(colDeptID, 2);
		cv.put(colDeptName, "IT");
		db.insert(deptTable, colDeptID, cv);
		db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + employeeTable);
		db.execSQL("drop table if exists " + deptTable);
		db.execSQL("drop trigger if exists dept_id_trigger");
		db.execSQL("drop trigger if exists dept_id_trigger22");
		db.execSQL("drop trigger if exists fk_empdept_deptid");
		db.execSQL("drop view if exists " + viewEmps);
		onCreate(db);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onOpen(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			db.execSQL("pragma foreign_keys=on;");
		}
	}
	
	public int updateEmp(Employee emp) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(colName, emp.getName());
		cv.put(colAge, emp.getAge());
		cv.put(colDept, emp.getDept());
		return db.update(employeeTable, cv, colID + " = ?", new String[] {String.valueOf(emp.getId())});
	}
	
	public void deleteEmp(Employee emp) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(employeeTable, colID + " = ?", new String[] {String.valueOf(emp.getId())});
		db.close();
	}
	
	public Cursor getAllDepts() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery("select " + colDeptID + " as _id, " + colDeptName + " from " + deptTable, new String[] {});
		return cur;
	}
	
	public Cursor getEmpByDept(String dept) {
		SQLiteDatabase db = this.getWritableDatabase();
		String[] columns = new String[] {"_id", colName, colAge, colDeptName};
		Cursor c = db.query(viewEmps, columns, colDeptName + " = ?", new String[] {dept}, null, null, null);
		return c;
	}
	
	public int getDeptID(String dept) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.query(deptTable, new String[] {colDeptID + " as _id", colDeptName}, colDeptName + " = ?", new String[] {dept}, null, null, null);
		c.moveToFirst();
		return c.getInt(c.getColumnIndex("_id"));
	}
}
