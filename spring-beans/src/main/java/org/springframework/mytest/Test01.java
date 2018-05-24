/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.mytest;

import java.io.IOException;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import test.Student;

/**
 * 
 * @author caoping
 */
public class Test01 {

	public static void main(String[] args) throws IOException {
		ClassPathResource resource = new ClassPathResource("beans.xml");
		@SuppressWarnings("deprecation")
		XmlBeanFactory bf = new XmlBeanFactory(resource);
		Student stu = (Student) bf.getBean("student");
		stu.setAge(20);
		System.out.println(stu.getAge());
	}
}
