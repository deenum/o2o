package deenum.odata.ogee2olingo.test;

import javax.swing.JOptionPane;


public class DEBUG {
	public static String e(Exception e) {
		StringBuilder debug = new StringBuilder();
		debug.append(e.getMessage());
		debug.append("\n");
		for(StackTraceElement el : e.getStackTrace()) {
			debug.append(el.getClassName()+" "+el.getMethodName()+" "+el.getLineNumber());
			debug.append("\n");
		}
		return debug.toString();
	}
	
	public static void log(String str) {
		System.out.println(str);
	}
	
	public static <T> T nullWarning(T obj) {
		if(obj==null) {
			//JOptionPane.showMessageDialog(null, "thank you for using java");
			DEBUG.log("Warning: Null-Return!");
		}
		return (T) obj;
	}
}
