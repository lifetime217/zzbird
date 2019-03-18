package com.luoran.zzbird.core;

/**
 * @author lifetime
 *
 */
public class UserContext {
	private static String CtxPath;
	private static ThreadLocal<UserContextInfo> local = new ThreadLocal<UserContextInfo>();

	public static void init(UserContextInfo contextInfo) {
		local.set(contextInfo);
	}

	public static void clear() {
		local.remove();
	}

	public static UserContextInfo get() {
		return local.get();
	}

	public static String getCtxPath() {
		return CtxPath;
	}

	public static void setCtxPath(String ctxPath) {
		CtxPath = ctxPath;
	}

}
