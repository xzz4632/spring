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

package test;


/**
 * 
 * @author caoping
 */
public class Student {

	private String name;
	
	private Integer age;

	
	
	/**
	 * @param name
	 * @param age
	 */
	public Student(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}


	/**
	 * 
	 */
	public Student() {
		super();
	}


	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getAge() {
		return age;
	}

	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
