## Coinex Java Library
Communicate with coinex api with java programming language.

```xml
<dependency>
    <groupId>ir.moke</groupId>
    <artifactId>coinex4j</artifactId>
</dependency>
```

### Usage:

```java
import ir.moke.coinex.resource.Perpetual;

Perpetual perpetual = new Coinex.Builder()
        .setAccessId("ACCESS_ID")
        .setSecretKey("SECRET_KEY")
        .perpetual();
```