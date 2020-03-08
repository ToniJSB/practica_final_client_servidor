var mysql = require('mysql');

var con = mysql.createConnection({
  host: "localhost",
  user: "yourusername",
  password: "yourpassword",
  database: "mydb"
});

con.connect(function(err : any) {
  if (err) throw err;
  con.query("SELECT * FROM customers", function (err:any, result:any, fields:any) {
    if (err) throw err;
    console.log(result);
  });
});