
package m.nicholas.mealville.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtendedIngredient {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("aisle")
    @Expose
    private String aisle;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("unitShort")
    @Expose
    private String unitShort;
    @SerializedName("unitLong")
    @Expose
    private String unitLong;
    @SerializedName("originalString")
    @Expose
    private String originalString;
    @SerializedName("metaInformation")
    @Expose
    private List<Object> metaInformation = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ExtendedIngredient() {
    }

    /**
     * 
     * @param image
     * @param amount
     * @param unit
     * @param unitShort
     * @param name
     * @param unitLong
     * @param originalString
     * @param id
     * @param aisle
     * @param metaInformation
     */
    public ExtendedIngredient(Integer id, String aisle, String image, String name, Double amount, String unit, String unitShort, String unitLong, String originalString, List<Object> metaInformation) {
        super();
        this.id = id;
        this.aisle = aisle;
        this.image = image;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.unitShort = unitShort;
        this.unitLong = unitLong;
        this.originalString = originalString;
        this.metaInformation = metaInformation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public void setUnitLong(String unitLong) {
        this.unitLong = unitLong;
    }

    public String getOriginalString() {
        return originalString;
    }

    public void setOriginalString(String originalString) {
        this.originalString = originalString;
    }

    public List<Object> getMetaInformation() {
        return metaInformation;
    }

    public void setMetaInformation(List<Object> metaInformation) {
        this.metaInformation = metaInformation;
    }

}
