package format;
import java.util.ArrayList;

public class FormatLayout {

	String tcname;
	String seq;
	String id;
	String name;
	String datatype;
	String length;	
	String precision;
	
	static ArrayList<FormatLayout> al=null;
	
	

	public FormatLayout(String tcname, String seq, String id, String name, String datatype,String length, String precision) {
		super();
		this.tcname = tcname;
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.datatype = datatype;
		this.length = length;		
		this.precision = precision;
		
		
		

		
	}

	
	public String getTcname() {
		return tcname;
	}


	public String getSeq() {
		return seq;
	}


	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getDatatype() {
		return datatype;
	}


	public String getPrecision() {
		return precision;
	}

	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("<attribute");
		sb.append(" type='E'");
		sb.append(" seq='").append(this.seq).append("'");
		sb.append(" id='").append(this.id).append("'");
		sb.append(" name='").append(this.name).append("'");		
		sb.append(" datatype='").append(this.datatype).append("'");
		sb.append(" length='").append(this.length).append("'");
		sb.append(" precision='").append(this.precision).append("'");
		sb.append(" />");
		
		return sb.toString();
	}
	
	

	
	
	
	
	public static void main(String[] args) {

		
	


	}
}
