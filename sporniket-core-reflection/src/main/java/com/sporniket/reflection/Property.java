package com.sporniket.reflection ;

class Property {

    private final Getter myGetter ;

    private final SetterUsingStringValue mySetter ;

    public Property(Getter getter, SetterUsingStringValue setter) {
        super() ;
        myGetter = getter ;
        mySetter = setter ;
    }

    public Getter getGetter() {
        return myGetter ;
    }

    public SetterUsingStringValue getSetter() {
        return mySetter ;
    }

}
