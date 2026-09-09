class Player{
    private String name;
    private int age;
    private int playerId;
    private int position = 0;
    private static int idCounter = 1;
    public Player(String name, int age){
        this.name = name;
        this.age = age;
        this.playerId = idCounter++;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public int getPlayerId(){
        return playerId;
    }
    public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position = position;
        }
}