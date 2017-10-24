package delivery.models.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NumberElement extends ContentElement<Double> {
    private Double value;

    public NumberElement(
            ObjectMapper objectMapper,
            String name,
            String codename,
            String type,
            JsonNode value
    ){
        super(objectMapper, name, codename, type);

        this.value = value.doubleValue();
    }

    @Override
    public Double getValue(){
        return this.value;
    }
}