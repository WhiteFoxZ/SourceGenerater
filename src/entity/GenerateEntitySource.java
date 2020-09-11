package entity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateEntitySource {

	String DOMAIN = null;
	String CURRENT_DIR = null;
	String entity = null;
	String spec = null;
	String store = null;
	String logic = null;
	String rest = null;
	String jpastore = null;
	String jpo = null;
	String repository = null;
	String domainlifecycle = null;
	String ServiceLifecycle = null;
	String StoreLifecycle = null;

	public void initPath(String ROOT_DIR, String DOMAIN) {		

		CURRENT_DIR = ROOT_DIR ;

		entity = CURRENT_DIR + "/" + DOMAIN + "-domain/src/main/java/com/posco/mes3/" + DOMAIN + "/domain/entity";

		spec = CURRENT_DIR + "/" + DOMAIN + "-domain/src/main/java/com/posco/mes3/" + DOMAIN + "/domain/spec/entity";

		store = CURRENT_DIR + "/" + DOMAIN + "-domain/src/main/java/com/posco/mes3/" + DOMAIN + "/domain/store/entity";

		logic = CURRENT_DIR + "/" + DOMAIN + "-domain/src/main/java/com/posco/mes3/" + DOMAIN + "/domain/logic/entity";

		rest = CURRENT_DIR + "/" + DOMAIN + "-service/src/main/java/com/posco/mes3/" + DOMAIN + "/service/rest/entity";

		jpastore = CURRENT_DIR + "/" + DOMAIN + "-store/src/main/java/com/posco/mes3/" + DOMAIN + "/store";

		jpo = CURRENT_DIR + "/" + DOMAIN + "-store/src/main/java/com/posco/mes3/" + DOMAIN + "/store/jpo";

		repository = CURRENT_DIR + "/" + DOMAIN + "-store/src/main/java/com/posco/mes3/" + DOMAIN
				+ "/store/repository";

		domainlifecycle = CURRENT_DIR + "/" + DOMAIN + "-domain/src/main/java/com/posco/mes3/" + DOMAIN
				+ "/domain/lifecycle";

		ServiceLifecycle = domainlifecycle + "/ServiceLifecycle.java";

		StoreLifecycle = domainlifecycle + "/StoreLifecycle.java";
	}

	public GenerateEntitySource(String ROOT_DIR, String DOMAIN, String entityName) {
		// TODO Auto-generated constructor stub
		
		initPath(ROOT_DIR,DOMAIN);

		File entityPath = new File(entity + "/" + entityName + ".java");
		File specPath = new File(spec + "/" + entityName + "Service.java");
		File logicPath = new File(logic + "/" + entityName + "Logic.java");
		File restPath = new File(rest + "/" + entityName + "Resource.java");
		File storePath = new File(store + "/" + entityName + "Store.java");

		File jpastorePath = new File(jpastore + "/" + entityName + "JpaStore.java");

		File jpoPath = new File(jpo + "/" + entityName + "Jpo.java");
		File repositoryPath = new File(repository + "/" + entityName + "Repository.java");

		File serviceLifecyclePath = new File(ServiceLifecycle);
		File storeLifecyclePath = new File(StoreLifecycle);

//		 System.out.println(entityPath);
//		 System.out.println(specPath);
//		 System.out.println(storePath);
//		 System.out.println(logicPath);
//		 System.out.println(restPath);
//		 System.out.println(jpastorePath);
//		 System.out.println(jpoPath);
//		 System.out.println(repositoryPath);
//		 System.out.println(serviceLifecyclePath);
//		 System.out.println(storeLifecyclePath);
		 

		replace(entityPath, entityName);
		replace(specPath, entityName);
		replace(storePath, entityName);
		replace(logicPath, entityName);
		replace(restPath, entityName);
		replace(storePath, entityName);
		replace(jpastorePath, entityName);
		replace(jpoPath, entityName);
		replace(repositoryPath, entityName);

		makeLifecycle(serviceLifecyclePath, entityName);
		makeLifecycle(storeLifecyclePath, entityName);

		// System.out.println(serviceLifecyclePath.toString());
		// System.out.println(storeLifecyclePath.toString());

	}

	public void makeLifecycle(File path, String entityName) {

		BufferedReader bf = null;

		try {

			FileReader f = new FileReader(path);

			bf = new BufferedReader(f);

			String carmel = entityName.substring(0, 1).toLowerCase() + entityName.substring(1);

			String s;

			StringBuffer sb = new StringBuffer();

			String addStr = "";

			while (true) {
				s = bf.readLine();
				if (s == null)
					break;
				else {

					if (s.toString().contains("requestSampleService();")) {
						addStr = "public " + entityName + "Service request" + entityName + "Service();";
						sb.append(s).append("\n\n");
						sb.append("\t").append(addStr).append("\n");

					} else if (s.toString().contains("requestSampleStore();")) {
						addStr = "public " + entityName + "Store request" + entityName + "Store();";
						sb.append(s).append("\n\n");
						sb.append("\t").append(addStr).append("\n");
					}

					else {
						sb.append(s).append("\n");
					}

				}

			}

			s = sb.toString();

//			System.out.println(s);

			bf.close();

			 writeFile(path,s);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				bf.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	public void replace(File entityPath, String entityName) {

		BufferedReader bf = null;

		try {

			FileReader f = new FileReader(entityPath);
			
			bf = new BufferedReader(f);

			String carmel = entityName.substring(0, 1).toLowerCase() + entityName.substring(1);

			String s;

			StringBuffer sb = new StringBuffer();

			while (true) {
				s = bf.readLine();															
				if (s == null)
					break;

				sb.append(s).append("\n");

			}

			s = sb.toString();
			
			

			s = s.replaceAll("Sample", entityName);

			if (entityPath.toString().contains("Logic")) {
				s = s.replaceAll("sampleStore", carmel + "Store");
			}

			if (entityPath.toString().contains("Resource")) {
				s = s.replaceAll("entityService", carmel + "Service");
			}

			if (entityPath.toString().contains("JpaStore")) {
				s = s.replaceAll("sampleRepository", carmel + "Repository");
			}

//			System.out.println(s);

			bf.close();

			writeFile(entityPath, s);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				bf.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	public void writeFile(File entityPath, String s) {

		
		try {
			
			
			
			FileWriter f = new FileWriter(entityPath);

			f.write(s);

			f.close();

		} catch (Exception e) {

		}

	}

	public static void main(String args[]) {
		
		if(args!=null && args.length==3) {
			System.out.println(args[0]);
			System.out.println(args[1]);
			System.out.println(args[2]);
				
		}
								
		
		if(args==null) {
			
			System.out.println("you have to put three paramter ");
			System.out.println("DOMAIN : m2ae01_lime_calcine ");
			System.out.println("ROOT_DIR : D:/git/m2ae01_lime_calcine/");
			System.out.println("ENTITY : FeSiMainTable");
			System.out.println("example\n\n");
			System.out.println("run.bat m2ae01_lime_calcine D:/git/m2ae01_lime_calcine/ FeSiMainTable");
			
			
			
		}else if(args.length==3) {
			
			System.out.println("START");
			
			String DOMAIN = args[0];
			String ROOT_DIR = args[1];
			String ENTITY = args[2];

			new GenerateEntitySource(ROOT_DIR, DOMAIN, ENTITY);
			
			System.out.println("END");
			
		}else {
			
			System.out.println("you have to put three paramter ");
			System.out.println("DOMAIN : m2ae01_lime_calcine ");
			System.out.println("ROOT_DIR : D:/git/m2ae01_lime_calcine/");
			System.out.println("ENTITY : FeSiMainTable");
			System.out.println("example\n\n");
			
			System.out.println("run.bat m2ae01_lime_calcine D:/git/fesi/ FeSiMainTable");
			
			
		}		

	}
}

