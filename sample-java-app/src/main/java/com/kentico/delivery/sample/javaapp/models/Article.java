package com.kentico.delivery.sample.javaapp.models;

import com.kentico.delivery.core.models.ContentItem;
import com.kentico.delivery.core.models.element.ElementMapping;
import com.kentico.delivery.core.models.elements.AssetsElement;
import com.kentico.delivery.core.models.elements.DateTimeElement;
import com.kentico.delivery.core.models.elements.ModularContentElement;
import com.kentico.delivery.core.models.elements.MultipleChoiceElement;
import com.kentico.delivery.core.models.elements.RichTextElement;
import com.kentico.delivery.core.models.elements.TaxonomyElement;
import com.kentico.delivery.core.models.elements.TextElement;
import com.kentico.delivery.core.models.elements.models.AssetModel;
import com.kentico.delivery.core.models.elements.models.MultipleChoiceOption;
import com.kentico.delivery.core.models.elements.models.TaxonomyTerms;

import java.util.ArrayList;
import java.util.Date;

public final class Article extends ContentItem {

    public static final String TYPE = "article";

    @ElementMapping("summary")
    public TextElement summary;

    @ElementMapping("title")
    public TextElement title;

    @ElementMapping("teaser_image")
    public AssetsElement teaserImage;

    @ElementMapping("body_copy")
    public RichTextElement bodyCopy;

    @ElementMapping("post_date")
    public DateTimeElement postDate;

    @ElementMapping("personas")
    public TaxonomyElement personas;

    @ElementMapping("category")
    public MultipleChoiceElement category;

    @ElementMapping("related_articles")
    public ModularContentElement<Article> relatedArticles;

    public String getTitle() {
        return title.getValue();
    }

    public String getTeaserImageUrl(){
        AssetModel[] assets = this.teaserImage.getValue();
        if (assets == null){
            return null;
        }

        if (assets.length == 0){
            return null;
        }

        return assets[0].url;
    }

    public Date getPostDate() {
        return postDate.getValue();
    }

    public String getSummary() {
        return summary.getValue();
    }

    public String getBodyCopy() {
        return bodyCopy.getValue();
    }

    public TaxonomyTerms[] getPersonas() { return personas.getValue(); }

    public MultipleChoiceOption[] getCategories() { return category.getValue(); }

    public ArrayList<Article> getRelatedArticles() { return relatedArticles.getValue(); }
}
