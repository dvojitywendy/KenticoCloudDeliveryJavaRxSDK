package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.common;

/**
 * Order type
 */

public enum OrderType {

    Asc("asc"),
    Desc("desc");
    private final String text;

    /**
     * @param text text of enum
     */
    OrderType(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}