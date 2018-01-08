# Testzeug

[![Build Status](https://travis-ci.org/ArtunSubasi/testzeug.svg?branch=master)](https://travis-ci.org/ArtunSubasi/testzeug)
[![Coverage Status](https://coveralls.io/repos/github/ArtunSubasi/testzeug/badge.svg?branch=master)](https://coveralls.io/github/ArtunSubasi/testzeug?branch=master)
[![Latest release](https://img.shields.io/github/release/ArtunSubasi/testzeug.svg)](https://github.com/ArtunSubasi/testzeug/releases/latest)

## Table of Contents
[The Testzeug Project](#the-testzeug-project)  
[The Testzeug Language](#the-testzeug-language)  
[Referencing Testzeug beans](#referencing-testzeug-beans)  
[Placeholders](#placeholders)  
[Templates](#templates)  

## The Testzeug Project
The Testzeug Project is an ongoing work which aims to make test data easier to write, read and maintain. It will consist of:
* The Testzeug Language: a domain specific language (DSL) which aims to allow the definition of cross-platform test data in YAML (Testzeug data).
* The Testzeug Libraries: Libraries for different programming languages to use Testzeug data. The first one will be for Java.
* The Testzeug Server: a web-application to serve Testzeug data to clients (automated test runners).

## The Testzeug Language
The Testzeug Language is a DSL based on YAML. It can be used to define a Testzeug context which contains a collection of Testzeug beans. Each Testzeug bean consists of the following attributes:

* id (mandatory): A unique id to allow referencing by other Testzeug beans, test managers or automated test cases
* description (optional): A description to define the purpose or special properties of this Testzeug bean
* type (optional): This type can be used by automated test cases to interpret the Testzeug bean. The semantics is of the type is not strictly defined. This could be concrete Java class, the name of a GUI screen or something abstract.
* tags (optional): Tags (aka labels) for searching, grouping or filtering purposes
* data (optional): The actual test data

### Example of a Testzeug context containing two Testzeug beans
```yaml
- id: Martin
  data:
    name: Martin L. Gore
    age: 25

- id: Kiddy
  description: A test person who is a minor
  type: Person
  tags: young
  data:
    name: Kid A.
    age: 17
```

## Referencing Testzeug beans
Testzeug beans can be referenced by the keyword $ref in order to define tree or graph structures.

### Examples of Testzeug beans with references

```yaml
- id: Homer
  data:
    name: Homer Simpson
    children:
      - $ref Bart
      - $ref Lisa

- id: Bart
  data:
    name: Bart Simpson
    father: $ref Homer

- id: Lisa
  data:
    name: Lisa Simpson
    father: $ref Homer
```

## Placeholders
Data within the Testzeug beans can contain placeholders in order to represent values which are not known during the definition of the test data (e.g. current date or random texts). The list of the placeholders will be developed over time upon requests.

### Examples for the usage of placeholders

```yaml
- id: Fresh Account First Example
  description: An account which was opened today
  data:
    accountName: FreshAccount
    creationDate: $currentDate

- id: Fresh Account Second Example
  description: Placeholders can have parameters. E.g. date formatting
  data:
    accountName: FreshAccount
    creationDate: $currentDate yyyy-MM-dd

- id: Open Account Length Constraints
  description: >
    Invalid formular data for the validation of the length constraints.
    On this example the accountName can have maximal 100 characters.
    The password can have maximal 128 characters.
  data:
    accountName: $randomText 101
    password: $randomText 129
```

## Templates
Testzeug beans can be used as templates which can be concretized by other Testzeug beans in order to reduce redundancy. In other words, Testzeug beans can inherit their data from other Testzeug beans.

### Examples for the usage of Templates

```yaml
- id: Simpson
  data:
    surname: Simpson

- id: Homer
  template: Simpson
  data:
    name: Homer
    gender: male

- id: Homers Daughter
  template: Simpson
  data:
    gender: female
    father: $ref Homer

- id: Lisa
  template: Homers Daughter
  data:
    name: Lisa

- id: Maggie
  template: Homers Daughter
  data:
    name: Maggie
```