# osgi-topaz

The simplest OSGi template for *Apache Felix* + *Web Console* + *Declarative Services*

```
mvn clean install -P deploy-bundle
```

- [Bundle](http://localhost:8080/system/console/bundles/com.ilyamur.osgi.topaz)
- [Servlet example](http://localhost:8080/osgi-topaz/hello?name=John)
- [Component configuration example](http://localhost:8080/system/console/configMgr/com.ilyamur.osgi.topaz.service.impl.HelloServiceImpl)
