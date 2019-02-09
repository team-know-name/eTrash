package mlexpert.tanishqsaluja.etrash;

public class Item {
    private String garbage_category;
    private String price;

    Item(String gc, String p) {
        garbage_category = gc;
        price = p;
    }

    public void setGarbage_category(String garbage_category) {
        this.garbage_category = garbage_category;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGarbage_category() {
        return garbage_category;
    }

    public String getPrice() {
        return price;
    }
}
