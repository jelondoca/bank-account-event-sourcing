**CREATE ACCOUNT**

```bash
curl -i -XPOST -d '{"ownerName":"Sergio","ownerSurnames":"Verde"}' -H 'Content-Type:application/json' localhost:9090/bank-accounts/resources/accounts
```

**DEPOSIT QUANTITY IN ACCOUNT**
```bash
curl -i -XPOST -d '{"account":"7f8eab7b-bb4e-457f-9b19-74e9cdcd326a","quantity":"20"}' -H 'Content-Type:application/json' localhost:8080/bank-operations/resources/operations/deposit
```