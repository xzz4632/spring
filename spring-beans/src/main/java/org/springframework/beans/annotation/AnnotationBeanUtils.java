/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringValueResolver;

/**
 * General utility methods for working with annotations in JavaBeans style.
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @since 2.0
 */
public abstract class AnnotationBeanUtils {

	/**
	 * Copy the properties of the supplied {@link Annotation} to the supplied target bean.
	 * Any properties defined in {@code excludedProperties} will not be copied.
	 * 
	 * @param ann the annotation to copy from
	 * @param bean the bean instance to copy to
	 * @param excludedProperties the names of excluded properties, if any
	 * @see org.springframework.beans.BeanWrapper
	 */
	public static void copyPropertiesToBean(Annotation ann, Object bean,
			String... excludedProperties) {
		copyPropertiesToBean(ann, bean, null, excludedProperties);
	}

	/**
	 * Copy the properties of the supplied {@link Annotation} to the supplied target bean.
	 * Any properties defined in {@code excludedProperties} will not be copied.
	 * <p>
	 * A specified value resolver may resolve placeholders in property values, for
	 * example.
	 * 
	 * @param ann the annotation to copy from
	 * @param bean the bean instance to copy to
	 * @param valueResolver a resolve to post-process String property values (may be
	 *        {@code null})
	 * @param excludedProperties the names of excluded properties, if any
	 * @see org.springframework.beans.BeanWrapper
	 * 
	 * 将提供的注解属性复制给指定的bean
	 */
	public static void copyPropertiesToBean(Annotation ann, Object bean,
			StringValueResolver valueResolver, String... excludedProperties) {
		Set<String> excluded = new HashSet<String>(Arrays.asList(excludedProperties));
		// 返回 Method 对象的一个数组，这些对象反映此 Class
		// 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
		// 返回数组中的元素没有排序，也没有任何特定的顺序。如果该类或接口不声明任何方法，或者此 Class 对象 表示一个基本类型、一个数组类或
		// void，则此方法返回一个长度为 0 的数组。类初始化方法 <clinit>
		// 不包含在返回数组中。如果该类声明带有相同参数类型的多个公共成员方法，则它们都包含在返回的数组中。
		//返回注解中的所有方法名
		Method[] annotationProperties = ann.annotationType().getDeclaredMethods();
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(bean);
		for (Method annotationProperty : annotationProperties) {
			//以 String 形式返回此 Method 对象表示的方法名称。
			String propertyName = annotationProperty.getName();
			if ((!excluded.contains(propertyName))
					&& bw.isWritableProperty(propertyName)) {
				Object value = ReflectionUtils.invokeMethod(annotationProperty, ann);
				if (valueResolver != null && value instanceof String) {
					value = valueResolver.resolveStringValue((String) value);
				}
				bw.setPropertyValue(propertyName, value);
			}
		}
	}

}
