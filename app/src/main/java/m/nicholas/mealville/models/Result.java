
package m.nicholas.mealville.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("readyInMinutes")
    @Expose
    private Integer readyInMinutes;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageUrls")
    @Expose
    private List<String> imageUrls = null;

    private String firebaseId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param readyInMinutes
     * @param image
     * @param imageUrls
     * @param id
     * @param title
     */
    public Result(Integer id, String title, Integer readyInMinutes, String image, List<String> imageUrls) {
        super();
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.image = image;
        this.imageUrls = imageUrls;
    }

    public Result(String firebaseId, String title, Integer readyInMinutes) {
        super();
        this.firebaseId = firebaseId;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(Integer readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }
}
