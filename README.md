# core
Code that I deemed essential for my projects at one point.

# How to use in your project with maven

## Build and install the library
For now, sporniket-core is not deployed in a public repository. One MUST clone the git repository, build and install to the maven local repository.

```
git clone https://github.com/sporniket/core.git
cd core/sporniket-core
mvn install
```

## Add a dependency to your project
Add one of the following dependencies in your pom file :

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
