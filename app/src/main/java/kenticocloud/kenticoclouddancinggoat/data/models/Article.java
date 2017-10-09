package kenticocloud.kenticoclouddancinggoat.data.models;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.ModularContentElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.MultipleChoiceElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.TaxonomyElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models.AssetModel;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.AssetsElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.DateTimeElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.RichTextElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.TextElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models.MultipleChoiceOption;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.models.TaxonomyTerms;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.ElementMapping;

/**
 * Created by RichardS on 15. 8. 2017.
 */

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
