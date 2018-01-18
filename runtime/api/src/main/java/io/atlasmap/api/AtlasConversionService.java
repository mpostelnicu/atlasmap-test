/**
 * Copyright (C) 2017 Red Hat, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atlasmap.api;

import java.util.Optional;

import io.atlasmap.v2.FieldType;

public interface AtlasConversionService {

    Optional<AtlasConverter<?>> findMatchingConverter(FieldType source, FieldType target);

    Optional<AtlasConverter<?>> findMatchingConverter(String sourceClassName, String targetClassName);

    Object copyPrimitive(Object sourceValue);

    Object convertType(Object sourceValue, FieldType origSourceType, FieldType targetType)
            throws AtlasConversionException;

    Object convertType(Object sourceValue, String sourceFormat, FieldType targetType, String targetFormat)
            throws AtlasConversionException;

    Object convertType(Object sourceValue, String sourceFormat, Class targetType, String targetFormat)
            throws AtlasConversionException;

    Class<?> boxOrUnboxPrimitive(Class<?> clazz);

    Class<?> classFromFieldType(FieldType fieldType);

    FieldType fieldTypeFromClass(Class<?> clazz);

    FieldType fieldTypeFromClass(String className);

    Boolean isPrimitive(String className);

    Boolean isPrimitive(Class<?> clazz);

    Boolean isPrimitive(FieldType fieldType);

    Boolean isBoxedPrimitive(Class<?> clazz);

}
