package demo.com.forummanagement.db;

public class User {

    private String name;
    private String gender;
    private int age;
    private int level;
    private int coin;
    private String intro;

    public User(String name, String gender, int age, int level, int coin, String intro) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.level = level;
        this.coin = coin;
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

}
