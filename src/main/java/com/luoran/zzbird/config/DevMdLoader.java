
package com.luoran.zzbird.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.beetl.sql.core.ClasspathLoader.SQLFileVersion;
import org.beetl.sql.core.DefaultSQLIdNameConversion;
import org.beetl.sql.core.SQLIdNameConversion;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLSource;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.kit.MDParser;

/**
 * 从classpath系统加载sql模板，id应该格式是"xx.yyy",xx代表了文件名，yyy代表了sql标识 sql 模板格式如下：
 *
 * <pre>
 selectUser 
 ===

 select * from user
 * </pre>
 * 
 * 
 * @author Administrator
 *
 */
public class DevMdLoader implements SQLLoader {

	protected String sqlRoot = null;

	protected String lineSeparator = System.getProperty("line.separator", "\n");

	protected Map<String, SQLSource> sqlSourceMap = new ConcurrentHashMap<String, SQLSource>();

	protected DBStyle dbs = null;

	protected boolean autoCheck = true;

	protected String charset = null;

	protected SQLIdNameConversion sqlIdNameConversion = new DefaultSQLIdNameConversion();

	protected SQLSource NO_EXIST = new SQLSource();

	protected ClassLoader classLoader = null;

	protected SQLManager sqlManager = null;

	public DevMdLoader() {
		this("/sql");
	}

	public DevMdLoader(String root) {
		this(root, new MySqlStyle());
	}

	public DevMdLoader(String root, DBStyle dbs) {
		this.sqlRoot = root;
		this.dbs = dbs;
	}

	@Override
	public SQLSource getSQL(String id) {
		SQLSource ss = this.tryLoadSQL(id);

		return ss;
	}

	private SQLSource tryLoadSQL(String id) {
		SQLSource ss = sqlSourceMap.get(id);
		loadSql(id);

		ss = sqlSourceMap.get(id);
		if (ss == null) {
			sqlSourceMap.put(id, this.NO_EXIST);
			return null;
		} else if (ss == this.NO_EXIST) {
			return null;
		} else {
			return ss;
		}

	}

	@Override
	public boolean isModified(String id) {
		return true;

	}

	protected static Long getURLVersion(URL url) {
		if (url == null) {
			return 0l;
		}

		if (url.getProtocol().equals("file")) {
			String path = url.getFile();
			return new File(path).lastModified();
		} else {
			// 其他协议，比如jar。
			return 0l;
		}

	}

	public boolean exist(String id) {
		return this.tryLoadSQL(id) != null;
	}

	@Override
	public void addSQL(String id, SQLSource source) {
		sqlSourceMap.put(id, source);

	}

	/***
	 * 考虑到跨数据库支持，ClasspathLoader加载SQL顺序如下： 首先根据DBStyle.getName()
	 * 找到对应的数据库名称，然后在ROOT/dbName 下找对应的sql， 如果ROOT/dbName
	 * 文件目录不存在，或者相应的sql文件不存在，再搜索ROOT目录下的sql文件。 如mysql 里查找user.select2,顺序如下： -
	 * 先找ROOT/mysql/user.sql 文件，如果有此文件，且包含了select2，则返回此sql语句， -
	 * 如果没有，下一步查找ROOT/mysql/user.md,如果有此文件，且包含了slect2，则返回sql语句 -
	 * 如果没有，下一步查找ROOT/user.sql,如果有此文件，且包含了slect2，则返回sql语句 -
	 * 如果没有，下一步查找ROOT/user.md,如果有此文件，且包含了slect2，则返回sql语句 -
	 * 都没有，抛错，告诉用户未在ROOT/,或者ROOT/mysql 下找到相关sql
	 * 
	 * @return
	 */
	private boolean loadSql(String id) {
		// 读取root目录下的sql文件
		URL ins = this.getRootFile(id);
		boolean rootResult;
		rootResult = readSqlFile(id, ins, true);
		if (rootResult) {
			return true;
		} else {

			return false;
		}
	}

	private boolean readSqlFile(String id, URL url, boolean isRoot) {
		if (url == null) {
			return false;
		}
		InputStream ins;
		try {
			ins = url.openStream();
		} catch (IOException e1) {
			return false;
		}
		String modelName = id.substring(0, id.lastIndexOf(".") + 1);
		if (ins == null)
			return false;
		String filePath = sqlIdNameConversion.getPath(id);

		long lastModified = getURLVersion(url);

		LinkedList<String> list = new LinkedList<String>();
		BufferedReader bf = null;
		try {

			bf = new BufferedReader(new InputStreamReader(ins, charset));
			MDParser parser = new MDParser(modelName, bf);
			SQLSource source = null;
			while ((source = parser.next()) != null) {
				SQLFileVersion version = new SQLFileVersion();
				version.url = url;
				if (isRoot) {
					version.root = System.currentTimeMillis();
				} else {
					version.db = System.currentTimeMillis();
				}
				source.setVersion(version);
				sqlSourceMap.put(source.getId(), source);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	public Map<String, SQLSource> getSqlSourceMap() {
		return sqlSourceMap;
	}

	public String getSqlRoot() {
		return sqlRoot;
	}

	public void setSqlRoot(String sqlRoot) {
		this.sqlRoot = sqlRoot;
	}

	/***
	 * 获取.md文件 md文件需放在classpath下
	 * 
	 * @param id
	 * @return
	 * @throws UnexpectedException
	 */
	private URL getRootFile(String id) {
		String path = this.sqlIdNameConversion.getPath(id);
		String filePath0 = sqlRoot + "/" + path + ".sql";
		String filePath1 = sqlRoot + "/" + path + ".md";
		File f0 = new File(System.getProperty("user.dir") + "/" + filePath0);
		File f1 = new File(System.getProperty("user.dir") + "/" + filePath1);
		if (f0.exists()) {
			try {
				return f0.toURI().toURL();
			} catch (MalformedURLException e) {
				return null;
			}
		} else if (f1.exists()) {
			try {
				return f1.toURI().toURL();
			} catch (MalformedURLException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	private URL getDBRootFile(String id) {
		String path = this.sqlIdNameConversion.getPath(id);
		String filePath0 = sqlRoot + "/" + dbs.getName() + "/" + path + ".sql";
		String filePath1 = sqlRoot + "/" + dbs.getName() + "/" + path + ".md";
		URL is = this.getFile(filePath0);
		if (is == null) {
			is = this.getFile(filePath1);
			if (is == null) {
				return null;
			}
		}
		return is;
	}

	public boolean exsitResource(String id) {
		if (getRootFile(id) == null && getDBRootFile(id) == null) {
			return false;
		} else {
			return true;
		}
	}

	private URL getFile(String filePath) {
		ClassLoader loader = null;
		URL url = null;
		if (classLoader != null) {
			url = classLoader.getResource(filePath);
			if (url != null) {
				return url;
			}
		}

		loader = Thread.currentThread().getContextClassLoader();
		// InputStream is = null;
		if (loader != null) {
			url = loader.getResource(filePath);
			if (url != null) {
				return url;
			} else {
				url = this.getClass().getResource(filePath);
				return url;
			}
		} else {
			url = this.getClass().getResource(filePath);
			return url;
		}

	}

	@Override
	public boolean isAutoCheck() {
		return false;
	}

	@Override
	public void setAutoCheck(boolean check) {
		this.autoCheck = check;

	}

	public DBStyle getDbs() {
		return dbs;
	}

	public void setDbs(DBStyle dbs) {
		this.dbs = dbs;
	}

	@Override
	public String getCharset() {

		return charset;
	}

	@Override
	public void setCharset(String charset) {
		this.charset = charset;

	}

	public String toString() {
		return this.sqlRoot;
	}

	@Override
	public void setSQLIdNameConversion(SQLIdNameConversion sqlIdNc) {
		this.sqlIdNameConversion = sqlIdNc;

	}

	@Override
	public void setDbStyle(DBStyle dbStyle) {
		this.dbs = dbStyle;
	}

	public void setSQLManager(SQLManager sqlManager) {
		this.sqlManager = sqlManager;
	}

	public SQLManager getSQLManager() {
		return this.sqlManager;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public void refresh() {

		sqlSourceMap = new ConcurrentHashMap<String, SQLSource>();
	}

}
