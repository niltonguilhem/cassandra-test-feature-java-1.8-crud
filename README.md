# example-cassandra-test

Api para estudo da conexão do cassandra

# Docker

```
docker run --name some-cassandra -p 9042:9042 cassandra
```
```
docker exec -it some-cassandra cqlsh
```
# Create Keyspace

```
CREATE KEYSPACE testKeySpace
  WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 1 
  };
```

# Create database
```
CREATE TABLE testKeySpace.book(
   id uuid, 
   title text, 
   name text, 
   PRIMARY KEY (id));
```