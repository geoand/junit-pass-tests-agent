## Purpose

You might need your JUnit tests to always pass for whatever reason (not naming any names :))
For this reason, just use the java agent that is produced by this project! 

## Prerequisites

A JDK 1.7+ needs to be installed on your machine

## Build the sample

This is Maven multi-module project that contains the agent module and a sample for testing it.
The sample contains a JUnit test which under normal circumstances will always fail!
However, when run with the agent, the tests always pass!
  
From the project root simply run:   
  
    $ ./mvnw package  
    
The goal will create a java agent in `./agents/junit-agent.jar` that is then used when running the tests for the sample.

The agent in then used when running the tests using the configuration than can be found `sample/pom.xml`


## Inspiration

I came up with this simple project after checking out [this](https://github.com/auchenberg/volkswagen) awesome project!
 
