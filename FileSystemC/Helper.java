public class Helper {

    public static void main(String[] args) {

        // Root folder
        Folder root = new Folder("root");

        // Folders
        Folder documents = new Folder("documents");
        Folder projects = new Folder("projects");
        Folder photos = new Folder("photos");

        // Files
        File resume = new File("resume.pdf");
        File project = new File("project.txt");
        File image = new File("image.jpg");
        File readme = new File("readme.txt");

        // Add files to folders
        documents.add(resume);
        projects.add(project);
        photos.add(image);

        // Add sub-folder to documents
        documents.add(projects);

        // Add folders/files to root
        root.add(documents);
        root.add(photos);
        root.add(readme);

        // Display filesystem
        root.ls();
    }
}