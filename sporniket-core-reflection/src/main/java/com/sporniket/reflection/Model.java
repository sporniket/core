package com.sporniket.reflection ;

import java.util.HashMap ;
import java.util.Map ;

class Model {

    private final Map<String, Property> myProperties = new HashMap<>(20) ;

    public Map<String, Property> getProperties() {
        return myProperties ;
    }

}
