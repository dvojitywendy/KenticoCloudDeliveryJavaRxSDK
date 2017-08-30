package kenticocloud.kenticoclouddancinggoat.app.shared;

/**
 * Created by RichardS on 30. 8. 2017.
 */
public enum CommunicationHub {
    ArticleCodename("ArticleCodename"),
    CoffeeCodename("CoffeeCodename"),
    CafeCodename("CafeCodename");

    private final String text;

    private CommunicationHub(final String text) {
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

