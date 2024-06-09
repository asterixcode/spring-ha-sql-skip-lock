# spring-ha-sql-skip-lock

## Overview

Sample code for `SELECT ... FOR UPDATE SKIP LOCKED` using Spring Boot and JPA.

## Use Case example

A scheduled job in a high available environment that fetches data from a database table and processes it.

## Description

In a high available distributed system, a job runs on multiple application nodes/instances, but each node should process a different set of data and the data should not be processed by more than one node.

The data is fetched from a database table and a lock is acquired on the data to prevent other nodes from processing the same data.

The lock is released after the data is processed, but this is not optimal for the performance.

Therefore, each node should not wait for the lock to be released when the data is already being processed by another node.

The node should instead skip the data that is already being processed by another node and process the next available data.

## Credits

Inspired by the Spring I/O 2024 Barcelona talk by [Rafael Ponte](https://github.com/rponte)

YouTube link [Distributed Scheduling with Spring Boot: the challenges & pitfalls of implementing a background job](https://www.youtube.com/watch?v=ghpljMg8Ecc)



