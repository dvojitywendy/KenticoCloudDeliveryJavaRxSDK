package com.kentico.delivery.sample.androidapp.app.shared;

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

