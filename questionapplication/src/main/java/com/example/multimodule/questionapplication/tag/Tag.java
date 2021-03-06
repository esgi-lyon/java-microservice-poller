package com.example.multimodule.questionapplication.tag;

import javax.persistence.*;

import com.example.multimodule.questionapplication.category.Category;
import com.example.multimodule.library.IdentifiedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Tag class.
 */
@Entity
public class Tag implements IdentifiedEntity {

    /**
     * The id.
     */
    @GeneratedValue(generator = "seq_gen_tag")
    @GenericGenerator(name = "seq_gen_tag", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = { @Parameter(name = "sequence_name", value = "seq_tag"),
                    @Parameter(name = "initial_value", value = "0"), @Parameter(name = "increment_size", value = "1") })
    @Id
    private long id;

    /**
     * The tag value.
     */
    private String value;
    /**
     * Category associated.
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    private Category category;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param newId the new id to set
     */
    public void setId(final long newId) {
        this.id = newId;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param newValue the value to set
     */
    public void setValue(final String newValue) {
        this.value = newValue;
    }

    /**
     * @return (Category)
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param newCategory the category to set
     */
    public void setCategory(final Category newCategory) {
        this.category = newCategory;
    }

}
