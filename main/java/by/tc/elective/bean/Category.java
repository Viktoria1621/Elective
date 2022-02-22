package by.tc.elective.bean;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {
    private int categoryID;
    private String categoryTitle;

    public Category() {

    }

    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Category(int categoryID, String categoryTitle) {
        this.categoryID = categoryID;
        this.categoryTitle = categoryTitle;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryTitle='" + categoryTitle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return categoryID == category.categoryID && categoryTitle.equals(category.categoryTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID, categoryTitle);
    }
}
