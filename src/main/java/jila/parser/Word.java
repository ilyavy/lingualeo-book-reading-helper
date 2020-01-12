package jila.parser;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Represents word entity.
 * It is an abstract class. It does not implement functionality
 * related to contexts (the sentence, where the word has been used).
 * How to store and work with context should be decided in the concrete
 * implementations. It can be array, list, map or anything else.
 */
public abstract class Word implements Comparable<Word>, Jsonable {
    /**
     * String value of word entity.
     */
    private String word = "";

    /**
     * The translation of the word.
     */
    private String translate = "";

    /**
     * The flag, which shows either the word is known by the user or not.
     */
    private boolean known = false;

    /**
     * The default constructor.
     */
    public Word() {
    }

    /**
     * Creates the word entity by its string value.
     * @param word  string representation of the word
     */
    public Word(final String word) {
        setWord(word);
    }

    public Word(final String word, final String context) {
        this(word);
        setContext(context);
    }


    /**
     * Returns the string value of the word.
     * @return  string representation of the word
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the translation of the word.
     * @return  the translation of the word
     */
    public String getTranslate() {
        return translate;
    }

    /**
     * Returns the context (the sentence, where the word
     * has been used) as a string object.
     * @return
     */
    public abstract String getContext();

    /**
     * Sets a context to the word.
     * @param context   the array of Word objects, forming the
     *                  sentence, where the word has been used.
     */
    public abstract Word setContext(String context);

    /**
     * Returns the value of the known flag.
     * @return boolean, either the word is already known by a user or not
     */
    public boolean isKnown() {
        return known;
    }

    /**
     * Returns the value of the count.
     * @return how much times the word has been found in the text
     */
    public abstract long getCount();

    /**
     * Sets the new value for string representation of the word.
     * Should not be given public access. Supposed to be used only
     * inside the class.
     * @param word  a string representation of the word
     */
    protected Word setWord(final String word) {
        this.word = word.toLowerCase();
        return this;
    }

    /**
     * Sets the new translation of the word.
     * @param translate the tranlsation of the word
     */
    public Word setTranslate(final String translate) {
        this.translate = translate;
        return this;
    }

    /**
     * Sets the new value for the known flag.
     * @param known boolean value of the known flag
     */
    public Word setKnown(final boolean known) {
        this.known = known;
        return this;
    }

    /**
     * Sets new value for the count field.
     * @param newCount  a new value for the cound field
     */
    public abstract Word setCount(final long newCount);

    public abstract long incrementCount();

    /**
     * Transforms the word into a string.
     * @return  the string form of the word entity
     */
    @Override
    public String toString() {
        return word + " :: " + getCount();
    }

    /**
     * Compares the string representations of this word with another one.
     * @param that  the word to be compared with
     * @return  -1 - this < that, 0 - equal, +1 - this > that
     */
    @Override
    public int compareTo(final Word that) {
        return word.compareTo(that.word);
    }

    /**
     * Returns true if this and that word are equal in the
     * meaning of their string representations and the count values.
     * @param o the word to be compared with
     * @return  boolean, equal or not
     */
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Word)) {
            return false;
        }
        Word that = (Word) o;
        if (word.equals(that.word) && getCount() == that.getCount()) {
            return true;
        }
        return false;
    }

    /**
     * Creates {@link JsonObject} without special attributes.
     * @return  JSON representation of the word with its fields
     */
    @Override
    public JsonObject toJsonObject() {
        return toJsonObject(null);
    }

    /**
     * Creates {@link JsonObject} with special attributes.
     * @param attributes    the map of the attributes to be used with the
     *                      resulting JsonObject
     * @return  JSON representation of the word with its fields
     */
    @Override
    public JsonObject toJsonObject(final Map<String, String> attributes) {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObjectBuilder builder = factory.createObjectBuilder();

        builder.add("word", getWord());
        builder.add("translate", getTranslate());
        builder.add("context", getContext());
        builder.add("known", isKnown());
        builder.add("count", getCount());

        if (attributes != null) {
            for (Entry<String, String> entry : attributes.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }

        return builder.build();
    }


    /**
     * Creates new CountComparator.
     * @return new CountComparator
     */
    public static Comparator<Word> countOrder() {
        return new CountComparator();
    }


    /**
     * Comparator for words, which uses count value of the words
     * in order to compare them.
     */
    protected static class CountComparator
            implements Comparator<Word> {
        /**
         * Compares the word entities by their field count, i.e. the word
         * is bigger if it has been found more often in the source text.
         * @param o1
         * @param o2
         * @return -1 - o1 < o2, 0 - equal, +1 - o2 > o1
         */
        @Override
        public int compare(final Word o1, final Word o2) {
            Long c1 = Long.valueOf(o1.getCount());
            Long c2 = Long.valueOf(o2.getCount());
            return c1.compareTo(c2);
        }
    }
}
