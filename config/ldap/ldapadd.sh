docker exec -u root -it ldap bash
ldapadd -x -D "cn=admin,dc=example,dc=org" -w admin -f /my/ldap-data.ldif