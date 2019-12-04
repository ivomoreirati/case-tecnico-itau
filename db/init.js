db = db.getSiblingDB("itau");
db.createUser(
   {
     user: "itau",
     pwd: "itau",

     roles: [{"role":"readWrite","db":"itau"}]
   }
);