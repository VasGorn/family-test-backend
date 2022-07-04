# family-test-backend
Quick Start:
1. Create role in DBMS:
```sql
CREATE ROLE family 
LOGIN 
PASSWORD 'family';
```
2. Create database in DBMS:
```sql
CREATE DATABASE family
WITH 
	ENCODING = 'UTF8'
	OWNER = family;
```
3. Clone repository:
```
git clone https://github.com/VasGorn/family-test-backend.git family-test-backend
```
4. open project in IDE
5. run FamilyTestBackendApplication.main()