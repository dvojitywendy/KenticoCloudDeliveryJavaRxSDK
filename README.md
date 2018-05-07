[![Forums](https://img.shields.io/badge/chat-on%20forums-orange.svg)](https://forums.kenticocloud.com)

# Kentico Cloud Delivery JavaRx/AndroidRx SDK

| Platform        | Maven Central Package  | jCenter Package | 
| ------------- |:-------------:| :-------------:|
| Android      | [![Android](https://img.shields.io/maven-central/v/com.kenticocloud/delivery-android.svg)](https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22delivery-android%22) | [![Android](https://api.bintray.com/packages/kentico/KenticoCloudDeliveryJavaRxSDK/delivery-android/images/download.svg)](https://bintray.com/kentico/KenticoCloudDeliveryJavaRxSDK/delivery-android) |
| JavaRx      | [![JavaRx](https://img.shields.io/maven-central/v/com.kenticocloud/delivery-rx.svg)](https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22delivery-rx%22) | [![JavaRx](https://api.bintray.com/packages/kentico/KenticoCloudDeliveryJavaRxSDK/delivery-rx/images/download.svg)](https://bintray.com/kentico/KenticoCloudDeliveryJavaRxSDK/delivery-rx) |

## Summary

Kentico Cloud Delivery JavaRx/AndroidRx SDK is a client library for retrieving content from [Kentico Cloud](https://kenticocloud.com/). It is written in Java 7 for both Java & Android projects. The SDK is available as `delivery-rx` and `delivery-android` on [Maven Central](https://search.maven.org/#search%7Cga%7C1%7Ckenticocloud) and [jCenter](https://bintray.com/kentico-timothyf/kenticocloud-maven/delivery-rx).

The SDK is built with [ReactiveX programming](http://reactivex.io/) and supports [RxJava2](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) querying. It also integrates with [OkHttp](http://square.github.io/okhttp/) for those developers who do not want to use *Rx*.

## Prerequisites

Java 7+

Android 2.3+ (minSdk 21)

## Getting started

The first step is to include the SDK in your project, for example, as a Gradle compile dependency. Based on your project type, choose one of the following:

#### Java

```
compile 'com.kenticocloud:delivery-rx:2.0.1'
```

#### Android

```
compile 'com.kenticocloud:delivery-android:2.0.1'
```

*Note*: The only difference between these two dependencies is the 'Observable' they present for ReactiveX to subscribe to. Android will present a standard *Rx2AndroidNetworking* request while Java will present a generic *http* request as an observable. Most of your imports will come from the shared `com.kenticocloud.delivery_core` which is automatically included with both packages.

### Configuration

```java
// Kentico Cloud project ID
String projectId = "683771be-aa26-4887-b1b6-482f56418ffd";

// Type resolvers are required to convert the retrieved content items to their strongly-typed models
// based on their 'system.type' property
List<TypeResolver<?>> typeResolvers = new ArrayList<>();

// First, create strongly-typed models representing your items. 
// This is optional, but strongly recommended. It is best practice to use safe types 
// instead of relying on dynamic objects and values. For more details, see
// https://developer.kenticocloud.com/v1/docs/strongly-typed-models
// Here is an example of a strongly-typed model of the 'Cafe' content type.
public final class Cafe extends ContentItem {

    // Codename of your content type in Kentico Cloud
    public static final String TYPE = "cafe";

    @ElementMapping("country")
    public TextElement country;

    @ElementMapping("email")
    public TextElement email;

    public String getCountry() {
        return country.getValue();
    }

    public String getEmail() {
        return email.getValue();
    }
}

// Adds a type resolver that will eventually convert items from JSON to your strongly-typed models at runtime.
// Please note that you currently need to have models for all content types you want to work with.
// We plan on releasing an update that will allow you to return a generic ContentItem class.
typeResolvers.add(new TypeResolver<>(Cafe.TYPE, new Function<Void, Cafe>() {
    @Override
    public Cafe apply(Void input) {
        return new Cafe();
        }
    }
));

// Prepares configuration object
// Note that there are also other parameters, for example, a preview API key
DeliveryConfig config = DeliveryConfig.newConfig(projectId)
    .withTypeResolvers(typeResolvers);
```

### Initialization

#### Java

Imports
```java
import com.kenticocloud.delivery_rx.DeliveryService;
import com.kenticocloud.delivery_core.IDeliveryService;
```

Service
```java
IDeliveryService deliveryService = new DeliveryService(config);
```

#### Android

Imports
```java
import com.kenticocloud.delivery_android.DeliveryAndroidService;
import com.kenticocloud.delivery_core.IDeliveryService;
```

Service
```java
IDeliveryService androidDeliveryService = new DeliveryAndroidService(config);
```

### Get data with Rx (Observables pattern)

```java
deliveryService.<Cafe>items()
    .type(Cafe.TYPE)
    .getObservable()
    .subscribe(new Observer<DeliveryItemListingResponse<Cafe>>() {
        @Override
        public void onSubscribe(Disposable disposable) {
        }

        @Override
        public void onNext(DeliveryItemListingResponse<Cafe> response) {
            // Gets cafe items
            List<Cafe> cafes = response.getItems();

            // Uses a method from your strongly-typed model
            String country = cafes.get(0).getCountry();
        }

        @Override
        public void onError(Throwable throwable) {
        }

        @Override
        public void onComplete() {
        }
    });
```

### Get data using HttpAdapter (OkHttp client)

```java
DeliveryItemListingResponse<Cafe> response = this.deliveryService.<Cafe>items()
    .type(Cafe.TYPE)
    .get();
        
List<Cafe> cafes = response.getItems();
```

## API Reference

### Property binding

It's best practice to map the retrieved content items to their strongly-typed models. To create a content items's representation in code, you need a class with properties representing the individual content elements:

1. Make sure that your model extends the `ContentItem` class.
2. Create public fields with an `ElementMapping` decorator. This will make sure that the value from your field is mapped to the content item element.
3. Based on the type of field, choose the proper element type. Supported element types include: `AssetsElement`, `ContentElement`, `DateTimeElement`, `ModularContentElement`, `MultipleChoiceElement`, `NumberElement`, `RichTextElement`, `TaxonomyElement`, `TextElement` and `UrlSlugElement`.

The following example shows a typical strongly-typed model class with different types of elements:

```java
public final class Coffee extends ContentItem {

    public static final String TYPE = "coffee";

    @ElementMapping("product_name")
    public TextElement productName;

    @ElementMapping("price")
    public NumberElement price;

    @ElementMapping("image")
    public AssetsElement image;

    @ElementMapping("short_description")
    public RichTextElement shortDescription;
}
```

### Filtering, sorting

The SDK contains all available [filters](https://developer.kenticocloud.com/v1/reference#content-filtering) and other parameters ([sort](https://developer.kenticocloud.com/v1/reference#content-ordering), [projection](https://developer.kenticocloud.com/v1/reference#projection), [paging](https://developer.kenticocloud.com/v1/reference#listing-response-paging)) as predefined methods for each query type (different options are available for items and taxonomies query). All of these methods are written in a builder pattern to help you create queries more efficiently.

Example of an advanced items query:

```java
MultipleItemQuery<Cafe> query = deliveryService.<Cafe>items()
    .equalsFilter("elements.title", "London")
    .limitParameter(5)
    .depthParameter(2)
    .orderParameter("elements.street", OrderType.Desc);
```

If you need to add other query parameters to the URL directly, you can use the `addParameter` method:

```java
MultipleItemQuery<Cafe> query = deliveryService.<Cafe>items()
    .addParameter(new Filters.EqualsFilter("elements.title", "London"));
```

### Querying data

Each type of data (item, taxonomy, elements, etc.) can be obtained using the methods available in `IDeliveryClient`. 

Basic examples of different queries:

```java
// Gets items
SingleItemQuery<Cafe> cafeQuery = deliveryService.<Cafe>item("boston");
MultipleItemQuery<Cafe> cafesQuery = deliveryService.<Cafe>items();

// Gets types
SingleTypeQuery typeQuery = deliveryService.type("Cafe");
MultipleTypeQuery typesQuery = deliveryService.types();

// Gets taxonomies
SingleTaxonomyQuery taxonomyQuery = deliveryService.taxonomy("personas");
MultipleTaxonomyQuery taxonomiesQuery = deliveryService.taxonomies();

// Gets elements
SingleContentTypeElementQuery elementQuery = deliveryService.contenTypeElement("cafe", "country");
```

To execute a query, choose either `get` or `getObservable` method depending on whether you want to work with the [ReactiveX](http://reactivex.io) API or not.

```java
// Get examples
Cafe cafe = cafeQuery.get().getItem();
List<Cafe> cafes = cafesQuery.get().getItems();

// Observable examples
cafesQuery.getObservable()
    .subscribe(new Observer<DeliveryItemListingResponse<Cafe>>() {
        @Override
        public void onSubscribe(Disposable disposable) {
        }

        @Override
        public void onNext(DeliveryItemListingResponse<Cafe> response) {
            // Gets cafe items
            List<Cafe> cafes = response.getItems();

            // Uses a method from your strongly typed model
            String country = cafes.get(0).getCountry();
        }

        @Override
        public void onError(Throwable throwable) {
        }

        @Override
        public void onComplete() {
        }
    });

```

### Custom query parameters

It is possible to create custom query parameters in case you need more information in the URL. This can be useful if you use a proxy and need to log additional information.

To create a custom parameter, implement `IQueryParameter` and use it in combination with the `addParameter` query method.

```java
public static class CustomFilter implements IQueryParameter {

    private String data;

    public CustomFilter(String data){
        this.data = data;
    }

    @Override
    public String getParam() {
        return "customData";
    }

    @Override
    public String getParamValue() {
        return this.data;
    }
}

MultipleItemQuery<Cafe> query = deliveryService.<Cafe>items()
    .addParameter(new CustomFilter("result"));

```

### Preview mode

To enable preview mode, pass your Preview API key to the configuration object.

```java
new DeliveryConfig(projectId, typeResolvers, "yourPreviewAPIKey");
```

To make calls to the Preview API globally, use a default `QueryConfig` during initialization. You can override this when executing particular queries.

```java

// Configures global query config that will enable preview mode by default.
QueryConfig defaultQueryConfig = new QueryConfig();
defaultQueryConfig.setUsePreviewMode(true);

DeliveryConfig config = new DeliveryConfig(projectId, typeResolvers, defaultQueryConfig);


// Enables preview mode for a specific call. This overrides global configuration.
MultipleItemQuery<Cafe> query = deliveryService.<Cafe>items()
    .type(Cafe.TYPE)
    .setUsePreviewMode(true);
```

### Getting the URL of a query

You can get the URL of a query without executing it by calling the `getQueryUrl` method on any `IQuery` object.

```java
deliveryService.items()
    .equalsFilter("elements.title", "Warrior")
    .limitParameter(5)
    .depthParameter(2)
    .skipParameter(1)
    .getQueryUrl();
``` 

The code above outputs the following URL:

```
https://deliver.kenticocloud.com/683771be-aa26-4887-b1b6-482f56418ffd/items?elements.title=Warrior&limit=5&depth=2&skip=1
```

### Advanced configuration

During initialization of the `DeliveryConfig` you can configure the following options:

| Method        | Use
| ------------- |:-------------:
| withTypeResolvers | Sets type resolvers responsible for mapping API responses to strongly-typed models.
| withPreviewApiKey      | Sets preview API key.
| withSecuredApiKey | Sets secured API key.
| withDeliveryApiUrl | Sets custom URL of a Kentico Cloud endpoint.
| withDeliveryPreviewApiUrl | Sets custom URL of a Kentico Cloud preview endpoint.
| withThrowExceptionForUnknownTypes | If enabled, the SDK will throw an Exception when it cannot find a strongly-typed model (type resolver) of an item in the response.
| withDefaultQueryConfig | Sets default query config for all queries. This is useful when you want to set a default behavior and then override it on a per-query level.

Example of an advanced Delivery configuration:

```java
IDeliveryConfig config = DeliveryConfig.newConfig("projectId")
    .withPreviewApiKey("previewApiKey")
    .withThrowExceptionForUnknownTypes(true)
    .withDeliveryApiUrl("customDeliveryEndpointUrl");
```

### Handling errors

The SDK will automatically map [Kentico Cloud error responses](https://developer.kenticocloud.com/v1/reference) to a `KenticoCloudResponseException` runtime exception that you can handle.

```java
try {
    DeliveryItemListingResponse<IContentItem> response = deliveryService.items().get();
} catch (KenticoCloudResponseException ex) {
    String cloudErrorMessage = ex.getMessage(); // i.e. missing item
} catch (Exception ex){
    // other error
}
```

## Sample applications
| Android | JavaRx |
| ------ |-----|
| [![Android](https://vignette.wikia.nocookie.net/scribblenauts/images/2/24/Android_Logo.png/revision/latest?cb=20130410160638)](https://github.com/Kentico/KenticoCloudDeliveryJavaRxSDK/tree/master/sample-android-app) | [![JavaRx](http://reactivex.io/assets/Rx_Logo_S.png)](https://github.com/Kentico/KenticoCloudDeliveryJavaRxSDK/tree/master/sample-java-app) |

## Feedback & Contributing

Check out the [contributing](https://github.com/Kentico/KenticoCloudDeliveryJavaRxSDK/blob/master/CONTRIBUTING.md) page to see the best places to file issues, start discussions, and begin contributing.
