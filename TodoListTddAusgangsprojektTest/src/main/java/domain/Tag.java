package domain;

import exceptions.InvalidTagNameException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * Tag-Representation. Tags are used to tag the tasks.
 */
@Getter
@EqualsAndHashCode
public class Tag implements Serializable {

    private Tagname tagname;

    /**
     * @param tagname Tagname for creation of the tag.
     * @throws InvalidTagNameException Provided tagname must not be null;
     */
    public Tag(Tagname tagname) {
        if (tagname == null) throw new InvalidTagNameException();
        this.tagname = tagname;
    }

    /**
     * @param tagname Tagname for creation of the tag.
     * @throws InvalidTagNameException Could throw InvalidTagNameException if the String provided for generating the tagname is invalid for creation of tagname.
     */
    public Tag(String tagname) {
        this.tagname = new Tagname(tagname);
    }

    /**
     * Change the tagname of the Tag with the provided tagname.
     *
     * @param tagname New tagname
     */
    public void changeTagname(Tagname tagname) {
        if (tagname == null) throw new InvalidTagNameException();
        this.tagname = tagname;
    }


}
