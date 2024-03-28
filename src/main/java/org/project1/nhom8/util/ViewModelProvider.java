package org.project1.nhom8.util;

public class ViewModelProvider<TConverter> {

    private Class<TConverter> converterClass;

    public ViewModelProvider(Class<TConverter> converterClass) {
        this.converterClass = converterClass;
    }
}
