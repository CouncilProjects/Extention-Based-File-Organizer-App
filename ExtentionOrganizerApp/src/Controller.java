import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {

	public Controller() {
		
	}
	
	 public String listExtentions(String path)
	 {
		 File directory=new File(path);
		 File[] files=directory.listFiles();
		 if(files==null)
		 {
			 return("Not working");
		 }
		 String returned="";
		 for(File e:files)
		 {
			 //if the extension has not been found before
			 if(!returned.contains(e.getName().substring(e.getName().lastIndexOf(".")+1))
					 &&e.getName().lastIndexOf(".")!=-1 //AND there is an extension
					 &&!e.isDirectory()) //AND it is NOT and directory 
			 {
				 returned+=e.getName().substring(e.getName().lastIndexOf(".")+1)+';';
			 }
		 }
		 return returned;
	 }
	 
	 public void moveToCreate(String path,String name,String ext)
	 {
		 File newFolder=new File(path+"\\"+name);
		 if(!newFolder.mkdir())
		 {
			 return;
		 }
		 File directory=new File(path);
		 File[] files=directory.listFiles();
		 if(files==null)
		 {
			 return;
		 }
		 
		 for(File e:files)
		 {
			 if(e.getName().substring(e.getName().lastIndexOf(".")+1).contentEquals(ext))
			 {
				 try {
					Files.move(Paths.get(e.getPath()),Paths.get(newFolder+"\\"+e.getName()));
				} catch (IOException e1) {
					System.err.println("ERROR while trying to move a file");
				}
			 }
		 }
		 
	 }

}
