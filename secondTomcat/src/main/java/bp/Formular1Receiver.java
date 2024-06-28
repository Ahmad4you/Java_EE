package bp;

import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequestWrapper;




public class Formular1Receiver {
	public int val =0;
	
	public static Formular1Receiver f1r = null;
	
	protected Formular1Receiver() {
		
	}
	
	public static Formular1Receiver getInstance() {
		if ( f1r == null) {
			f1r = new Formular1Receiver();
		}
		return f1r;
		
	}
	
	public String getCheckStatus(int value) {
		if ( val % 3 == value) {
			return "checked";

		}
		return "";
		
	}
	
	
	public void getData(HttpServletRequestWrapper req) {
		if ( req == null) {
			System.out.println("No Request!");
			return;
		}
		StringBuffer sb = new StringBuffer();
		Enumeration<String> parameterList = req.getParameterNames();
		  while( parameterList.hasMoreElements() )
		  {
		    String   sName     = parameterList.nextElement().toString();
		    String[] sMultiple = req.getParameterValues( sName );
		    if( 1 >= sMultiple.length )
		      sb.append( sName +": " + req.getParameter( sName ));
		    else
		      for( int i=0; i<sMultiple.length; i++ ) {
		        sb.append( sName + "[" + i + "]: " + sMultiple[i]  );
		      }
		    sb.append("\n");
		  }
		  System.out.println(sb.toString());
	}
	public String getText() {
		return "Ralf" + val ++;
	}
}