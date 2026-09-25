interface FileSystemComponent {
    String getName();

    void ls();

    void ls(String indent, boolean isLast);
}