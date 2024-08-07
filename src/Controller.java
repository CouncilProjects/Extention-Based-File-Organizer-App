import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Controller {

	public Controller() {
		
	}
	
	 public Set<String> listExtentions(String path)
	 {
		 File directory=new File(path);
		 File[] files=directory.listFiles();
		 if(files==null) // Something went wrong when reading the directory
		 {
			 return null;
		 }
		 // A Set holds unique instances 
		 Set<String> extensions=new HashSet<String>();
		 for(File e:files)
		 {
			 if(e.getName().lastIndexOf(".")!=-1  &&!e.isDirectory()) //If there is an extension AND it is NOT and directory 
			 {
				 // Due to the nature of Set no duplicates will be added
				 extensions.add(e.getName().substring(e.getName().lastIndexOf('.')+1).toLowerCase());
			 }
		 }
		 return extensions;
	 }
	 
	 public int moveToCreate(String path,String name,String ext,Boolean mode)
	 {
		 File newFolder=new File(path+"\\"+name);
		 if(!newFolder.mkdir()&&mode==false) //Directory already exists. If append mode is on ignore it
		 {
			 return 1; 
		 }
		 File directory=new File(path);
		 File[] files=directory.listFiles();
		 if(files==null) //if there are no files to move it does not constitute a problem.
		 {
			 return 0;
		 }
		 
		 for(File e:files)
		 {
			 if(e.getName().substring(e.getName().lastIndexOf(".")+1).contentEquals(ext))
			 {
				 try {
					Files.move(Paths.get(e.getPath()),Paths.get(newFolder+"\\"+e.getName()));
				} catch (IOException e1) {
					// do nothing
				}
			 }
		 }
		 return 0;
	 }

}
