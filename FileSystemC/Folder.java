import java.util.*;

class Folder implements FileSystemComponent {

    private String folderName;
    private List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String folderName) {
        this.folderName = folderName;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public String getName() {
        return folderName;
    }

    @Override
    public void ls() {
        System.out.println(folderName);
        for (int i = 0; i < children.size(); i++) {
            System.out.println("│");
            boolean isLast = (i == children.size() - 1);
            children.get(i).ls("", isLast);
        }
    }

    @Override
    public void ls(String indent, boolean isLast) {
        System.out.println(indent + (isLast ? "└── " : "├── ") + folderName);
        String childIndent = indent + (isLast ? "    " : "│   ");
        for (int i = 0; i < children.size(); i++) {
            boolean lastChild = (i == children.size() - 1);
            children.get(i).ls(childIndent, lastChild);
        }
    }
}