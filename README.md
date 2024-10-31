## Coinex Java Library
Communicate with coinex api with java programming language.

### Usage:

```java
import ir.moke.coinex.resource.Perpetual;

Perpetual perpetual = new Coinex.Builder()
        .setAccessId("ACCESS_ID")
        .setSecretKey("SECRET_KEY")
        .perpetual();
```