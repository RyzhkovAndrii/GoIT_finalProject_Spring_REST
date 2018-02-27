package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import java.util.Collection;

public interface ExtendedConversionService {

    <T> T convert(Object obj, Class<T> type);

    <T> Collection<T> convert(Collection<?> collection, Class<T> type);

}
