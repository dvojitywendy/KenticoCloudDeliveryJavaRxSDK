package kenticocloud.kenticoclouddancinggoat.data.models;

import java.util.ArrayList;
import java.util.Date;

import com.kenticocloud.delivery.models.ContentItem;
import com.kenticocloud.delivery.models.elements.ModularContentElement;
import com.kenticocloud.delivery.models.elements.MultipleChoiceElement;
import com.kenticocloud.delivery.models.elements.TaxonomyElement;
import com.kenticocloud.delivery.models.elements.models.AssetModel;
import com.kenticocloud.delivery.models.elements.AssetsElement;
import com.kenticocloud.delivery.models.elements.DateTimeElement;
import com.kenticocloud.delivery.models.elements.RichTextElement;
import com.kenticocloud.delivery.models.elements.TextElement;
import com.kenticocloud.delivery.models.elements.models.MultipleChoiceOption;
import com.kenticocloud.delivery.models.elements.models.TaxonomyTerms;
import com.kenticocloud.delivery.models.element.ElementMapping;

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
