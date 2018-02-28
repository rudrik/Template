package template.r3tech.com.model;

/**
 * Created by Zymr Inc. on 28/2/18.
 */
public class ItemModel {
    private int id;
    private String itemContent;
    private boolean isFav;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
