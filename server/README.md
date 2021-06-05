**First run**

Create MySQL container:

```console
docker run --name mysql_m2 -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d mysql mysqld --default-authentication-plugin=mysql_native_password
```

Check container logs:

```console
docker logs -f mysql_m2
```

After the MySQL container is up and running, fill the database:

```console
docker exec -i mysql_m2 mysql -u root -ppassword < database.sql
```

**After the first run**

```console
docker start mysql_m2
```

**Removing the container**

```console
docker stop mysql_m2 && docker rm mysql_m2
```
