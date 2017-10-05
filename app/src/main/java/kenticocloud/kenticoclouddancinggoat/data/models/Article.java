package kenticocloud.kenticoclouddancinggoat.data.models;

import org.json.JSONException;

import java.text.ParseException;
import java.util.Date;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements.TextElement;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.item.ElementMapping;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public final class Article extends ContentItem {

    public Article(){
        // required for Java Beans
    }

    public static final String TYPE = "article";

    private String _title;
    private String _teaserImageUrl;
    private Date _postDate;
    private String _summary;
    private String _bodyCopy;

    @ElementMapping("title")
    public TextElement testTitleElement;

    public void setTestTitleElement(TextElement element){
        this.testTitleElement = element;
    }

    public TextElement getTestTitleElement(){
        return this.testTitleElement;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getTitle() {
        return _title;
    }

    public void setTeaserImageUrl(String teaserImageUrl) {
        this._teaserImageUrl = teaserImageUrl;
    }

    public String getTeaserImageUrl() {
        return _teaserImageUrl;
    }

    public void setPostDate(Date postDate) {
        this._postDate = postDate;
    }

    public Date getPostDate() {
        return _postDate;
    }

    public void setSummary(String summary) {
        this._summary = summary;
    }

    public String getSummary() {
        return _summary;
    }

    public void setBodyCopy(String bodyCopy) {
        this._bodyCopy = bodyCopy;
    }

    public String getBodyCopy() {
        return _bodyCopy;
    }

    @Override
    public void mapProperties() throws ParseException, JSONException {
        this.setTitle(this.getStringValue("title"));
        this.setTeaserImageUrl(this.getAssetUrl("teaser_image"));
        this.setPostDate(this.getDateValue("post_date"));
        this.setSummary(this.getStringValue("summary"));
        this.setBodyCopy(this.getStringValue("body_copy"));
    }
}
