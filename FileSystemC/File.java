class File implements FileSystemComponent {

    private String fileName;
    private String content = "";

    public File(String fileName) {
        this.fileName = fileName;
    }

    public void addContent(String content) {
        this.content += content;
    }

    public String readContent() {
        return content;
    }

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public void ls() {
        System.out.println(fileName);
    }

    @Override
    public void ls(String indent, boolean isLast) {
        System.out.println(indent + (isLast ? "└── " : "├── ") + fileName);
    }
}