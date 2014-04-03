Blender
=======

Blender is used to merge properties from the property files and/or system properties and provide the correct override semantics. It can also be used to serialize java beans from these properties and optionally validate them.
Property names specified in the System properties override the properties specified in the property files.

Properties are read using the commons-configuration library.
Beans are serialized using the commons-beanutils library.
Validation is done using hibernate validator library.

### Usage
First create the Blender config with the list of property files that needs to be processed. If system properties needs to be accessible, then add that to the configuration. By default, it is not added :

    BlenderConfig config = new BlenderConfig.Builder().withPropertyFiles(PROPERTIES_FILE_1).useSystemProperties().build();


Here PROPERTIES_FILE_1 has the following properties:
    name=name1
    value=value1

Then create the Blender object using the above config :

    Blender blender = new Blender(config)


To access the properties :

    blender.valueOf("name")

To serialize properties to a bean

    class SimpleBean{
        private String name;
        private String value;

        //add getters and setters
    }

    SimpleBean bean = new SimpleBean()
    blender.fill(bean)

Now bean will be serialized with the values correctly.


To validate the bean, add the hibernate validator annotations as specified in [here](http://hibernate.org/validator/)

    class SimpleBean{

        @NotEmpty
        private String name;

        @NotEmpty
        @Size(min = 5, max = 14)
        private String value;

        //add getters and setters
    }

    SimpleBean bean = new SimpleBean()
    blender.fill(bean)

Blender will automatically validate the bean if annotations are present. If validation fails, BlenderException is thrown.

Nested objects will also be serialized properly.

### System Properties
If useSystemProperties() is specified when the config object is constructed, all the system properties are available.


### Prefix properties
Blender can be configured to read only properties that have a specific prefix.

    BlenderConfig config = new BlenderConfig.Builder().withPropertyFiles(PROPERTIES_FILE_1).withPrefix("org.blender").build();

Properties prefixed with "org.blender" will be filtered and the prefix will be removed. If properties are to be serialized to a bean, then
they will be done without the prefix.


### Have a look at BlenderTest for examples