package format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateFormatLayoutSource {

	static String[] tcnameArray = new String[] { 
			"KEC1B200", "KEC1B201", "KEC1B202","KEC1B203", "KEC1B213", "KEC1B216", "KEC1B217",
			"KEC1B222", "KEC1B224", "KEC1B228", "KEC1B229", "KEC1B233", "KEC1B234", "KEC1B236",
			"KEC1B243", "KEC1B244", "KEC1B245", "KEC1B246", "KEC1B247", "KEC1B248",	"KEC1B249", 
			"KEC1B250", "KEC1B253", "KEC1B254", "KEC1B256",	"KEC1B260", "KEC1B195", "KEC1B196",
			"KEC1B272", "KEC1B274", "KEC1B277", "KEC1B280", "KEC1B282", "KEC1B285",	"KEC1B286",
			"KEC1B287", "KEC1B294", "KEC1B296", "KEC1B297", "KEC1B301", "KEC1B302", "KEC1B304", 
			"KEC1B305",	"KEC1B306", "KEC1B307", "KEC1B308", "KEC1B313", "KEC2B230"

	};

	ArrayList<FormatLayout> al = new ArrayList<FormatLayout>();

	HashMap<String, ArrayList<FormatLayout>> tcHash = new HashMap<String, ArrayList<FormatLayout>>();

	public void makeData() {

		// make sampledata;

		al = Data1.getSampleData(al);
		al = Data2.getSampleData(al);
		al = Data3.getSampleData(al);
		al = Data4.getSampleData(al);

		// hashmap 에 담기위해 분리한다.
		for (int i = 0; i < tcnameArray.length; i++) {

			String ttcname = tcnameArray[i];

			Stream<FormatLayout> s = al.stream().filter(x -> x.getTcname().equals(ttcname));

			tcHash.put(ttcname, (ArrayList<FormatLayout>) s.collect(Collectors.toList()));
		}

	}

	public GenerateFormatLayoutSource(String ROOT_DIR, String TCNAME) {
		// TODO Auto-generated constructor stub

		makeData();

		System.out.println(ROOT_DIR);

		File entityPath = new File(ROOT_DIR + "/" + TCNAME + "-msg.xml");

		StringBuffer sb = new StringBuffer("");

		ArrayList<FormatLayout> tmp = (ArrayList<FormatLayout>) tcHash.get(TCNAME);

		sb.append("<?xml version='1.0' encoding='UTF-8'?>\n");
		sb.append("<msgs>\n");
		sb.append("<msg id='" + TCNAME + "' name='" + TCNAME + "'>\n");

		for (FormatLayout formatLayout : tmp) {
			sb.append(formatLayout.toString()).append("\n");
		}

		sb.append("</msg>\n");
		sb.append("</msgs>\n");

		System.out.println(sb.toString().replaceAll("'", "\""));
		writeFile(entityPath, sb.toString().replaceAll("'", "\""));

		sb.setLength(0);

	}

	public void writeFile(File entityPath, String s) {

		try {
			System.out.println(entityPath.toPath());
			FileWriter f = new FileWriter(entityPath);

			f.write(s);

			f.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	

	public static void main(String args[]) {

		System.out.println("you have to put three paramter ");
		System.out.println("ROOT_DIR : D:/git/m2ae01_lime_calcine/");
		System.out.println("TCNAME : KEC1B200 OR ALL");
		System.out.println("example\n\n");
		System.out.println("run.bat D:/PHAPPS/CEF010APP/src/layout KEC2B230");

		
//		new GenerateFormatLayoutSource("D:/PHAPPS/CEF010APP/src/layout", "KEC1B203");
//		new GenerateFormatLayoutSource("D:/PHAPPS/CEF010APP/src/layout", "KEC1B286");
//		new GenerateFormatLayoutSource("D:/PHAPPS/CEF010APP/src/layout", "KEC1B195");
		new GenerateFormatLayoutSource("D:/PHAPPS/CEF010APP/src/layout", "KEC1B301");
		
		
//		for (int i = 0; i < tcnameArray.length; i++) {
//			new GenerateFormatLayoutSource("D:/PHAPPS/CEF010APP/src/layout", tcnameArray[i]);
//		}

	}
}
