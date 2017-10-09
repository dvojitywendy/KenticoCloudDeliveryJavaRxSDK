package kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.util.Date;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.exceptions.KenticoCloudException;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.utils.DateHelper;

public class DateTimeElement extends ContentElement<Date> {
    private Date _value;

    public DateTimeElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type,
            JsonNode value
    ){
        super(objectMapper, name, codename, type);

        try {
            _value = DateHelper.parseIso8601(value.textValue());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new KenticoCloudException("Could not parse Date for field '" + codename + "'", e);
        }
    }

    @Override
    public Date getValue(){
        return this._value;
    }
}