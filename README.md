### A Demo project for Izumi-Distage issue №1557 reproduction

Little application consists of 2 roles:

- `:terminating-role` - role, that works as expected (do things and exits).
- `:stuck-role` - role with a bug - does not exit after completion.

#### Dependencies
- Izumi Distage
- FS2 Kafka
- Cats Effect IO

This project demands FS2 kafka, because the problem caused by role, that has `KafkaProducer` as a dependency.


#### Environment
Though, actual sending to Kafka does not take place, you can run local Kafka instace with Compose:

```sh
docker-compose -f env.yml
```
