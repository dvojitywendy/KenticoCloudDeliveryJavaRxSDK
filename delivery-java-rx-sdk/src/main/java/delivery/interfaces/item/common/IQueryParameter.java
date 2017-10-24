package delivery.interfaces.item.common;

/**
 * Query parameter that is used to construct URL such as: domain.com?param=paramValue
 */
public interface IQueryParameter {
    String getParam();
    String getParamValue();
}
