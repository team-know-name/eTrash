package mlexpert.tanishqsaluja.etrash;

public class Contribution {
    private int score;
    private String str;
    private int image;

    Contribution(int i, String t, int s) {
        this.str = t;
        this.image = i;
        this.score = s;
    }

    public int getScore() {
        return score;
    }

    public String getStr() {
        return str;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStr(String text) {
        this.str = text;
    }
}
