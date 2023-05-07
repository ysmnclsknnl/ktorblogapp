# Configure mongodb
- Create a .env file in the directory where docker-compose.yml is with at least the following

```
MONGODB_USERNAME=myrootusername
MONGODB_PASSWORD=myrootpassword
```

- Optionally, work out how to create the db on startup (eg 'yourBlog'), by editing the file(s) in docker-entrypoint-initdb.d
- If so, work out how to add give a user, or the rootuser, readwrite permissions to the db, also on startup


# Use mongodb
- `docker-compose --env-file .env up  mongodb`
- Test if it is up and accessible from outside the container `curl --connect-timeout 10 --silent --show-error localhost:27017`  . It is up if the message is "ooks like you are trying to access MongoDB over HTTP ..."
-  if not configured in the optional steps above, Go into the container to setup mongodb
```docker exec -it <container_name> /bin/bash```
, and then type (inside the container)
    - `mongo mongodb://myrootusername:myrootpassword@localhost:27017/`
    - `db.getUsers()`
- if the db is persisted to a volume, then these steps shouldn't need to be done again. Tests could create and delete the collections/entries before and after tests are run
