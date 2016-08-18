# Sporniket-core

> [WARNING] Please read carefully this note before using this project. It contains important facts.

Content

1. What is **Sporniket-core**, and when to use it ?
2. What should you know before using **Sporniket-core** ?
3. How to use **Sporniket-core** ?
4. Known issues
5. Miscellanous

##1. What is **Sporniket-core**, and when to use it ?
**Sporniket-core** is a collection of libraries containing code that I deemed essential for my projects at one point.

### What's new in v16.08.00

* a regexp utility that create a pattern that match a formatted input using a limited description format.

### What's new in v15.09.00

* ssl utility to get a required SSLContext to use an https url.

### What's new in v15.03.01

* general availability through central sonatype maven repository.


###Licence
 **Sporniket-core** is free software: you can redistribute it and/or modify it under the terms of the
 GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 option) any later version.

 **Sporniket-core** is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 more details.
 
 You should have received a copy of the GNU Lesser General Public License along with **Sporniket-core**.
 If not, see http://www.gnu.org/licenses/ .


##2. What should you know before using **Sporniket-core** ?
**Sporniket-core** consists of and relies only on standard jdk 8 :

* **sporniket-core-lang** : general purpose code.
* **sporniket-core-io** : code related with IO, mostly files.
* **sporniket-core-ml** : code related with xml, sgml, html,... processing.
* **sporniket-core-ui** : code related with swing.

> Do not use **Sporniket-core** if this project is not suitable for your project

##3. How to use **Sporniket-core** ?

###From source
To get the latest available code, one must clone the git repository, build and install to the maven local repository.

	git clone https://github.com/sporniket/core.git
	cd core
	mvn install

###Maven
Add any of the following dependencies that are appropriate to your project.

```
<dependency>
	<groupId>com.sporniket.core</groupId>
	<artifactId>sporniket-core-lang</artifactId>
	<version><!-- the version to use --></version>
</dependency>
<dependency>
	<groupId>com.sporniket.core</groupId>
	<artifactId>sporniket-core-io</artifactId>
	<version><!-- the version to use --></version>
</dependency>
<dependency>
	<groupId>com.sporniket.core</groupId>
	<artifactId>sporniket-core-ml</artifactId>
	<version><!-- the version to use --></version>
</dependency>
<dependency>
	<groupId>com.sporniket.core</groupId>
	<artifactId>sporniket-core-ui</artifactId>
	<version><!-- the version to use --></version>
</dependency>
```

###Directions and sample code
Read the javadoc and look at the test code.

##4. Known issues
...

##5. Miscellanous
### Report issues
Use the issue reporting system at the [project page](https://github.com/sporniket/core)


