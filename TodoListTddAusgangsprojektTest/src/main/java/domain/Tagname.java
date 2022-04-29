package domain;

import exceptions.InvalidTagNameException;

import java.io.Serializable;

/**
 * Valueobject for tagnames.
 */
public record Tagname(String tagname) implements Serializable {
    /**
     * @param tagname The name for the Tag to be created.
     * @throws InvalidTagNameException Tagnames must not be null und must contain at least one character.
     */
    public Tagname(String tagname) {
        if (tagname != null && tagname.length() > 0) {
            this.tagname = tagname;
        } else {
            throw new InvalidTagNameException();
        }
    }
}
